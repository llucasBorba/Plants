package com.example.Plants.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantsController {

    @GetMapping("/Test")
    public String Test(){
        return "Test";
    }
}
