package com.example.Plants.Controller;

import com.example.Plants.Repository.PlantRepository;
import com.example.Plants.entites.Plant;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PlantController {

    private final PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    @GetMapping("/plants")
    public Iterable<Plant> getAllPlants() {
        return this.plantRepository.findAll();
    }
    @GetMapping("/plants/{id}")
    public Optional<Plant> getPlantById(@PathVariable("id") Integer id){
        return plantRepository.findById(id);
    }

    @PostMapping("/plants")
    public Plant createNewPlant(@RequestBody Plant plant){
        Plant newPlant;
        newPlant = plantRepository.save(plant);
        return newPlant;
    }
}
