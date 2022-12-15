package com.example.project2.services;

import com.example.project2.models.ServicePrice;
import com.example.project2.repositories.ServicePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class ServiceServicePrice {

    private final ServicePriceRepository servicePriceRepository;

    @Autowired
    public ServiceServicePrice(ServicePriceRepository servicePriceRepository) {

        this.servicePriceRepository = servicePriceRepository;
    }

}
