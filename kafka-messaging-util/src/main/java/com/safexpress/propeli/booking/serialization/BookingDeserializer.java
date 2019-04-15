package com.safexpress.propeli.booking.serialization;

import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.messaging.configuration.AbstractDeserializer;

public class BookingDeserializer extends AbstractDeserializer<BookingRequest> {

	public BookingDeserializer() {
		super(BookingRequest.class);
	}
}
