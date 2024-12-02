package com.petr.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerKafkaConfiguration {

    private final static String KAFKA_BROKER = "localhost:9091";
    private final static String TOPIC_NAME = "topic-example";
    private final static int REPLICAS = 3;
    private final static int PARTITIONS = 3;
    private final static String CONFIG_NAME = "min.insync.replicas";
    private final static String CONFIG_VALUE = "2";


    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(TOPIC_NAME)
                .replicas(REPLICAS)
                .partitions(PARTITIONS)
                .config(CONFIG_NAME, CONFIG_VALUE)
                .build();
    }

    @Bean
    public Map<String, Object> producerConfiguration() {
        HashMap<String, Object> configuration = new HashMap<>();
        configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configuration;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfiguration());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
