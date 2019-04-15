package com.safexpress.propeli.booking.integration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.booking.domain.BookingRequest;

@Service("bookingKafkaIntegration")
public class BookingKafkaIntegrationImpl implements BookingIntegration {

	@Autowired
	@Qualifier("bookingKafkaTemplate")
	private KafkaTemplate<String, BookingRequest> kafkaTemplate;

	@Value(value = "${kafka.booking.producer.topic.name}")
	private String bookingProducerTopic;
	
	@Value(value = "${kafka.booking.consumer.topic.name}")
	private String bookingTopic;

	private void sendMessage(BookingRequest bookingRequest) {
		kafkaTemplate.send(bookingProducerTopic, bookingRequest);
	}

	@KafkaListener(topics = "booking-request", groupId = "booking-group")
	public void listen(BookingRequest bookingResponse) {
		System.out.println("Received Messasge - : " + bookingResponse);
		bookingResponse.setDescription("This is async responce send from booking service over kafka.");
		sendMessage(bookingResponse);
	}

}
