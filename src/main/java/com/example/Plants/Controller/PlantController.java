package com.example.Plants.Controller;

import com.example.Plants.Repository.PlantRepository;
import com.example.Plants.entites.Plant;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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


        return plantRepository.save(plantToUpdate);

    }
    @DeleteMapping("/plants/{id}")
    public Plant deletePlant(@PathVariable("id") Integer id){
        Optional<Plant> optionalPlant = plantRepository.findById(id);
        if(optionalPlant.isEmpty()){return null;}

        Plant plantToDelete = optionalPlant.get();
        plantRepository.delete(plantToDelete);
        return plantToDelete;
    }

    @GetMapping("/plants/search")
    public List<Plant> searchPlants(
            @RequestParam(name = "hasFruit", required=false) Boolean hasFruit,
            @RequestParam(name = "maxQuantity", required=false) Integer quantity
    ){
        if(hasFruit != null && quantity != null){
            if (hasFruit) {
                return plantRepository.findByHasFruitTrueAndQuantityLessThan(quantity);
            }else return plantRepository.findByHasFruitFalseAndQuantityLessThan( quantity);

        }else if (hasFruit != null && hasFruit) {
            return plantRepository.findByHasFruitTrue();
        } else if (hasFruit !=null && !hasFruit) {
            return plantRepository.findByHasFruitFalse();
        } else if (quantity != null){
            return plantRepository.findByQuantityLessThan(quantity);
        }
        return new ArrayList<>();
    }
}
