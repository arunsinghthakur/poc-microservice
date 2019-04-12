package com.safexpress.propeli.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class GetBookingController {

	@GetMapping
	public String getDummyBooking() {
		return "Your booking ID is - " + System.currentTimeMillis();
	}

}
