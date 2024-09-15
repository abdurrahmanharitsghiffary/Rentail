package com.abdhage.rentail.controller;

import com.abdhage.rentail.response.UserResponse;
import com.abdhage.rentail.service.MeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class MeController {

    private final MeService meService;

    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping
    public UserResponse getUserDetails() {
        return meService.getMyInformations();
    }
}
