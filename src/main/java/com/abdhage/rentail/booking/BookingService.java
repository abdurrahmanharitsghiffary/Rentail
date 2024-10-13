package com.abdhage.rentail.booking;

import com.abdhage.rentail.booking.dto.BookUnitDTO;
import com.abdhage.rentail.booking.model.BookingId;
import com.abdhage.rentail.booking.model.BookingStatus;

public interface BookingService {

    public BookingResponse bookAccommodation(BookUnitDTO dto);

    public void destroy(BookingId bookingId);

    public BookingResponse updateStatus(BookingId id, BookingStatus status);
}
