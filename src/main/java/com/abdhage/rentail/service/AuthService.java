package com.abdhage.rentail.service;

import com.abdhage.rentail.dtos.auth.SignInDto;
import com.abdhage.rentail.dtos.auth.SignUpDto;
import com.abdhage.rentail.model.Token;
import com.abdhage.rentail.model.User;

public interface AuthService {
    public Token signIn(SignInDto signInDto);

    public Token signUp(SignUpDto signUpDto);

    public User getLoggedUserInformations();
}
