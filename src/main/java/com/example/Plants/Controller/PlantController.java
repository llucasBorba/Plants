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

    @PutMapping("/plants/{id}")
    public Plant updatePlant(@PathVariable("id") Integer id, @RequestBody Plant p){
        Optional<Plant> optionalPlant = plantRepository.findById(id);
        if(optionalPlant.isEmpty()){return null;}

        Plant plantToUpdate = optionalPlant.get();

        if(p.getName() != null){plantToUpdate.setName(p.getName());}
        if(p.getHasFruit() != null){plantToUpdate.setHasFruit(p.getHasFruit());}
        if(p.getQuantity() != null){plantToUpdate.setQuantity(p.getQuantity());}
        if(p.getWateringFrequency() != null){plantToUpdate.setWateringFrequency(p.getWateringFrequency());}

        Plant updatedPlant = plantRepository.save(plantToUpdate);

        return updatedPlant;

    }
    @DeleteMapping("/plants/{id}")
    public Plant deletePLant(@PathVariable("id") Integer id){
        Optional<Plant> optionalPlant = plantRepository.findById(id);
        if(optionalPlant.isEmpty()){return null;}

        Plant plantToDelete = optionalPlant.get();
        plantRepository.delete(plantToDelete);
        return plantToDelete;
    }
}
