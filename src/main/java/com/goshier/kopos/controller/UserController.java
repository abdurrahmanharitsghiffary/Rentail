package com.goshier.kopos.controller;

import com.goshier.kopos.service.UserService;
import com.goshier.kopos.dtos.user.CreateUserDto;
import com.goshier.kopos.dtos.user.UpdateUserDto;
import com.goshier.kopos.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findOne(@PathVariable UUID id) {
        return userService.findOne(id);
    }

    @PostMapping()
    public UserResponse create(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateUserDto updatedUserDto) {
        return userService.update(id, updatedUserDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable UUID id) {
        userService.destroy(id);
    }
}
