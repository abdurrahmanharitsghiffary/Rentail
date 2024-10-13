package com.abdhage.rentail.booking;

import com.abdhage.rentail.booking.dto.BookUnitDTO;
import com.abdhage.rentail.booking.model.Booking;
import com.abdhage.rentail.booking.model.BookingId;
import com.abdhage.rentail.booking.model.BookingStatus;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    public List<BookingResponse> findAllByUnit(UUID unitId);

    public BookingResponse bookAccommodationUnit(BookUnitDTO dto);

    public BookingResponse updateStatus(BookingId id, BookingStatus status);

    public BookingResponse updateStatus(Booking booking, BookingStatus status);

    public Booking checkBookingMustExists(BookingId bookingId);

    public BookingResponse findOne(BookingId bookingId);

    public void cancelBooking(BookingId bookingId);

    public void confirmBooking(BookingId bookingId);

    public void completeBooking(BookingId bookingId);

    public void payBooking(BookingId bookingId);

    public void verifyPermission(Booking booking);

    public void verifyPermission(BookingId bookingId);

}
