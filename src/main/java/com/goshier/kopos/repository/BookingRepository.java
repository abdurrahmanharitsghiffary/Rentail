package com.goshier.kopos.repository;

import com.goshier.kopos.model.AccommodationBooking;
import com.goshier.kopos.model.ids.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<AccommodationBooking, BookingId> {
}