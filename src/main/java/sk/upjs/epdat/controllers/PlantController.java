package sk.upjs.epdat.controllers;

import org.springframework.data.jpa.repository.Query;
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
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    // Get All Plants
    @GetMapping("/plants")
    @CrossOrigin
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
        if (plantDetails.getFamily() != "")
            plant.setFamily(plantDetails.getFamily());
        if (plantDetails.getGenus() != "")
            plant.setGenus(plantDetails.getGenus());
        if (plantDetails.getSpecies() != "")
            plant.setSpecies(plantDetails.getSpecies());
        if (plantDetails.getAuthority() != "")
            plant.setAuthority(plantDetails.getAuthority());
        if (plantDetails.getNotice() != "")
            plant.setNotice(plantDetails.getNotice());


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

    //get all plants with family
    @GetMapping("/plants/F/{family}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamily(@PathVariable(value = "family") String family) {
        return plantRepository.findAllByFamily(family);
    }

    //get all plants with family and genus
    @GetMapping("/plants/FG/{family}/{genus}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamilyAndGenus(@PathVariable(value = "family") String family, @PathVariable(value = "genus") String genus) {
        return plantRepository.findAllByFamilyAndGenusContaining(family, genus);
    }

    //get all plants with family and genus and species
    @GetMapping("/plants/FGS/{family}/{genus}/{species}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamilyAndGenusAndSpecies(@PathVariable(value = "family") String family, @PathVariable(value = "genus") String genus, @PathVariable(value = "species") String species) {
        return plantRepository.findAllByFamilyAndGenusContainingAndSpeciesContaining(family, genus, species);
    }

   //get all plants with family and tissue
    @GetMapping("/plants/FT/{family}/{recordTissue}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamilyAndTissue(@PathVariable(value = "family") String family, @PathVariable(value = "recordTissue") String recordTissue){
        return plantRepository.findAllbyFamilyAndTissue(family, recordTissue);

    }

    //get all plants with family and genus and tissue
    @GetMapping("/plants/FGT/{family}/{genus}/{tissue}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamilyAndGenusAndTissue(@PathVariable(value = "family") String family, @PathVariable(value = "genus") String genus, @PathVariable(value = "tissue") String tissue) {
        return plantRepository.findAllByFamilyAndGenusAndTissue(family, genus, tissue);
    }

    //get all plants with family and genus and species and tissue
    @GetMapping("/plants/FGST/{family}/{genus}/{species}/{tissue}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Plant> getAllPlantsByFamilyAndGenusAndSpeciesAndTissue(@PathVariable(value = "family") String family, @PathVariable(value = "genus") String genus, @PathVariable(value = "species") String species, @PathVariable(value = "tissue") String tissue) {
        return plantRepository.findAllByFamilyAndGenusAndSpeciesAndTissue(family, genus, species, tissue);
    }
}