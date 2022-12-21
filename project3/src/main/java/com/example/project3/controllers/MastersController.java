package com.example.project3.controllers;


import com.example.project3.models.*;
import com.example.project3.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MastersController {
    private final ServiceService serviceService;
    private final ServiceServicePrice serviceServicePrice;
    private final ServiceServiceType serviceServiceType;
    private final ReviewService reviewService;

    private final RepairRequestService repairRequestService;

    @Autowired
    public MastersController(ServiceService serviceService,
                             ServiceServicePrice serviceServicePrice,
                             ServiceServiceType serviceServiceType, ReviewService reviewService, RepairRequestService repairRequestService) {
        this.serviceService = serviceService;
        this.serviceServicePrice = serviceServicePrice;
        this.serviceServiceType = serviceServiceType;
        this.reviewService = reviewService;
        this.repairRequestService = repairRequestService;
    }

    //получение списка автомастерских
    @GetMapping("/list-services/{serviceTypeId}")
    public String getListServicesPage(@PathVariable Integer serviceTypeId, Model model) {
        ServiceType serviceType = serviceServiceType.getServiceTypeById(serviceTypeId);
        if (serviceType != null) {
            List<ServicePrice> servicePriceList = serviceType.getServicePrices();
            model.addAttribute("servicePrices", servicePriceList);
            model.addAttribute("serviceTypeId", serviceTypeId);
        }
        return "list-services";
    }

    //получение определенного сервиса
    @GetMapping("/car-service/{serviceTypeId}/{serviceId}")
    public String getServicePage(@PathVariable("serviceTypeId") Integer serviceTypeId,
                                 @PathVariable("serviceId") Integer serviceId,
                                 Model model,
                                 @ModelAttribute(name = "review") Review review) {
        Optional<CarService> carService = serviceService.getServiceById(serviceId);


        if(carService.isPresent()) {
            model.addAttribute("carService", carService.get());
        }
        return "service";
    }

    //добавление отзыва
    @PostMapping("/add-review/{serviceTypeId}/{serviceId}")
    public String createReview(@PathVariable("serviceTypeId") Integer serviceTypeId,
                               @PathVariable("serviceId") Integer serviceId,
                               @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "service";
        }
        reviewService.saveReviewForCarService(serviceId, review);
        return "redirect:/car-service/" + serviceTypeId + '/' + serviceId;
    }




}
