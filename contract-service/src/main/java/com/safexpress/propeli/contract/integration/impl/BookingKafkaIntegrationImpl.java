package com.safexpress.propeli.contract.integration.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.contract.integration.BookingIntegration;

@Service("bookingKafkaIntegration")
public class BookingKafkaIntegrationImpl implements BookingIntegration {

	@Autowired
	@Qualifier("bookingKafkaTemplate")
	private KafkaTemplate<String, BookingRequest> kafkaTemplate;

	@Autowired
	private ContractRepository contractRepository;

	@Value(value = "${kafka.booking.producer.topic.name}")
	private String bookingProducerTopic;

	@Override
	public void saveBooking(BookingRequest request) {
		kafkaTemplate.send(bookingProducerTopic, request);
	}

	@KafkaListener(topics = "booking-response", groupId = "booking-group")
	public void listen(BookingRequest bookingResponse) {
		contractRepository.save(new ContractEntity(bookingResponse.getBookingId(), bookingResponse.getDescription()));
	}

	@Override
	public Optional<String> getBooking() {
		return null;
	}

}
