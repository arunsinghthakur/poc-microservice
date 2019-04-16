package com.safexpress.propeli.contract.integration.kafka;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.contract.domain.BookingRequest;
import com.safexpress.propeli.contract.integration.BookingIntegration;
import com.safexpress.propeli.messaging.publisher.AbstractMessagePublisher;

@Service("bookingKafkaIntegration")
public class BookingKafkaIntegrationImpl extends AbstractMessagePublisher<String, BookingRequest>
		implements BookingIntegration {

	@Autowired
	@Qualifier("bookingKafkaTemplate")
	private KafkaTemplate<String, BookingRequest> kafkaTemplate;

	@Value(value = "${kafka.booking.producer.topic.name}")
	private String bookingProducerTopic;

	@Override
	public void saveBooking(BookingRequest request) {
		publish(bookingProducerTopic, request, kafkaTemplate);
	}

	@Override
	public Optional<String> getBooking() {
		throw new IllegalAccessError("Not yet supported.");
	}

}
