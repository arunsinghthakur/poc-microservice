package com.safexpress.propeli.contract.integration;

import java.util.Optional;

import com.safexpress.propeli.booking.domain.BookingRequest;

public interface BookingIntegration {

	Optional<String> getBooking();
	
	void saveBooking(BookingRequest request);
	

}
