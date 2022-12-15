package com.example.project2.repositories;

import com.example.project2.models.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Long> {

}
