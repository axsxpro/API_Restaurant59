package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Service.EntityService.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;

    // Créer une nouvelle instance d'une entité Schedule
    @PostMapping("/create")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDto) {
        // Utilise le service pour créer une nouvelle Schedule à partir du DTO reçu
        ScheduleDTO createdSchedule = scheduleService.create(scheduleDto);
        // Retourne une réponse HTTP avec le DTO de la Schedule créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    // Récupérer toutes les données d'une entité Schedule
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(schedules);
    }

    // Récupérer une Schedule par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable("id") Integer idSchedule) {
        ScheduleDTO schedule = scheduleService.getById(idSchedule);
        // Retourne une réponse HTTP avec le DTO de la Schedule et un statut 200 (OK)
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    // Mettre à jour une Schedule par son identifiant
    @PutMapping("/update/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable("id") Integer idSchedule, @RequestBody ScheduleDTO scheduleDto) {
        ScheduleDTO updatedSchedule = scheduleService.update(idSchedule, scheduleDto);
        // Retourne une réponse HTTP avec le DTO de la Schedule mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedSchedule, HttpStatus.ACCEPTED);
    }

    // Supprimer une Schedule par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Integer idSchedule) {
        scheduleService.deleteById(idSchedule);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

