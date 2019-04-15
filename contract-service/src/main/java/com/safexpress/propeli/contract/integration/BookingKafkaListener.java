package com.safexpress.propeli.contract.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.messaging.consumer.AbstractMessageConsumer;

@Component
public class BookingKafkaListener extends AbstractMessageConsumer<BookingRequest> {

	@Autowired
	private ContractRepository contractRepository;

	@Override
	@KafkaListener(topics = "booking-response", groupId = "booking-group")
	public void consume(BookingRequest message) {
		contractRepository.save(new ContractEntity(message.getDescription()));
	}

}
