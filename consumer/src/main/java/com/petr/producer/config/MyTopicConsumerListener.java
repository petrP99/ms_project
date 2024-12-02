package com.petr.producer.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
@Getter
@RequiredArgsConstructor
public class MyTopicConsumerListener {

    private final List<String> messages = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "MyTopic", groupId = "kafka_id")
    public void listener(String value) throws InterruptedException {
        System.err.println(value);
        Thread.sleep(1000);
    }

    @KafkaListener(topics = "topic-example", groupId = "kafka_id")
    public void listener2(String value) throws InterruptedException {
        Thread.sleep(1000);
        System.err.println(value);
    }

}
