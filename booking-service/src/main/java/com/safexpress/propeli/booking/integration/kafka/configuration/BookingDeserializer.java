package com.safexpress.propeli.booking.integration.kafka.configuration;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.messaging.configuration.AbstractDeserializer;

public class BookingDeserializer extends AbstractDeserializer<BookingRequest> {

	public BookingDeserializer() {
		super(BookingRequest.class);
	}
}
