package com.abdhage.rentail.verifyaccount;

public interface VerifyAccountService {
    public void requestVerifyAccount(String email);

    public void verifyAccount(String token);
}
