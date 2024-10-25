package com.quan.flink.demo;

import com.quan.flink.event.ClickEvent;
import com.quan.flink.util.FlinkUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

public class FlinkWindowsDemo {

    public static void main(String[] args) {
        windowProcess();
    }

    public static void windowProcess() {
        // 构建数据源
        DataGeneratorSource<ClickEvent> generator = new DataGeneratorSource<>(
                (GeneratorFunction<Long, ClickEvent>) value -> new ClickEvent(String.valueOf(value % 5), Math.toIntExact(value)),
                10,
                RateLimiterStrategy.perSecond(1),
                Types.POJO(ClickEvent.class)
        );

        FlinkUtils.start(env -> {
                    KeyedStream<ClickEvent, String> stream = env
                            .fromSource(generator, WatermarkStrategy.noWatermarks(), "windows-source")
                            .returns(Types.POJO(ClickEvent.class))
                            .keyBy(ClickEvent::getUserId);
                    //stream.print("原始数据");
                    stream
                            // 滚动窗口
                            .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                            //.process((new ProcessWindowFunction<ClickEvent, String, String, TimeWindow>() {
                            //    @Override
                            //    public void process(String key, Context context, Iterable<ClickEvent> input, Collector<String> out) throws Exception {
                            //        int sum = 0;
                            //        for (ClickEvent clickEvent : input) {
                            //            sum += clickEvent.getCount();
                            //        }
                            //        long windowEnd = context.window().getEnd();
                            //        out.collect("windowEnd: " + windowEnd + " sum: " + sum);
                            //    }
                            //}))
                            .apply((WindowFunction<ClickEvent, String, String, TimeWindow>) (s, window, input, out) -> {

                            })
                            .print();
                    ;
                }
        );
    }

    public static void windowAggregate() {
        // 构建数据源
        DataGeneratorSource<ClickEvent> generator = new DataGeneratorSource<>(
                (GeneratorFunction<Long, ClickEvent>) value -> new ClickEvent(String.valueOf(value % 5), Math.toIntExact(value)),
                10,
                RateLimiterStrategy.perSecond(1),
                Types.POJO(ClickEvent.class)
        );

        FlinkUtils.start(env -> {
                    KeyedStream<ClickEvent, String> stream = env
                            .fromSource(generator, WatermarkStrategy.noWatermarks(), "windows-source")
                            .returns(Types.POJO(ClickEvent.class))
                            .keyBy(ClickEvent::getUserId);
                    //stream.print("原始数据");
                    stream
                            // 滚动窗口
                            .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                            // 滑动窗口
                            //.window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
                            // 会话窗口
                            //.window(EventTimeSessionWindows.withGap(Time.minutes(5)))
                            // 计数窗口
                            //.countWindow(20)
                            // 滑动计数窗口
                            //.countWindow(10, 5)
                            .aggregate(new AggregateFunction<ClickEvent, Integer, String>() {
                                @Override
                                public Integer createAccumulator() {
                                    System.out.println("创建累加器");
                                    return 0;
                                }

                                @Override
                                public Integer add(ClickEvent value, Integer accumulator) {
                                    System.out.printf("Event: %s %d", value, accumulator);
                                    return accumulator + value.getCount();
                                }

                                @Override
                                public String getResult(Integer accumulator) {
                                    System.out.println("getResult:" + accumulator);
                                    return accumulator.toString();
                                }

                                @Override
                                public Integer merge(Integer a, Integer b) {
                                    System.out.println("merge");
                                    return null;
                                }
                            })
                    ;
                }
        );
    }

    public static void windowReduce() {
        // 构建数据源
        DataGeneratorSource<ClickEvent> generator = new DataGeneratorSource<>(
                (GeneratorFunction<Long, ClickEvent>) value -> new ClickEvent(String.valueOf(value % 5), Math.toIntExact(value)),
                100,
                RateLimiterStrategy.perSecond(1),
                Types.POJO(ClickEvent.class)
        );

        FlinkUtils.start(env -> {
                    KeyedStream<ClickEvent, String> stream = env
                            .fromSource(generator, WatermarkStrategy.noWatermarks(), "windows-source")
                            .returns(Types.POJO(ClickEvent.class))
                            .keyBy(ClickEvent::getUserId);
                    stream.print("原始数据");
                    stream
                            // 滚动窗口
                            //.window(TumblingEventTimeWindows.of(Time.seconds(5)))
                            // 滑动窗口
                            //.window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
                            // 会话窗口
                            //.window(EventTimeSessionWindows.withGap(Time.minutes(5)))
                            // 计数窗口
                            .countWindow(20)
                            // 滑动计数窗口
                            //.countWindow(10, 5)
                            // reduce
                            //.reduce((ReduceFunction<Tuple2<String, Integer>>) (value1, value2) -> Tuple2.of(value1.f0, value1.f1 + value2.f1))
                            .reduce((ReduceFunction<ClickEvent>) (value1, value2) -> new ClickEvent(value1.getUserId(), value1.getCount() + value2.getCount()))
                            .assignTimestampsAndWatermarks(WatermarkStrategy
                                    .<ClickEvent>forBoundedOutOfOrderness(Duration.ofSeconds(5)) // 设置最大允许的乱序时间
                                    .withTimestampAssigner((SerializableTimestampAssigner<ClickEvent>) (element, recordTimestamp) -> element.getTimestamp()))
                            .print("窗口数据");
                }
        );
    }

}
