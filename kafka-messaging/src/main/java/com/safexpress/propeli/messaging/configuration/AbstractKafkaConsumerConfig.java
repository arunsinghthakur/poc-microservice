package com.safexpress.propeli.messaging.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

public abstract class AbstractKafkaConsumerConfig<K, V> {

	protected ConsumerFactory<K, V> consumerFactory(String bootstrapServers, String groupId, String keyDeserializer,
			String valueDeserializer) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	protected ConcurrentKafkaListenerContainerFactory<K, V> kafkaListenerContainerFactory(String bootstrapServers,
			String groupId, String keyDeserializer, String valueDeserializer) {
		ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(bootstrapServers, groupId, keyDeserializer, valueDeserializer));
		return factory;
	}

}
