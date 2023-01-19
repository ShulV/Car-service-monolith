package com.example.project3.repositories;

import com.example.project3.models.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
    Optional<CarService> findByEmail(String username);
}
