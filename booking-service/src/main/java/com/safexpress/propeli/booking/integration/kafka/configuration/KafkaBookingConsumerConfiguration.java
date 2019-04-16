package com.safexpress.propeli.booking.integration.kafka.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.messaging.configuration.AbstractKafkaConsumerConfig;

@Configuration
@EnableKafka
public class KafkaBookingConsumerConfiguration extends AbstractKafkaConsumerConfig<String, BookingRequest> {

	@Value(value = "${kafka.booking.consumer.bootstrapServers}")
	private String bootstrapServers;

	@Value(value = "${kafka.booking.consumer.topic.name}")
	private String bookingTopic;

	@Value(value = "${kafka.booking.consumer.topic.groupid}")
	private String groupId;

	@Value("${kafka.booking.consumer.topic.key-deserializer}")
	private String keyDeserializer;

	@Value("${kafka.booking.consumer.topic.value-deserializer}")
	private String valueDeserializer;

	public KafkaBookingConsumerConfiguration() {
	}

	@Bean
	@Qualifier("bookingConsumer")
	public ConsumerFactory<String, BookingRequest> consumerFactory() {
		return super.consumerFactory(bootstrapServers, groupId, keyDeserializer, valueDeserializer);
	}

	@Bean
	@Qualifier("bookingKafkaListener")
	public ConcurrentKafkaListenerContainerFactory<String, BookingRequest> kafkaListenerContainerFactory() {
		return super.kafkaListenerContainerFactory(bootstrapServers, groupId, keyDeserializer, valueDeserializer);
	}

}
