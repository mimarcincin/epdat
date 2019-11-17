package sk.upjs.epdat.controllers;

import sk.upjs.epdat.exceptions.ResourceNotFoundException;
import sk.upjs.epdat.models.Plant;
import sk.upjs.epdat.PlantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlantController {

    @Autowired
    PlantRepository plantRepository;

    // Get All Plants
    @GetMapping("/plants")
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }
    // Create a new Note
    @PostMapping("/plants")
    public Plant createPlant(@Valid @RequestBody Plant plant) {
        return plantRepository.save(plant);
    }
    // Get a Single Note
    @GetMapping("/plants/{id}")
    public Plant getPlantById(@PathVariable(value = "id") Long plantId) {
        return plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant", "id", plantId));
    }
    // Update a Note
    @PutMapping("/plants/{id}")
    public Plant updatePlant(@PathVariable(value = "id") Long plantId,
                           @Valid @RequestBody Plant plantDetails) {

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant", "id", plantId));

        plant.setFamily(plantDetails.getFamily());
        plant.setGenus(plantDetails.getGenus());
        plant.setSpecies(plantDetails.getGenus());
        plant.setAuthority(plantDetails.getAuthority());

        Plant updatedPlant = plantRepository.save(plant);
        return updatedPlant;
    }
    // Delete a Note
    @DeleteMapping("/plants/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable(value = "id") Long plantId) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant", "id", plantId));

        plantRepository.delete(plant);

        return ResponseEntity.ok().build();
    }
}