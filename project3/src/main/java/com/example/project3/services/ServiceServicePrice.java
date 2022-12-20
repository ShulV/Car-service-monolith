package com.example.project3.services;

import com.example.project3.repositories.ServicePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ServiceServicePrice {

    private final ServicePriceRepository servicePriceRepository;

    @Autowired
    public ServiceServicePrice(ServicePriceRepository servicePriceRepository) {

        this.servicePriceRepository = servicePriceRepository;
    }

}
