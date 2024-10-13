package com.abdhage.rentail.booking;

import com.abdhage.rentail.booking.model.Booking;
import com.abdhage.rentail.booking.model.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingId> {
    List<Booking> findById_UnitId(UUID unitId);
}