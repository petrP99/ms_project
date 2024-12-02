package com.petr.producer.controller;

import com.petr.producer.config.MyTopicConsumerListener;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {

    private final MyTopicConsumerListener topic;

    @GetMapping("/get")
    public List<String> getMessage() {
        return topic.getMessages();
    }
}
