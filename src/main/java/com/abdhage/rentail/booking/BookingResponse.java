package com.abdhage.rentail.booking;

import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.booking.model.BookingStatus;
import com.abdhage.rentail.user.model.User;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private String notes;
    private BookingStatus status;
    private Date startFrom;
    private User user;
    private AccommodationUnit unit;

}
