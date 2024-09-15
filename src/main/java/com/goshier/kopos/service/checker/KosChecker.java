package com.goshier.kopos.service.checker;

import com.goshier.kopos.model.Accommodation;

import java.util.UUID;

public interface KosChecker {
    public Accommodation checkKosMustExists(UUID id);

    public void checkKosNameMustUnique(String name);
}
