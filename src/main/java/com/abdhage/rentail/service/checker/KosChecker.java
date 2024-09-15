package com.abdhage.rentail.service.checker;

import com.abdhage.rentail.model.Accommodation;

import java.util.UUID;

public interface KosChecker {
    public Accommodation checkKosMustExists(UUID id);

    public void checkKosNameMustUnique(String name);
}
