package com.sysmap.srcmsportability.framework.adapters.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "sulky.srvs.cloudkafka.com:9094");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("sasl.mechanism", "SCRAM-SHA-256");
        configProps.put("group-id", "portability-producer");
        configProps.put("topic.name.producer", "i28hrd7l-portability");
        configProps.put("sasl.jaas.config", " org.apache.kafka.common.security.scram.ScramLoginModule required username=\"i28hrd7l\" password=\"wvUr9X8jxhdUJxoATUgVETAnSA-Xonja\"; ");
        configProps.put("security.protocol", "SASL_SSL");
        configProps.put("auto.create.topics.enable", "true");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
            return new KafkaTemplate<>(producerFactory());
    }

}
