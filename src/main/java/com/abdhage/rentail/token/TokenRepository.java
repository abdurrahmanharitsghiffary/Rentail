package com.abdhage.rentail.token;

import com.abdhage.rentail.token.model.Token;
import com.abdhage.rentail.token.model.TokenType;
import com.abdhage.rentail.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    public Optional<Token> findByToken(String token);

    public long deleteByTypeAndUser(TokenType type, User user);
}
