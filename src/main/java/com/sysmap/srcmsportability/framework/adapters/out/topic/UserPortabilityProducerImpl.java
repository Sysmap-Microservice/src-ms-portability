package com.sysmap.srcmsportability.framework.adapters.out.topic;

import com.google.gson.Gson;
import com.sysmap.srcmsportability.application.ports.in.entities.Portability;

import com.sysmap.srcmsportability.application.ports.out.UserPortabilityProducer;
import com.sysmap.srcmsportability.framework.adapters.out.topic.dto.OutputPortability;
import com.sysmap.srcmsportability.framework.adapters.out.topic.dto.PortabilityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPortabilityProducerImpl implements UserPortabilityProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(Portability portability) {
        final PortabilityDTO portabilityDTO = PortabilityDTO.builder()
                .portabilityId(portability.getPortabilityId())
                .target(portability.getTarget())
                .source(portability.getSource())
                .build();

        final OutputPortability outputPortability = OutputPortability.builder()
                .number(portability.getUser().getLine().getNumber())
                .documentNumber(portability.getUser().getDocumentNumber())
                .portability(portabilityDTO)
                .build();

        log.info("Payload enviado {}", outputPortability);
        final Gson gson = new Gson();
        kafkaTemplate.send(topicName, gson.toJson(outputPortability));
    }
}
