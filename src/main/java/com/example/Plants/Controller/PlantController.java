package com.example.Plants.Controller;

import com.example.Plants.Repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;


    @GetMapping("/Test")
    public String Test(){
        return "Test";
    }
}
