package com.example.project3.controllers;


import com.example.project3.models.CarService;
import com.example.project3.models.Review;
import com.example.project3.models.ServicePrice;
import com.example.project3.models.ServiceType;
import com.example.project3.services.ReviewService;
import com.example.project3.services.ServiceService;
import com.example.project3.services.ServiceServicePrice;
import com.example.project3.services.ServiceServiceType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Autowired
    public MastersController(ServiceService serviceService,
                             ServiceServicePrice serviceServicePrice,
                             ServiceServiceType serviceServiceType, ReviewService reviewService) {
        this.serviceService = serviceService;
        this.serviceServicePrice = serviceServicePrice;
        this.serviceServiceType = serviceServiceType;
        this.reviewService = reviewService;
    }

    @GetMapping()
        public String getMainPage() {
            return "index";
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

    @GetMapping("/car-service/{serviceId}")
    public String getServicePage(@PathVariable Integer serviceId, Model model) {
        Optional<CarService> carService = serviceService.getServiceById(serviceId);


        if(carService.isPresent()) {
            Review review = new Review();
            review.setCarService(carService.get());
            model.addAttribute("review", review);
            model.addAttribute("carService", carService.get());
        }

        return "service";
        }

    @PostMapping("/add-review/{serviceId}")
    public String createReview(@PathVariable Integer serviceId, @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "service";
        }
        reviewService.saveReviewForCarService(serviceId, review);
        return "redirect:/car-service/" + serviceId;
    }
}
