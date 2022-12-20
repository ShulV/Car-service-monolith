package com.example.project3.services;

import com.example.project3.models.ServiceType;
import com.example.project3.repositories.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ServiceServiceType {
    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceServiceType(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public ServiceType getServiceTypeById(Integer id) {
        return serviceTypeRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
