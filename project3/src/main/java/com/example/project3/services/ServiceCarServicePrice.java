package com.example.project3.services;

import com.example.project3.models.CarServicePrice;
import com.example.project3.repositories.CarServicePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class ServiceCarServicePrice {

    private final CarServicePriceRepository carServicePriceRepository;

    @Autowired
    public ServiceCarServicePrice(CarServicePriceRepository carServicePriceRepository) {
        this.carServicePriceRepository = carServicePriceRepository;
    }

    @Transactional
    public void save(CarServicePrice carServicePrice) {
        carServicePriceRepository.save(carServicePrice);
    }

    public Optional<CarServicePrice> findById(Integer id) {
        return carServicePriceRepository.findById(id);
    }
}
