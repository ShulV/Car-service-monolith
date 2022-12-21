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

    //получение главной страницы
    @GetMapping()
        public String getMainPage() {
            return "index";
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
//            Review review = new Review();
////            review.setCarService(carService.get());
//            model.addAttribute("review", review);
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

    //получение страницы создания заявки
    @GetMapping("/add-repair-request/{serviceId}/{serviceTypeId}")
    public String getPageCreatingRepairRequest(@PathVariable("serviceId") Integer serviceId,
                                               @PathVariable("serviceTypeId") Integer serviceTypeId,
                                               Model model) {
//        @ModelAttribute("repairRequest") RepairRequest repairRequest
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setServiceType(serviceServiceType.getServiceTypeById(serviceTypeId));
        model.addAttribute("repairRequest", repairRequest);
        return "repair-request";

    }

    @PostMapping("/add-repair-request/{serviceId}/{serviceTypeId}")
    public String CreateRepairRequest(@PathVariable("serviceId") Integer serviceId,
                                      @PathVariable("serviceTypeId") Integer serviceTypeId,
                                      @ModelAttribute("repairRequest") RepairRequest repairRequest,
                                      BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "service";
        }

        repairRequestService.saveRepairRequest(serviceId, serviceTypeId, repairRequest);
        return "redirect:/car-service/" + serviceTypeId + '/' + serviceId;
    }


}
