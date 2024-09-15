package com.goshier.kopos.service;

import com.goshier.kopos.dtos.auth.SignInDto;
import com.goshier.kopos.dtos.auth.SignUpDto;
import com.goshier.kopos.model.Token;
import com.goshier.kopos.model.User;

public interface AuthService {
    public Token signIn(SignInDto signInDto);

    public Token signUp(SignUpDto signUpDto);

    public User getLoggedUserInformations();
}
