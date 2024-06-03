package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.Service.EntityService.AvailabilityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@AllArgsConstructor
public class AvailabilityController {


    @Autowired
    private AvailabilityService availabilityService;


    //créer une disponibilité
    @PostMapping("/create")
    @Operation(summary = "Create new availability")
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO createdAvailability = availabilityService.create(availabilityDTO);
        return new ResponseEntity<>(createdAvailability, HttpStatus.CREATED);
    }


    //récupérer toutes les disponibilités
    @GetMapping
    @Operation(summary = "Get all availabilities")
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailabilities() {
        List<AvailabilityDTO> availabilities = availabilityService.readAll();
        return ResponseEntity.ok(availabilities);
    }


    //récupérer toutes les disponibilités par pagination
    @GetMapping("/pagination")
    @Operation(summary = "Get All availabilities with pagination")
    public ResponseEntity<Page<AvailabilityDTO>> getAllAvailabilitiesWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

        Page<AvailabilityDTO> availabilities = availabilityService.readAll(page, size);

        return ResponseEntity.ok(availabilities);
    }


    //récupérer une disponibilité par son ID
    @GetMapping("/{id}")
    @Operation(summary = "Get availability by ID")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable("id") Integer id) {
        AvailabilityDTO availabilityDTO = availabilityService.getById(id);
        return ResponseEntity.ok(availabilityDTO);
    }


    //mettre à jour une disponibilité par son ID
    @PutMapping("/update/{id}")
    @Operation(summary = "Update availability by ID")
    public ResponseEntity<AvailabilityDTO> updateAvailability(@PathVariable("id") Integer id,
                                                              @RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO updatedAvailability = availabilityService.update(id, availabilityDTO);
        return new ResponseEntity<>(updatedAvailability, HttpStatus.ACCEPTED);
    }


    //supprimer une disponibilité par son ID
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete availability by ID")
    public ResponseEntity<Void> deleteAvailability(@PathVariable("id") Integer id) {
        availabilityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
