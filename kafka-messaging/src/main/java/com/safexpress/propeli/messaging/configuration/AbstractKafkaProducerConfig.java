package com.safexpress.propeli.messaging.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

public abstract class AbstractKafkaProducerConfig<K, V> {

	protected ProducerFactory<K, V> producerFactory(String bootstrapServers, String keySerializer, String ValueSerializer) {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ValueSerializer);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	protected KafkaTemplate<K, V> kafkaTemplate(String bootstrapServers, String keySerializer, String ValueSerializer) {
		return new KafkaTemplate<>(producerFactory(bootstrapServers, keySerializer, ValueSerializer));
	}

	protected KafkaAdmin kafkaAdmin(String bootstrapServers) {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(configs);
	}

	protected NewTopic topic(final String topicName, final int numOfPartitions, final short replicationFactor) {
		return new NewTopic(topicName, numOfPartitions, replicationFactor);
	}
}
