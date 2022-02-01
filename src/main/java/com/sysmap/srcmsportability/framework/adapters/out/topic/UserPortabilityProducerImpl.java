package com.sysmap.srcmsportability.framework.adapters.out.topic;

import com.google.gson.Gson;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;

import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.framework.adapters.out.topic.dto.OutputPortability;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPortabilityProducerImpl
        implements
        UserPortabilityProducer
{
    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(Portability portability)
    {
        OutputPortability outputPortability = new OutputPortability(portability);
        log.info("Payload enviado {}", outputPortability);
        Gson gson = new Gson();
        kafkaTemplate.send(topicName, gson.toJson(outputPortability));
    }
}