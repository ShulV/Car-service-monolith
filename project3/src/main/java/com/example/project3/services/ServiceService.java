package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<CarService> getServiceById(Integer id) {
        return serviceRepository.findById(Long.valueOf(id));
    }
}
