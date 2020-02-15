package sk.upjs.epdat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.upjs.epdat.PlantRepository;
import sk.upjs.epdat.RecordRepository;
import sk.upjs.epdat.exceptions.ResourceNotFoundException;
import sk.upjs.epdat.models.Record;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping("/plants/{plantId}/records")
    public Page<Record> getAllRecordsByPlantId(@PathVariable(value = "plantId") Long plantId, Pageable pageable) {
        return recordRepository.findByPlantId(plantId, pageable);
    }

    @PostMapping("/plants/{plantId}/records")
    public Record createRecord(@PathVariable(value = "plantId") Long plantId,
                               @Valid @RequestBody Record record) {
        return plantRepository.findById(plantId).map(plant -> {
            record.setPlant(plant);
            return recordRepository.save(record);
        }).orElseThrow(() -> new ResourceNotFoundException("PlantId", "not found", plantId));
    }

    @PutMapping("/plants/{plantId}/records/{recordId}")
    public Record updateRecord(@PathVariable(value = "plantId") Long plantId,
                               @PathVariable(value = "recordId") Long recordId,
                               @Valid @RequestBody Record recordRequest) {
        if (!plantRepository.existsById(plantId)) {
            throw new ResourceNotFoundException("PlantId", "not found", plantId);
        }

        return recordRepository.findById(recordId).map(record -> {
            if (recordRequest.getEndopolyploidy() >= 0)
                record.setEndopolyploidy(recordRequest.getEndopolyploidy());
            if (recordRequest.getChromosomeNumber() != null)
                record.setChromosomeNumber(recordRequest.getChromosomeNumber());
            if (recordRequest.getPloidyLevel() >= 0)
                record.setPloidyLevel(recordRequest.getPloidyLevel());
            if (recordRequest.getNumber() >= 0)
                record.setNumber(recordRequest.getNumber());
            if (recordRequest.getIndexType()!=null)
                record.setIndexType(recordRequest.getIndexType());
            if (recordRequest.getTissue()!=null)
                record.setTissue(recordRequest.getTissue());
            return recordRepository.save(record);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId ","recordId", recordId));
    }

    @DeleteMapping("/plants/{plantId}/records/{recordId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "plantId") Long plantId,
                                           @PathVariable (value = "recordId") Long recordId) {
        return recordRepository.findByPlantIdAndId(plantId, recordId).map(record -> {
            recordRepository.delete(record);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PlantId", "not found", plantId)); //upravit
    }


}
