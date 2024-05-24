package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.Service.EntityService.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@AllArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping("/create")
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO createdAvailability = availabilityService.create(availabilityDTO);
        return new ResponseEntity<>(createdAvailability, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailabilities() {
        List<AvailabilityDTO> availabilities = availabilityService.readAll();
        return ResponseEntity.ok(availabilities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable("id") Integer id) {
        AvailabilityDTO availabilityDTO = availabilityService.getById(id);
        return ResponseEntity.ok(availabilityDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailability(@PathVariable("id") Integer id,
                                                              @RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO updatedAvailability = availabilityService.update(id, availabilityDTO);
        return new ResponseEntity<>(updatedAvailability, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable("id") Integer id) {
        availabilityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
