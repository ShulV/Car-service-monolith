package com.example.project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MastersController {


    @GetMapping()
        public String hello() {
            System.out.println("Lol");
            return "hello";
        }
}
