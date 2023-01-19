package com.example.project3.utils;

import com.example.project3.models.CarService;
import com.example.project3.services.ServiceCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

@Component
public class CarServiceValidator implements Validator {

    private final ServiceCarService serviceCarService;

    @Autowired
    public CarServiceValidator(ServiceCarService serviceCarService) {
        this.serviceCarService = serviceCarService;
    }


    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CarService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarService carService = (CarService) target;
        boolean isExistingLogin = serviceCarService.getCarServiceByEmail(carService.getEmail()).isPresent();

        if(isExistingLogin) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("login", "", "Логин (email) уже существует");
        }
    }
}
