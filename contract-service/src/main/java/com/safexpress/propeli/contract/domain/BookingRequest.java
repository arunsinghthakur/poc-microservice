package com.safexpress.propeli.contract.domain;

import java.io.Serializable;

public class BookingRequest implements Serializable {
	private static final Long serialVersionUID = 4262440358470589219L;
	private Long bookingId;
	private String description;

	public BookingRequest() {
	}

	public BookingRequest(Long bookingId, String description) {
		super();
		this.bookingId = bookingId;
		this.description = description;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BookingRequest [bookingId=" + bookingId + ", description=" + description + "]";
	}

}
