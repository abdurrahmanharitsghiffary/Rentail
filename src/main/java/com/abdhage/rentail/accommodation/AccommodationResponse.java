package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.model.AccommodationAddress;
import com.abdhage.rentail.common.model.Facility;
import com.abdhage.rentail.common.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccommodationResponse {
    private UUID id;
    private String name;
    private Set<Image> images;
    private Set<Facility> facilities;
    private Boolean isVerified;
    private String description;
    private AccommodationAddress address;
    private Date createdAt;
    private Date updatedAt;
}
