package com.example.project3.repositories;

import com.example.project3.models.CarService;
import com.example.project3.models.CarServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServicePriceRepository extends JpaRepository<CarServicePrice, Integer> {

    void deleteByCarService(CarService carService);
}
