package com.petr.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.randomUUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/getToTopic")
    private String sendTopic() {
        kafkaTemplate.send("MyTopic", "ключ кафки", randomUUID().toString());
        kafkaTemplate.send("topic-example", randomUUID().toString());
        return "Сообщение опубликовано в топике";
    }
}
