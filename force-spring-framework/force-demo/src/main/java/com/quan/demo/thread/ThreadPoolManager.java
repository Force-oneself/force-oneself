package com.quan.demo.thread;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 线程池管理中心
 *
 * @author Force-oneself
 * @date 2025-01-20
 */
public class ThreadPoolManager implements InitializingBean {

    /**
     * 线程池缓存
     */
    private final Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<>();

    /**
     * 配置前缀, force.dynamic.tp.thread-pools.
     */
    private final static String THREAD_POOL_PROPERTIES_PREFIX = DynamicThreadPoolProperties.PREFIX + "." + "thread-pools.";

    /**
     * 配置变更处理器
     */
    private final Map<String, BiConsumer<ThreadPoolExecutor, String>> changeProcessors = new ConcurrentHashMap<>();

    /**
     * 应用上下文
     */
    private final ConfigurableApplicationContext applicationContext;

    /**
     * 告警管理类
     */
    private final AlarmManager alarmManager;

    public ThreadPoolManager(ConfigurableApplicationContext applicationContext, AlarmManager alarmManager) {
        this.applicationContext = applicationContext;
        this.alarmManager = alarmManager;
    }

    @Override
    public void afterPropertiesSet() {
        this.init();
    }

    /**
     * 初始化
     */
    private void init() {
        // 线程数配置相关
        changeProcessors.put("core-pool-size", (tpe, value) -> tpe.setCorePoolSize(Integer.parseInt(value)));
        changeProcessors.put("maximum-pool-size", (tpe, value) -> tpe.setMaximumPoolSize(Integer.parseInt(value)));
        changeProcessors.put("keep-alive-time", (tpe, value) -> tpe.setKeepAliveTime(Long.parseLong(value), TimeUnit.MILLISECONDS));

        // 拒绝策略
        changeProcessors.put("rejected-execution-handler", (tpe, value) -> {
            Map<String, RejectedExecutionHandler> rejectedBeanMap = Arrays.stream(this.applicationContext.getBeanNamesForType(RejectedExecutionHandler.class))
                    .collect(Collectors.toMap(Function.identity(), name -> applicationContext.getBean(name, RejectedExecutionHandler.class)));
            RejectedExecutionHandler rejected = rejectedBeanMap.getOrDefault(value, this.defaultRejectedHandlers().get(value));
            if (rejected != null) {
                tpe.setRejectedExecutionHandler(rejected);
            }
        });

        // 线程池工厂
        changeProcessors.put("thread-factory", (tpe, value) -> {
            Map<String, ThreadFactory> threadFactoryMap = Arrays.stream(this.applicationContext.getBeanNamesForType(ThreadFactory.class))
                    .collect(Collectors.toMap(Function.identity(), name -> applicationContext.getBean(name, ThreadFactory.class)));
            ThreadFactory threadFactory = threadFactoryMap.get(value);
            if (threadFactory != null) {
                tpe.setThreadFactory(threadFactory);
            }
        });

        // TODO 队列大小 后续补充

        // 注册已经被Spring管理的线程池
        Arrays.stream(this.applicationContext.getBeanNamesForType(ThreadPoolExecutor.class))
                .forEach(name -> this.register(name, applicationContext.getBean(name, ThreadPoolExecutor.class)));

        // 注册告警
        threadPoolMap.forEach(alarmManager::register);

        // 启动告警
        alarmManager.start();
    }

    /**
     * 默认拒绝策略
     *
     * @return 默认拒绝策略
     */
    public Map<String, RejectedExecutionHandler> defaultRejectedHandlers() {
        Map<String, RejectedExecutionHandler> rejectedMap = new HashMap<>();
        rejectedMap.put("AbortPolicy", new ThreadPoolExecutor.AbortPolicy());
        rejectedMap.put("CallerRunsPolicy", new ThreadPoolExecutor.CallerRunsPolicy());
        rejectedMap.put("DiscardPolicy", new ThreadPoolExecutor.DiscardPolicy());
        rejectedMap.put("DiscardOldestPolicy", new ThreadPoolExecutor.DiscardOldestPolicy());
        return rejectedMap;
    }

    /**
     * 注册线程池
     *
     * @param name               线程池名称
     * @param threadPoolExecutor 线程池
     */
    public void register(String name, ThreadPoolExecutor threadPoolExecutor) {
        threadPoolMap.put(name, threadPoolExecutor);
    }

    /**
     * 获取线程池
     *
     * @param name 线程池名称
     * @return 线程池
     */
    public ThreadPoolExecutor get(String name) {
        return threadPoolMap.get(name);
    }

    /**
     * 配置变更执行操作
     *
     * @param keys 配置变更key
     */
    public void change(Set<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        keys.stream()
                // force.dynamic.tp.thread-pools. : 配置前缀
                .filter(THREAD_POOL_PROPERTIES_PREFIX::startsWith)
                .forEach(changeKey -> {
                    // 切割出对应ThreadTool的Name 和 对应更新的key
                    String[] nameAndKeySplit = changeKey.substring(THREAD_POOL_PROPERTIES_PREFIX.length())
                            .split("\\.");
                    // 获取对应的线程池
                    Optional.ofNullable(threadPoolMap.get(nameAndKeySplit[0]))
                            // 设置对应线程池参数
                            .ifPresent(tpe -> changeProcessors.getOrDefault(nameAndKeySplit[1], (t, val) -> {
                                        // 没有相应处理则什么都不做
                                    })
                                    .accept(tpe, applicationContext.getEnvironment().getProperty(changeKey)));

                });
    }

}