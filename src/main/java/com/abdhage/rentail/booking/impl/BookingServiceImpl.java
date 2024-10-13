package com.abdhage.rentail.booking.impl;

import com.abdhage.rentail.accommodation.AccommodationChecker;
import com.abdhage.rentail.accommodationunit.AccommodationUnitService;
import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.booking.BookingRepository;
import com.abdhage.rentail.booking.BookingResponse;
import com.abdhage.rentail.booking.BookingService;
import com.abdhage.rentail.booking.dto.BookUnitDTO;
import com.abdhage.rentail.booking.model.BookingId;
import com.abdhage.rentail.booking.model.BookingStatus;
import com.abdhage.rentail.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final AccommodationChecker accommodationChecker;
    private final AccommodationUnitService accommodationUnitService;

    public BookingServiceImpl(BookingRepository bookingRepository, AccommodationChecker accommodationChecker, AccommodationUnitService accommodationUnitService) {
        this.bookingRepository = bookingRepository;
        this.accommodationChecker = accommodationChecker;
        this.accommodationUnitService = accommodationUnitService;
    }

    @Override
    public BookingResponse bookAccommodation(BookUnitDTO dto) {
        AccommodationUnit unit = accommodationUnitService.checkAccommodationUnitMustExists(dto.getUnitId());
        if (unit.getAccommodation() == null) throw new NotFoundException("Accommodation not found");

        return null;
    }

    @Override
    public void destroy(BookingId bookingId) {
    }

    @Override
    public BookingResponse updateStatus(BookingId id, BookingStatus status) {
        return null;
    }

}
