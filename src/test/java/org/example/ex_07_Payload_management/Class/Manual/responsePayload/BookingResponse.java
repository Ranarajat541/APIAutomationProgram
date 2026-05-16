package org.example.ex_07_Payload_management.Class.Manual.responsePayload;

import org.example.ex_07_Payload_management.Class.Manual.requestPayload.Booking;

public class BookingResponse {
    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    private Integer bookingid;
    private Booking booking;

}

