package com.example.project3.services;

import com.example.project3.repositories.CarServicePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ServiceCarServicePrice {

    private final CarServicePriceRepository carServicePriceRepository;

    @Autowired
    public ServiceCarServicePrice(CarServicePriceRepository carServicePriceRepository) {

        this.carServicePriceRepository = carServicePriceRepository;
    }

}
