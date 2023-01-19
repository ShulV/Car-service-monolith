package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final CarServiceRepository carServiceRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(CarServiceRepository carServiceRepository, PasswordEncoder passwordEncoder) {
        this.carServiceRepository = carServiceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(CarService carService) {
        String encodedPassword = passwordEncoder.encode(carService.getPassword());
        carService.setPassword(encodedPassword);
        carServiceRepository.save(carService);
    }
}
