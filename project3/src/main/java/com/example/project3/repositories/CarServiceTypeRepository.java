package com.example.project3.repositories;

import com.example.project3.models.CarServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceTypeRepository extends JpaRepository<CarServiceType, Long> {
}
