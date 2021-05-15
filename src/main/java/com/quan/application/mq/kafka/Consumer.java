package com.quan.application.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-17
 **/
@Component
public class Consumer {

    @KafkaListener(id = "user", topics = "user", autoStartup = "${listen.auto.start:true}",
            concurrency = "${listen.concurrency:3}")
    public void consumer(ConsumerRecord consumerRecord) {
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMassage.isPresent()) {
            Object o = kafkaMassage.get();
            System.out.println(o);
        }
    }

    /**
     * 配置多个topic和partition，TopicPartition中partitions和PartitionOffset不能同时使用
     *
     * @param record
     */
//    @KafkaListener(id = "multiple", topicPartitions = {
//            @TopicPartition(topic = "multiple", partitions = {"0", "1"}),
//            @TopicPartition(topic = "multiple", partitions = "0",
//                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
//    })
    public void listen(ConsumerRecord<?, ?> record) {

    }

    /**
     * 使用ack手动确认模式
     *
     * @param data
     * @param ack
     */
//    @KafkaListener(id = "ack", topics = "ack",
//            containerFactory = "kafkaManualAckListenerContainerFactory")
//    public void listen(String data, Acknowledgment ack) {
//        ack.acknowledge();
//    }

    /**
     * 获取消息的header信息
     *
     * @param foo
     * @param key
     * @param partition
     * @param topic
     * @param ts
     */
    @KafkaListener(id = "header", topicPattern = "header")
    public void listen(@Payload String foo,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {

    }


    /**
     * 批处理
     *
     * @param list
     * @param keys
     * @param partitions
     * @param topics
     * @param offsets
     */
//    @KafkaListener(id = "batch", topics = "batch", containerFactory = "batchFactory")
//    public void listen(List<String> list,
//                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
//                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                       @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                       @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//
//    }


    /**
     * 使用@Valid校验数据
     *
     * @param val
     */
//    @KafkaListener(id = "validated", topics = "annotated35", errorHandler = "validationErrorHandler",
//            containerFactory = "kafkaJsonListenerContainerFactory")
//    public void validatedListener(@Payload @Valid ValidatedClass val) {
//    }


    /**
     * topic根据参数类型映射不同方法
     */
    @KafkaListener(id = "multi", topics = "myTopic")
    class MultiListenerBean {
        @KafkaHandler
        public void listen(String string) {
            System.out.println(string);
        }

        @KafkaHandler
        public void listen(Integer integer) {
            System.out.println(integer);
        }

        @KafkaHandler
        public void delete(@Payload(required = false) KafkaNull nul,
                           @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) int key) {

        }

    }

}

