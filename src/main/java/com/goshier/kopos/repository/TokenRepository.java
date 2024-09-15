package com.goshier.kopos.repository;

import com.goshier.kopos.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends CrudRepository<Token, UUID> {
    public Optional<Token> findByToken(String token);
}
