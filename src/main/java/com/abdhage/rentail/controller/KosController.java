package com.abdhage.rentail.controller;

import com.abdhage.rentail.dtos.kos.CreateKosDto;
import com.abdhage.rentail.dtos.kos.UpdateKosDto;
import com.abdhage.rentail.response.KosResponse;
import com.abdhage.rentail.service.KosService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kos")
public class KosController {

    private final KosService kosService;

    public KosController(KosService kosService) {
        this.kosService = kosService;
    }

    @GetMapping
    public List<KosResponse> findAll() {
        return kosService.findAll();
    }

    @GetMapping("/{id}")
    public KosResponse findOne(@PathVariable UUID id) {
        return kosService.findOne(id);
    }

    @PostMapping()
    public KosResponse create(@Valid @RequestBody CreateKosDto dto) {
        return kosService.create(dto, UUID.fromString("2b96e3f5-ea54-4a74-91fd-4a00701ea552"));
    }


    @PatchMapping("/{id}")
    public KosResponse update(Principal principal, @PathVariable UUID id, @Valid @RequestBody UpdateKosDto dto) {
        System.out.println(principal + " Principal");
        System.out.println(principal.getName() + " Principal Name");

        return kosService.update(id, new UUID(7, 7), dto);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable UUID id) {
        kosService.destroy(id, new UUID(7, 7));
    }
}
