package com.example.project3.repositories;

import com.example.project3.models.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Long> {

}
