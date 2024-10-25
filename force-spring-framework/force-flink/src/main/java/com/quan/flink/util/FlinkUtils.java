package com.quan.flink.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class FlinkUtils {
    public static StreamExecutionEnvironment getEnv() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }

    public static void start(Consumer<StreamExecutionEnvironment> envConsumer) {
        StreamExecutionEnvironment env = getEnv();
        Logger logger = (Logger) LoggerFactory.getLogger("org.apache.flink");
        logger.setLevel(Level.ERROR);
        try {
            env.setParallelism(1);
            envConsumer.accept(env);
            env.setParallelism(1)
                    .enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE)
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
