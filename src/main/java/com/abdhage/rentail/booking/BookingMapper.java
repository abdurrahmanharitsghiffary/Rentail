package com.abdhage.rentail.booking;

import com.abdhage.rentail.booking.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    public BookingResponse bookingToBookingResponse(Booking booking);

    public Booking bookingResponseToBooking(BookingResponse booking);

}
