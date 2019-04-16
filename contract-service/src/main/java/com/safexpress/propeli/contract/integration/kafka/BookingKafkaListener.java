package com.safexpress.propeli.contract.integration.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.safexpress.propeli.contract.dao.ContractRepository;
import com.safexpress.propeli.contract.domain.BookingRequest;
import com.safexpress.propeli.contract.entity.ContractEntity;
import com.safexpress.propeli.contract.integration.redis.ContractCacheManager;
import com.safexpress.propeli.messaging.consumer.AbstractMessageConsumer;

@Component
public class BookingKafkaListener extends AbstractMessageConsumer<BookingRequest> {

	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	@Qualifier("contractCacheManager")
	private ContractCacheManager contractCacheManager;

	@Override
	@KafkaListener(topics = "booking-response", groupId = "booking-group")
	public void consume(BookingRequest message) {
		
		ContractEntity ce = contractRepository.save(new ContractEntity(message.getDescription()));
		contractCacheManager.save(ce);
	}

}
