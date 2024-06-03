package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Service.EntityService.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    // Créer une nouvelle instance d'une entité Schedule
    @PostMapping("/create")
    @Operation(summary = "Create new schedule")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDto) {
        // Utilise le service pour créer une nouvelle Schedule à partir du DTO reçu
        ScheduleDTO createdSchedule = scheduleService.create(scheduleDto);
        // Retourne une réponse HTTP avec le DTO de la Schedule créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }


    // Récupérer toutes les données d'une entité Schedule
    @GetMapping
    @Operation(summary = "Get all schedules")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(schedules);
    }


    @GetMapping("/pagination")
    @Operation(summary = "Get All schedules with pagination")
    public ResponseEntity<Page<ScheduleDTO>> getAllSchedulesWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        Page<ScheduleDTO> schedules = scheduleService.readAll(page, size);

        return ResponseEntity.ok(schedules);
    }



    // Récupérer une Schedule par son identifiant
    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable("id") Integer idSchedule) {
        ScheduleDTO schedule = scheduleService.getById(idSchedule);
        // Retourne une réponse HTTP avec le DTO de la Schedule et un statut 200 (OK)
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    // Mettre à jour une Schedule par son identifiant
    @PutMapping("/update/{id}")
    @Operation(summary = "Update schedule by ID")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable("id") Integer idSchedule, @RequestBody ScheduleDTO scheduleDto) {
        ScheduleDTO updatedSchedule = scheduleService.update(idSchedule, scheduleDto);
        // Retourne une réponse HTTP avec le DTO de la Schedule mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedSchedule, HttpStatus.ACCEPTED);
    }


    // Supprimer une Schedule par son identifiant
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete schedule by ID")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Integer idSchedule) {
        scheduleService.deleteById(idSchedule);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

