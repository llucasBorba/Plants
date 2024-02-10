package com.example.Plants.Repository;

import com.example.Plants.entites.Plant;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant,Integer> {
}
