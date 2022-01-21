package com.sysmap.srcmsportability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SrcMsPortabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrcMsPortabilityApplication.class, args);
	}

}
