package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class ServiceCarService {
    private final CarServiceRepository carServiceRepository;

    @Autowired
    public ServiceCarService(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    public Optional<CarService> getServiceById(Integer id) {
        return carServiceRepository.findById(id);
    }

    public Optional<CarService> getServiceByIdWithAverageRating(Integer id) {
        Optional<CarService> carService = carServiceRepository.findById(id);
        if(carService.isPresent()) {
            carService.get().calcAverageRating();
        }
        return carService;
    }

    public Optional<CarService> getCarServiceByEmail(String email) {
        return carServiceRepository.findByEmail(email);
    }

    @Transactional
    public void deleteById(Integer carServiceId) {
        carServiceRepository.deleteById(carServiceId);
    }
}
