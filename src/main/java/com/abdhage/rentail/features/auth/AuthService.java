package com.abdhage.rentail.features.auth;

import com.abdhage.rentail.features.auth.dto.SignInDto;
import com.abdhage.rentail.features.auth.dto.SignUpDto;
import com.abdhage.rentail.features.token.model.Token;
import com.abdhage.rentail.features.user.model.User;

public interface AuthService {
    public Token signIn(SignInDto signInDto);

    public Token signUp(SignUpDto signUpDto);

    public User getLoggedUserInformations();
}
