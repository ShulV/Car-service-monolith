package com.example.project3.controllers;

import com.example.project3.models.CarService;
import com.example.project3.models.CarServiceType;
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
import java.util.List;
import java.util.Map;

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

        List<CarServiceType> csTypes = serviceCarServiceType.getServiceTypes();
        model.addAttribute("carServiceTypes", csTypes);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("carService") @Valid CarService carService,
                                      BindingResult bindingResult, @RequestParam Map<String, String> allParams) throws IOException {
        carServiceValidator.validate(carService, bindingResult);


        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationService.register(carService, allParams);

        return "redirect:/auth/login";
    }
}
