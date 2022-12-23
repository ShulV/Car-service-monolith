package com.example.project3.services;

import org.apache.commons.lang3.StringUtils;
import com.example.project3.models.CarService;
import com.example.project3.models.RepairRequest;
import com.example.project3.models.CarServiceType;
import com.example.project3.repositories.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class RepairRequestService {

    private final ServiceCarService serviceCarService;
    private final ServiceCarServiceType serviceCarServiceType;
    private final RepairRequestRepository repairRequestRepository;

    @Autowired
    public RepairRequestService(ServiceCarService serviceCarService, ServiceCarServiceType serviceCarServiceType, RepairRequestRepository repairRequestRepository) {
        this.serviceCarService = serviceCarService;
        this.serviceCarServiceType = serviceCarServiceType;
        this.repairRequestRepository = repairRequestRepository;
    }

    @Transactional
    public void saveRepairRequest(Integer serviceId, Integer serviceTypeId, RepairRequest repairRequest) {
        Optional<CarService> carService = serviceCarService.getServiceById(serviceId);
        CarServiceType carServiceType = serviceCarServiceType.getServiceTypeById(serviceTypeId);

        if(carService.isPresent() && carServiceType != null) {
            repairRequest.setCarService(carService.get());
            repairRequest.setServiceType(carServiceType);

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

    public List<RepairRequest> getAll(String colName) {
        return repairRequestRepository.findAll(Sort.by(Sort.Direction.DESC, colName));
    }

    public List<RepairRequest> getAll() {
        List<RepairRequest> repairRequests = repairRequestRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        for (RepairRequest rr : repairRequests) {
            rr.setServiceName(rr.getCarService().getName());
            rr.setServiceTypeName(rr.getServiceType().getName());
        }
        return repairRequests;
    }

    @Transactional
    public void changeStatus(Integer repairReqId) {
        Optional<RepairRequest> repairRequest = repairRequestRepository.findById(repairReqId);
        if (repairRequest.isPresent()) {
            repairRequest.get().setAccepted(!repairRequest.get().getAccepted());
        }
    }
}
