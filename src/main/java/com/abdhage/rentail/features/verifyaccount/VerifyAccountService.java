package com.abdhage.rentail.features.verifyaccount;

public interface VerifyAccountService {
    public void requestVerifyAccount(String email);

    public void verifyAccount(String token);
}
