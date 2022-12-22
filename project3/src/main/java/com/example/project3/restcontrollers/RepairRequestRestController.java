package com.example.project3.restcontrollers;

import com.example.project3.models.RepairRequest;
import com.example.project3.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair-request")
public class RepairRequestRestController {
    private final RepairRequestService repairRequestService;

    @Autowired
    public RepairRequestRestController(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @GetMapping("/get-all")
    public List<RepairRequest> getAllRepairRequests() {
        List<RepairRequest> repairRequests = repairRequestService.getAll();
        return repairRequests;
    }
}
