package com.abdhage.rentail.features.token;

import com.abdhage.rentail.features.token.model.Token;
import com.abdhage.rentail.features.token.model.TokenType;
import com.abdhage.rentail.features.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends CrudRepository<Token, UUID> {
    public Optional<Token> findByToken(String token);

    long deleteByTypeAndUser(TokenType type, User user);
}
