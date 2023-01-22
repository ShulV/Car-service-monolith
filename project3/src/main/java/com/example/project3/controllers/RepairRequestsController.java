package com.example.project3.controllers;

import com.example.project3.models.RepairRequest;
import com.example.project3.services.RepairRequestService;
import com.example.project3.services.ServiceCarServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/repair-request")
public class RepairRequestsController {
    private final ServiceCarServiceType serviceCarServiceType;
    private final RepairRequestService repairRequestService;

    @Autowired
    public RepairRequestsController(ServiceCarServiceType serviceCarServiceType, RepairRequestService repairRequestService) {
        this.serviceCarServiceType = serviceCarServiceType;
        this.repairRequestService = repairRequestService;
    }

    //получение страницы со всеми заявками
    @GetMapping("/all")
    public String getAllRepairRequestPage(Model model) {
        model.addAttribute("repairRequests", repairRequestService.getAll());
        return "repair-request-list";
    }

    //получение страницы создания заявки
    @GetMapping("/add/{carServiceId}/{carServiceTypeId}")
    public String getPageCreatingRepairRequest(@PathVariable("carServiceId") Integer carServiceId,
                                               @PathVariable("carServiceTypeId") Integer carServiceTypeId,
                                               Model model) {
//        @ModelAttribute("repairRequest") RepairRequest repairRequest
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setServiceType(serviceCarServiceType.getServiceTypeById(carServiceTypeId));
        model.addAttribute("repairRequest", repairRequest);
        return "repair-request";

    }

    @PostMapping("/add/{carServiceId}/{carServiceTypeId}")
    public String createRepairRequest(@PathVariable("carServiceId") Integer carServiceId,
                                      @PathVariable("carServiceTypeId") Integer carServiceTypeId,
                                      @ModelAttribute("repairRequest") RepairRequest repairRequest,
                                      BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "service";
        }
        repairRequest.setAccepted(false);
        repairRequestService.saveRepairRequest(carServiceId, carServiceTypeId, repairRequest);
        return "redirect:/car-service/" + carServiceTypeId + '/' + carServiceId;
    }
}
