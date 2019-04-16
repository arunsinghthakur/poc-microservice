package com.safexpress.propeli.booking.integration.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.messaging.configuration.AbstractKafkaProducerConfig;

@Configuration
public class KafkaBookingProducerConfiguration extends AbstractKafkaProducerConfig<String, BookingRequest> {

	@Value(value = "${kafka.booking.producer.bootstrapServers}")
	private String bootstrapServers;

	@Value(value = "${kafka.booking.producer.topic.name}")
	private String bookingTopic;

	@Value(value = "${kafka.booking.producer.topic.replication-factor}")
	private short replicationFactor;

	@Value(value = "${kafka.booking.producer.topic.partitions}")
	private int numOfPartitions;

	@Value("${kafka.booking.producer.topic.key-serializer}")
	private String keySerializer;

	@Value("${kafka.booking.producer.topic.value-serializer}")
	private String valueSerializer;

	public KafkaBookingProducerConfiguration() {
	}

	@Bean
	@Qualifier("bookingProducer")
	public ProducerFactory<String, BookingRequest> producerFactory() {
		return super.producerFactory(bootstrapServers, keySerializer, valueSerializer);
	}

	@Bean
	@Qualifier("bookingProducerTopic")
	public NewTopic topic() {
		return super.topic(bookingTopic, numOfPartitions, replicationFactor);
	}

	@Bean
	@Qualifier("bookingKafkaAdmin")
	public KafkaAdmin kafkaAdmin() {
		return super.kafkaAdmin(bootstrapServers);
	}

	@Bean
	@Qualifier("bookingKafkaTemplate")
	public KafkaTemplate<String, BookingRequest> kafkaTemplate() {
		return super.kafkaTemplate(bootstrapServers, keySerializer, valueSerializer);
	}

}
