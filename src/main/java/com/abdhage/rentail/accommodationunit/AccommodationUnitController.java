package com.abdhage.rentail.accommodationunit;

import com.abdhage.rentail.accommodationunit.dto.CreateUnitDTO;
import com.abdhage.rentail.accommodationunit.dto.UpdateUnitDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("units")
public class AccommodationUnitController {

    private final AccommodationUnitService accommodationUnitService;

    public AccommodationUnitController(AccommodationUnitService accommodationUnitService) {
        this.accommodationUnitService = accommodationUnitService;
    }

    @GetMapping("{id}")
    public AccommodationUnitResponse findOne(@PathVariable UUID id) {
        return accommodationUnitService.findOne(id);
    }

    @GetMapping
    public List<AccommodationUnitResponse> findAll() {
        return accommodationUnitService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccommodationUnitResponse create(@Valid @RequestBody CreateUnitDTO dto) {
        return accommodationUnitService.create(dto);
    }

    @PatchMapping("{id}")
    public AccommodationUnitResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateUnitDTO dto) {
        return accommodationUnitService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable UUID id) {
        accommodationUnitService.destroy(id);
    }
}

