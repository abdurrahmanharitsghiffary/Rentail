package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.accommodation.dto.UpdateAccommodationDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<AccommodationResponse> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public AccommodationResponse findOne(@PathVariable UUID id) {
        return accommodationService.findOne(id);
    }

    @PostMapping()
    public AccommodationResponse create(@Valid @RequestBody CreateAccommodationDTO dto) {
        return accommodationService.create(dto);
    }


    @PatchMapping("/{id}")
    public AccommodationResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateAccommodationDTO dto) {
        return accommodationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable UUID id) {
        accommodationService.destroy(id);
    }
}
