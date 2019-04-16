package com.safexpress.propeli.contract.integration.kafka;

import com.safexpress.propeli.contract.domain.BookingRequest;
import com.safexpress.propeli.messaging.configuration.AbstractDeserializer;

public class BookingDeserializer extends AbstractDeserializer<BookingRequest> {

	public BookingDeserializer() {
		super(BookingRequest.class);
	}
}
