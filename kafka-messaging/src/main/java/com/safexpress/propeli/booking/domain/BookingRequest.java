package com.safexpress.propeli.booking.domain;

import java.io.Serializable;

public class BookingRequest implements Serializable {
	private static final long serialVersionUID = 4262440358470589219L;
	private long bookingId;
	private String description;

	public BookingRequest() {
	}

	public BookingRequest(long bookingId, String description) {
		super();
		this.bookingId = bookingId;
		this.description = description;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
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
