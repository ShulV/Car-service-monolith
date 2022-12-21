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
@RequestMapping("/car-service")
public class CarServicesController {
    private final ServiceCarService serviceCarService;
    private final ServiceCarServicePrice serviceCarServicePrice;
    private final ServiceCarServiceType serviceCarServiceType;
    private final ReviewService reviewService;

    private final RepairRequestService repairRequestService;

    @Autowired
    public CarServicesController(ServiceCarService serviceCarService,
                                 ServiceCarServicePrice serviceCarServicePrice,
                                 ServiceCarServiceType serviceCarServiceType, ReviewService reviewService, RepairRequestService repairRequestService) {
        this.serviceCarService = serviceCarService;
        this.serviceCarServicePrice = serviceCarServicePrice;
        this.serviceCarServiceType = serviceCarServiceType;
        this.reviewService = reviewService;
        this.repairRequestService = repairRequestService;
    }

    //получение списка автомастерских
    @GetMapping("/all-by-type/{carServiceTypeId}")
    public String getListServicesPage(@PathVariable Integer carServiceTypeId, Model model) {
        CarServiceType carServiceType = serviceCarServiceType.getServiceTypeById(carServiceTypeId);
        if (carServiceType != null) {
            List<CarServicePrice> carServicePriceList = carServiceType.getServicePrices();
            model.addAttribute("carServicePrices", carServicePriceList);
            model.addAttribute("carServiceTypeId", carServiceTypeId);
        }
        return "list-services";
    }

    //получение определенного сервиса
    @GetMapping("/{carServiceTypeId}/{carServiceId}")
    public String getServicePage(@PathVariable("carServiceTypeId") Integer carServiceTypeId,
                                 @PathVariable("carServiceId") Integer carServiceId,
                                 Model model,
                                 @ModelAttribute(name = "review") Review review) {
        Optional<CarService> carService = serviceCarService.getServiceByIdWithAverageRating(carServiceId);


        if(carService.isPresent()) {
            model.addAttribute("carService", carService.get());
        }
        return "service";
    }
}
