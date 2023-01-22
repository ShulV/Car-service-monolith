package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.models.CarServicePrice;
import com.example.project3.models.CarServiceType;
import com.example.project3.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RegistrationService {
    private final CarServiceRepository carServiceRepository;

    private final ServiceCarServicePrice serviceCarServicePrice;

    private final ServiceCarServiceType serviceCarServiceType;
    private final PasswordEncoder passwordEncoder;

    //подстрока в значении атрибута 'name' (например <input type="checkbox" name="csType1">)
    //нужна для извлечения id типа сервиса (csType1 - id=1)
    private static final String CHECKBOX_INPUT_SUBSTR = "csType";
    private static final String PRICE_INPUT_SUBSTR = "csPrice";

    @Autowired
    public RegistrationService(CarServiceRepository carServiceRepository, ServiceCarServicePrice serviceCarServicePrice, ServiceCarServiceType serviceCarServiceType, PasswordEncoder passwordEncoder) {
        this.carServiceRepository = carServiceRepository;
        this.serviceCarServicePrice = serviceCarServicePrice;
        this.serviceCarServiceType = serviceCarServiceType;
        this.passwordEncoder = passwordEncoder;
    }

    private Map<Integer, Integer> getPairsOfCarServiceTypeAndPrice(Map<String, String> allReqParams) {
        Map<Integer, Integer> typePriceHashMap = new HashMap<>();


        for (Map.Entry<String, String> param: allReqParams.entrySet()
             ) {
            if (param.getKey().contains(PRICE_INPUT_SUBSTR) && !param.getValue().isEmpty()) {
                Integer csTypeId = Integer.valueOf(param.getKey().replaceAll(PRICE_INPUT_SUBSTR, ""));
                Integer price = Integer.valueOf(param.getValue());
                typePriceHashMap.put(csTypeId, price);
            }
        }
        ;
        return typePriceHashMap;
    }

    @Transactional
    public void register(CarService carService, Map<String, String> allReqParams) {
        String encodedPassword = passwordEncoder.encode(carService.getPassword());
        carService.setPassword(encodedPassword);
        carService.setRole("ROLE_USER");

        Map<Integer, Integer> typeIdPriceHashMap = getPairsOfCarServiceTypeAndPrice(allReqParams);
        for (Map.Entry<Integer, Integer> typeIdPrice: typeIdPriceHashMap.entrySet()
             ) {
            CarServicePrice carServicePrice = new CarServicePrice();
            Integer key = typeIdPrice.getKey();
            Optional<CarServiceType> carServiceType = serviceCarServiceType.findById(key);
            if(carServiceType.isPresent()) {
                carServicePrice.setPrice(typeIdPrice.getValue());
                carServicePrice.setServiceType(carServiceType.get());
                carServicePrice.setCarService(carService);
                serviceCarServicePrice.save(carServicePrice);
            }
            //TODO лучше сохранять пакетом
        }
        carServiceRepository.save(carService);
    }
}
