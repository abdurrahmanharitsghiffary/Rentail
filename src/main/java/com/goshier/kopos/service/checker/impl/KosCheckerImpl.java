package com.goshier.kopos.service.checker.impl;

import com.goshier.kopos.exception.KosNotFoundException;
import com.goshier.kopos.exception.KosNameMustUniqueException;
import com.goshier.kopos.model.Accommodation;
import com.goshier.kopos.repository.KosRepository;
import com.goshier.kopos.service.checker.KosChecker;
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
