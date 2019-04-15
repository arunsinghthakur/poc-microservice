package com.safexpress.propeli.messaging.publisher;

import java.io.Serializable;

import org.springframework.kafka.core.KafkaTemplate;

public abstract class AbstractMessagePublisher<T extends Serializable, M extends Object> {

	public void publish(T topic, M message, KafkaTemplate<T, M> kafkaTemplate) {
		kafkaTemplate.send((String) topic, message);
	}

}
