package com.example.project3.controllers;

import com.example.project3.models.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    //получение главной страницы
    @GetMapping()
    public String getMainPage() {

        return "index";
    }
}
