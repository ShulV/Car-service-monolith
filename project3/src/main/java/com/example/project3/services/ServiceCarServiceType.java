package com.example.project3.services;

import com.example.project3.models.CarServicePrice;
import com.example.project3.models.CarServiceType;
import com.example.project3.repositories.CarServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class ServiceCarServiceType {
    private final CarServiceTypeRepository carServiceTypeRepository;

    @Autowired
    public ServiceCarServiceType(CarServiceTypeRepository carServiceTypeRepository) {
        this.carServiceTypeRepository = carServiceTypeRepository;
    }

    public CarServiceType getServiceTypeById(Integer id) {
        return carServiceTypeRepository.findById(id).orElse(null);
    }

    public Optional<CarServiceType> findById(Integer id) {
        return carServiceTypeRepository.findById(id);
    }

    public List<CarServiceType> getServiceTypes() {
        return carServiceTypeRepository.findAll();
    }
}
