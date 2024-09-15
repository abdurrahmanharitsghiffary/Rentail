package com.abdhage.rentail.service.checker.impl;

import com.abdhage.rentail.exception.KosNotFoundException;
import com.abdhage.rentail.exception.KosNameMustUniqueException;
import com.abdhage.rentail.model.Accommodation;
import com.abdhage.rentail.repository.KosRepository;
import com.abdhage.rentail.service.checker.KosChecker;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KosCheckerImpl implements KosChecker {

    private final KosRepository kosRepository;

    public KosCheckerImpl(KosRepository kosRepository) {
        this.kosRepository = kosRepository;
    }

    public void checkKosNameMustUnique(String name) {
        kosRepository.findByName(name).ifPresent((kos) -> {
            throw new KosNameMustUniqueException();
        });
    }

    @Override
    public Accommodation checkKosMustExists(UUID id) {
        return kosRepository.findById(id).orElseThrow(KosNotFoundException::new);
    }
}
