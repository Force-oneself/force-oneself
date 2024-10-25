package com.quan.flink.demo;

import com.quan.flink.util.FlinkUtils;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.MemorySize;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;
import java.time.ZoneId;
import java.util.function.Consumer;


public class FlinkDemo {

    public static void main(String[] args) throws Exception {

        sourceWithDataGenerator();
    }

    private static void sinkToJdbc() throws Exception {
        StreamExecutionEnvironment env = getEnv();
        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "Number" + value,
                // 生成最大数字1000
                10000,
                // 限流1秒1条
                RateLimiterStrategy.perSecond(1000),
                Types.STRING
        );

        SinkFunction<String> sink = JdbcSink.sink(
                // 插入SQL
                "insert int ws values(?)",
                // 预编译SQL,占位符填写
                (JdbcStatementBuilder<String>) (ps, s) -> ps.setString(1, s),
                // 执行参数, 重试,批处理
                JdbcExecutionOptions.builder()
                        .withBatchIntervalMs(3000)
                        .withBatchSize(100)
                        .withMaxRetries(3)
                        .build(),
                // 连接参数, 账号密码, 连接url
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:mysql://localhost:3306/test")
                        .withUsername("root")
                        .withPassword("root")
                        .withConnectionCheckTimeoutSeconds(60)
                        .build()
        );
        env.fromSource(generatorSource, WatermarkStrategy.noWatermarks(), "file-out")
                .addSink(sink);
        env.setParallelism(1)
                .enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE)
                .execute();
    }

    private static void sinkToKafka() throws Exception {
        StreamExecutionEnvironment env = getEnv();
        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "Number" + value,
                // 生成最大数字1000
                10000,
                // 限流1秒1条
                RateLimiterStrategy.perSecond(1000),
                Types.STRING
        );

        KafkaSink<String> sink = KafkaSink.<String>builder()
                // 设置kafka地址
                .setBootstrapServers("localhost:9092")
                // 设置序列化方式
                .setRecordSerializer(KafkaRecordSerializationSchema.<String>builder()
                        .setTopic("sink-kafka")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build())
                // 发送到kafka的一致性级别
                .setDeliveryGuarantee(DeliveryGuarantee.EXACTLY_ONCE)
                // 精准一次必须: 设置事务前缀
                .setTransactionalIdPrefix("force-tx")
                // 精准一次必须: 设置事务超时时间: 大于checkpointing间隔,小于 max 15分钟
                .setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 10 * 60 * 1000 + "")
                .build();
        env.fromSource(generatorSource, WatermarkStrategy.noWatermarks(), "file-out")
                .sinkTo(sink);
        env.setParallelism(1)
                .enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE)
                .execute();
    }

    private static void sinkToFile() throws Exception {
        StreamExecutionEnvironment env = getEnv();
        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "Number" + value,
                // 生成最大数字1000
                10000,
                // 限流1秒1条
                RateLimiterStrategy.perSecond(1000),
                Types.STRING
        );

        // 输出行式存储的文件,指定路径指定编码
        FileSink<String> fileSink = FileSink.<String>forRowFormat(new Path("/d:/tmp"), new SimpleStringEncoder<>("UTF-8"))
                // 输出文件配置,文件名的前缀后缀
                .withOutputFileConfig(OutputFileConfig.builder()
                        .withPartPrefix("force")
                        .withPartSuffix(".log")
                        .build())
                // 按照目录分桶
                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyy-MM-dd HH", ZoneId.systemDefault()))
                // 文件滚动策略,1min 或者 1m
                .withRollingPolicy(DefaultRollingPolicy.builder()
                        .withRolloverInterval(Duration.ofMinutes(1))
                        .withMaxPartSize(new MemorySize(1024 * 1024))
                        .build())
                .build();
        env.fromSource(generatorSource, WatermarkStrategy.noWatermarks(), "file-out").sinkTo(fileSink);
        env.setParallelism(1)
                .enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE)
                .execute();
    }

    private static void methodNote() throws Exception {
        start(env -> env.fromElements(new int[]{1, 2, 3}, new int[]{2, 3, 4}, new int[]{3, 4, 5})
                // 一转多
                .flatMap((int[] value, Collector<Integer> out) -> {
                    for (int v : value) {
                        out.collect(v);
                    }
                })
                // 过滤
                .filter(value -> value % 3 == 0)
                // 一转一
                //.map(value -> value + ":" + value)
                .process(new ProcessFunction<Integer, Integer>() {

                    @Override
                    public void processElement(Integer value, ProcessFunction<Integer, Integer>.Context ctx, Collector<Integer> out) {
                        if (value % 2 == 0) {
                            ctx.output(new OutputTag<>("side"), value);
                        } else {
                            ctx.output(new OutputTag<>("main"), value);
                        }
                    }
                })
                // 侧输出流
                .getSideOutput(new OutputTag<String>("late-data") {
                })
                // 负载均衡,解决数据倾斜
                .rebalance()
                // 缩放: 实现轮询,局部组队
                .rescale()
                // 随机分区
                .shuffle()
                // 广播,发送给下游所有子任务
                .broadcast()
                // 只发到下游第一个子任务
                .global()
                .keyBy(value -> value)
                .max(1));
    }


    /**
     * source 从生成器读取,需要引入 flink-connector-datagen 依赖
     *
     */
    private static void sourceWithDataGenerator() {
        DataGeneratorSource<String> generatorSource = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "Number" + value,
                // 生成最大数字1000
                1000,
                // 限流1秒1条
                RateLimiterStrategy.perSecond(1),
                Types.STRING
        );
        start(env -> env.fromSource(generatorSource,
                        WatermarkStrategy.noWatermarks(),
                        "generator-source")
                .print());
    }

    /**
     * source 从Kafka中读取,需要引入 flink-connector-kafka 依赖
     *
     * @throws Exception runtime
     */
    private static void sourceWithKafka() throws Exception {
        start(env -> env.fromSource(KafkaSource.<String>builder()
                                .setBootstrapServers("localhost:9092")
                                .setTopics("topic")
                                .setGroupId("group")
                                .setValueOnlyDeserializer(new SimpleStringSchema())
                                .build(),
                        WatermarkStrategy.noWatermarks(),
                        "kafka-source")
                .print()
        );
    }

    /**
     * source 从文件中读取,需要引入 flink-connector-files 依赖
     *
     * @throws Exception runtime
     */
    private static void sourceWithFile() throws Exception {
        start(env -> env.fromSource(FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("/Users/quan/Documents/test.txt"))
                        .build(), WatermarkStrategy.noWatermarks(), "file-source")
                .print());
    }

    private static void sourceWithCollection() {
        start(env ->
                // 变长数组
                env.fromElements(1, 2, 3)
                        // 使用集合
                        //.fromCollection(Arrays.asList(1, 2, 3))
                        .print()
        );

    }

    private static StreamExecutionEnvironment getEnv() {
        return FlinkUtils.getEnv();
    }

    private static void env() {
        // 创建本地环境
        StreamExecutionEnvironment.createLocalEnvironment();
        // 创建远程环境
        StreamExecutionEnvironment.createRemoteEnvironment("", 8081, "");
        // 自动识别本地/远程环境
        Configuration conf = new Configuration();
        conf.set(RestOptions.BIND_PORT, "8081");
        StreamExecutionEnvironment.getExecutionEnvironment(conf);
    }


    private static void start(Consumer<StreamExecutionEnvironment> envConsumer) {
        FlinkUtils.start(envConsumer);
    }

    private static void localEenWithWebUI() {

        try (StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration())) {
            env.setParallelism(2);
            // 运行模式 默认流模式
            env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
            env.socketTextStream("localhost", 7777)
                    .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {
                        String[] words = value.split(" ");
                        for (String word : words) {
                            out.collect(Tuple2.of(word, 1));
                        }
                    })
                    //.setParallelism(2)
                    .returns(Types.TUPLE(Types.STRING, Types.INT))
                    .keyBy(value -> value.f0)
                    .sum(1)
                    .print();
            env.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
