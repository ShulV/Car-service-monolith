package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {
    private final CarServiceRepository carServiceRepository;

    private final ServiceCarServicePrice serviceCarServicePrice;
    private final PasswordEncoder passwordEncoder;

    //подстрока в значении атрибута 'name' (например <input type="checkbox" name="csType1">)
    //нужна для извлечения id типа сервиса (csType1 - id=1)
    private static final String CHECKBOX_INPUT_SUBSTR = "csType";

    @Autowired
    public RegistrationService(CarServiceRepository carServiceRepository, ServiceCarServicePrice serviceCarServicePrice, PasswordEncoder passwordEncoder) {
        this.carServiceRepository = carServiceRepository;
        this.serviceCarServicePrice = serviceCarServicePrice;
        this.passwordEncoder = passwordEncoder;
    }

    private List<Integer> getIdsOfServiceTypes(Map<String, String> allReqParams) {
        List<Integer> idList = new ArrayList<>();
        for (Map.Entry<String, String> param : allReqParams.entrySet()
             ) {
            if (param.getKey().contains(CHECKBOX_INPUT_SUBSTR)) {
                //удаляем из строки буквы, оставляя только число
                String numberInString = param.getKey().replaceAll(CHECKBOX_INPUT_SUBSTR, "");
                idList.add(Integer.valueOf(numberInString));
            }
        }
        return idList;
    }

    @Transactional
    public void register(CarService carService, Map<String, String> allReqParams) {
        String encodedPassword = passwordEncoder.encode(carService.getPassword());
        carService.setPassword(encodedPassword);
        List<Integer> serviceTypeIdList = getIdsOfServiceTypes(allReqParams);

        for (Integer id: serviceTypeIdList
             ) {
            //TODO masterTypeService.save(carServiceId, carServiceTypeId, price)
            //TODO создать прайсы в html
            //TODO создать метод вытягивающий список прайсов, возвращающий мапу (typeId, price)
            //TODO getIdsOfServiceTypes удалить
            //TODO создать сервис, репозиторий для master_type
            //TODO разработать save() для сервиса master_type
        }
//        carServiceRepository.save(carService);
    }
}
