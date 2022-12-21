package com.example.project3.services;

import com.example.project3.models.CarServiceType;
import com.example.project3.repositories.CarServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ServiceCarServiceType {
    private final CarServiceTypeRepository carServiceTypeRepository;

    @Autowired
    public ServiceCarServiceType(CarServiceTypeRepository carServiceTypeRepository) {
        this.carServiceTypeRepository = carServiceTypeRepository;
    }

    public CarServiceType getServiceTypeById(Integer id) {
        return carServiceTypeRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
