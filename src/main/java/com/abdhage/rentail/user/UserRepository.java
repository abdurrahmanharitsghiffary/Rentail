package com.abdhage.rentail.user;

import com.abdhage.rentail.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    List<User> findByInformLatestNewsTrue();

    List<User> findByInformRecommendationsTrue();

    public Optional<User> findTopByUsernameOrEmail(String username, String email);
}
