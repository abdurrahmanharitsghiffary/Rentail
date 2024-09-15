package com.goshier.kopos.response;

import com.goshier.kopos.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KosResponse {
    private UUID id;
    private String name;
    private List<String> images;
    private List<String> features;
    private Boolean isVerified;
    private String description;
    private AccommodationAddress accommodationAddress;
    private Date createdAt;
    private Date updatedAt;
}
