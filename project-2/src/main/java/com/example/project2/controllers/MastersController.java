package com.example.project2.controllers;


import com.example.project2.models.ServicePrice;
import com.example.project2.models.ServiceType;
import com.example.project2.services.ServiceService;
import com.example.project2.services.ServiceServicePrice;
import com.example.project2.services.ServiceServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MastersController {
    private final ServiceService serviceService;
    private final ServiceServicePrice serviceServicePrice;
    private final ServiceServiceType serviceServiceType;

    @Autowired
    public MastersController(ServiceService serviceService,
                             ServiceServicePrice serviceServicePrice,
                             ServiceServiceType serviceServiceType) {
        this.serviceService = serviceService;
        this.serviceServicePrice = serviceServicePrice;
        this.serviceServiceType = serviceServiceType;
    }

    @GetMapping()
        public String getMainPage() {
            return "hello";
        }

    @GetMapping("/list-services/{id}")
    public String getListServicesPage(@PathVariable Integer id, Model model) {
        ServiceType serviceType = serviceServiceType.getServiceTypeById(id);
        if (serviceType != null) {
            List<ServicePrice> servicePriceList = serviceType.getServicePrices();
            model.addAttribute("servicePrices", servicePriceList);
        }


        return "list-services";
    }
}
