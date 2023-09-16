package com.quan.framework.mongo.helper;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Force-oneself
 * @Description MongoTemplateHelper
 * @date 2021-08-20
 */
@Component
public class MongoTemplateHelper {

    private final MongoTemplate mongoTemplate;

    public MongoTemplateHelper(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 分页带转换器和分页自定义的通用实现
     *
     * @param provider  条件提供器
     * @param converter 转换器
     * @param <T>       实际表实体
     * @param <R>       转换类型
     * @return 分页数据
     */
    public <T, R, P> P page(PageableQuery<T> provider, Function<List<T>, List<R>> converter,
                            BiFunction<Pair<PageableQuery<T>, Long>, List<R>, P> pageProvider) {
        final Class<T> queryClass = provider.queryClass();
        Query condition = provider.get() == null ? new Query() : provider.get();
        long count = 0L;
        // 分页
        if (provider.enable()) {
            // 获取总数
            count = mongoTemplate.count(condition, queryClass);
            condition.skip(provider.skip()).limit(provider.size());
        }

        // 获取分页数据
        List<T> entities = mongoTemplate.find(condition, queryClass);

        Pair<PageableQuery<T>, Long> pair = Pair.of(provider, count);
        return pageProvider.apply(pair, converter.apply(entities));
    }

    /**
     * 分页带自定义转换器
     *
     * @param provider  条件提供器
     * @param converter 转换器
     * @param <T>       实际表实体
     * @param <R>       转换类型
     * @return 分页数据
     */
//    public <T, R> IPage<R> page( PageableQuery<T> provider,  Function<List<T>, List<R>> converter) {
//        return this.page(provider, converter,
//                (pair, res) -> {
//                    PageableQuery<T> query = pair.getFirst();
//                    Long count = pair.getSecond();
//                    // 组装page
//                    final Page<R> page = new Page<>(query.current(), query.size(), count);
//                    page.setRecords(res);
//                    return page;
//                });
//    }

    /**
     * 分页带自定义分页插件
     *
     * @param provider     条件提供器
     * @param pageProvider 分页提供器
     * @param <T>          实际封装实体类型
     * @param <P>          分页类型
     * @return
     */
    public <T, P> P page(PageableQuery<T> provider, BiFunction<Pair<PageableQuery<T>, Long>, List<T>, P> pageProvider) {
        return this.page(provider, obj -> obj, pageProvider);
    }

    /**
     * 分页通用实现
     *
     * @param provider 条件提供器
     * @param <T>      实际表实体
     * @return 分页数据
     */
//    public <T> IPage<T> page( PageableQuery<T> provider) {
//        return this.page(provider, obj -> obj);
//    }
    public void page() {

    }
}
