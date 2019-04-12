package com.safexpress.propeli.contract.integration.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.safexpress.propeli.booking.domain.BookingRequest;
import com.safexpress.propeli.contract.integration.BookingIntegration;

@Service("bookingRestIntegration")
public class BookingRestIntegrationImpl implements BookingIntegration {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Override
	@HystrixCommand(fallbackMethod = "getBookingFallback", commandKey = "BOOKING-INTERGRATION")
	public Optional<String> getBooking() {
		try {
			return Optional
					.of(restTemplate.getForObject(new URI(env.getProperty("booking-service.url")), String.class));
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
			// TODO Use custom exception here and throw that.
		}
		return Optional.empty();
	}

	private Optional<String> getBookingFallback() {
		// TODO Add some logger here.
		return Optional.of("Booking service is down. This is dummy response from fallback service.");
	}

	@Override
	public void saveBooking(BookingRequest request) {
	}
}
