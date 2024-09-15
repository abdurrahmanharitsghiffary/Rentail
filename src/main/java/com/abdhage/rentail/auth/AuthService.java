package com.abdhage.rentail.auth;

import com.abdhage.rentail.auth.dto.SignInDto;
import com.abdhage.rentail.auth.dto.SignUpDto;
import com.abdhage.rentail.token.model.Token;
import com.abdhage.rentail.user.model.User;

public interface AuthService {
    public Token signIn(SignInDto signInDto);

    public Token signUp(SignUpDto signUpDto);

    public User getLoggedUserInformations();
}
