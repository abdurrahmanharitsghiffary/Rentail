package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.model.AccommodationAddress;
import com.abdhage.rentail.common.model.Facility;
import com.abdhage.rentail.common.model.Image;
import com.abdhage.rentail.common.response.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccommodationResponse extends BaseResponse<UUID> {
    private String name;
    private Set<Image> images;
    private Set<Facility> facilities;
    private Boolean isVerified;
    private String description;
    private AccommodationAddress address;
}
