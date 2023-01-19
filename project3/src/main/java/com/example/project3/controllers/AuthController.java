package com.example.project3.controllers;

import com.example.project3.models.CarService;
import com.example.project3.services.RegistrationService;
import com.example.project3.services.ServiceCarServiceType;
import com.example.project3.utils.CarServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final CarServiceValidator carServiceValidator;
    private final RegistrationService registrationService;

    private final ServiceCarServiceType serviceCarServiceType;

    @Autowired
    public AuthController(CarServiceValidator carServiceValidator, RegistrationService registrationService, ServiceCarServiceType serviceCarServiceType) {
        this.carServiceValidator = carServiceValidator;
        this.registrationService = registrationService;
        this.serviceCarServiceType = serviceCarServiceType;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("carService", new CarService());
        model.addAttribute("carServiceTypes", serviceCarServiceType.getServiceTypes());
        model.addAttribute("features", new ArrayList<>());

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("carService") @Valid CarService carService,
                                      BindingResult bindingResult) throws IOException {
        carServiceValidator.validate(carService, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(carService);
        return "redirect:/auth/login";
    }
}
