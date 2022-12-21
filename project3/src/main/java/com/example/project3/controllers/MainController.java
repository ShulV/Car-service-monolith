package com.example.project3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //получение главной страницы
    @GetMapping()
    public String getMainPage() {
        return "index";
    }
}
