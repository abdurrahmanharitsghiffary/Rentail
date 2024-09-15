package com.abdhage.rentail.repository;

import com.abdhage.rentail.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KosRepository extends JpaRepository<Accommodation, UUID> {

    public Optional<Accommodation> findByName(String name);

}
