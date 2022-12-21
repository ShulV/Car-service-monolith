package com.example.project3.controllers;

import com.example.project3.models.RepairRequest;
import com.example.project3.services.RepairRequestService;
import com.example.project3.services.ServiceServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RepairRequestsController {
    private final ServiceServiceType serviceServiceType;
    private final RepairRequestService repairRequestService;

    @Autowired
    public RepairRequestsController(ServiceServiceType serviceServiceType, RepairRequestService repairRequestService) {
        this.serviceServiceType = serviceServiceType;
        this.repairRequestService = repairRequestService;
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
