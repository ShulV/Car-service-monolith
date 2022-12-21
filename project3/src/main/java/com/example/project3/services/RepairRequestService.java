package com.example.project3.services;

import org.apache.commons.lang3.StringUtils;
import com.example.project3.models.CarService;
import com.example.project3.models.RepairRequest;
import com.example.project3.models.ServiceType;
import com.example.project3.repositories.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class RepairRequestService {

    private final ServiceService serviceService;
    private final ServiceServiceType serviceServiceType;
    private final RepairRequestRepository repairRequestRepository;

    @Autowired
    public RepairRequestService(ServiceService serviceService, ServiceServiceType serviceServiceType, RepairRequestRepository repairRequestRepository) {
        this.serviceService = serviceService;
        this.serviceServiceType = serviceServiceType;
        this.repairRequestRepository = repairRequestRepository;
    }

    @Transactional
    public void saveRepairRequest(Integer serviceId, Integer serviceTypeId, RepairRequest repairRequest) {
        Optional<CarService> carService = serviceService.getServiceById(serviceId);
        ServiceType serviceType = serviceServiceType.getServiceTypeById(serviceTypeId);

        if(carService.isPresent() && serviceType != null) {
            repairRequest.setCarService(carService.get());
            repairRequest.setServiceType(serviceType);

            repairRequest.setDateRequest(LocalDate.now());

            String timeFromInput = repairRequest.getDateTimeWorkFromInput();
            if (StringUtils.countMatches(timeFromInput, ":") == 1) {
                repairRequest.setDateTimeWorkFromInput(timeFromInput + ":00");
            }
            repairRequest.setDateTimeWork(
                    Timestamp.valueOf(repairRequest.getDateTimeWorkFromInput().replace("T", " ")));

            repairRequestRepository.save(repairRequest);
        }
    }
}
