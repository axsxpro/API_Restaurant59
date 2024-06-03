package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.Service.EntityService.DayOfWeekService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dayofweek")
@AllArgsConstructor
public class DayOfWeekController {


    @Autowired
    private DayOfWeekService dayOfWeekService;


    // Créer une nouvelle instance d'une entité DayOfWeek
    // @RequestBody : indiquer que le corps de la requête HTTP doit être converti en un objet de type DTO
    @PostMapping("/create")
    @Operation(summary = "Create new day of week")
    public ResponseEntity<DayOfWeekDTO> createDayOfWeek(@RequestBody DayOfWeekDTO dayOfWeekDto) {

        // Utilise le service pour créer une nouvelle DayOfWeek à partir du DTO reçu
        DayOfWeekDTO createdDayOfWeek = dayOfWeekService.create(dayOfWeekDto);
        // Retourne une réponse HTTP avec le DTO de la DayOfWeek créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdDayOfWeek, HttpStatus.CREATED);
    }



    // Récupérer toutes les données d'une entité DayOfWeek
    @GetMapping
    @Operation(summary = "Get all days of week")
    public ResponseEntity<List<DayOfWeekDTO>> getAllDayOfWeeks() {

        List<DayOfWeekDTO> dayOfWeeks = dayOfWeekService.readAll();
        // Retourne une réponse HTTP statut 200 (OK).
        return ResponseEntity.ok(dayOfWeeks);
    }




    // Récupérer une DayOfWeek par son identifiant
    @GetMapping("/{id}")
    @Operation(summary = "Get day of week by ID")
    public ResponseEntity<DayOfWeekDTO> getDayOfWeekById(@PathVariable("id") Integer idDayOfWeek) {

        DayOfWeekDTO dayOfWeek = dayOfWeekService.getById(idDayOfWeek);
        // Retourne une réponse HTTP avec le DTO de la DayOfWeek et un statut 200 (OK)
        return new ResponseEntity<>(dayOfWeek, HttpStatus.OK);
    }




    // Mettre à jour une DayOfWeek par son identifiant
    @PutMapping("/update/{id}")
    @Operation(summary = "Update day of week by ID")
    public ResponseEntity<DayOfWeekDTO> updateDayOfWeek(@PathVariable("id") Integer idDayOfWeek, @RequestBody DayOfWeekDTO dayOfWeekDto) {

        DayOfWeekDTO updatedDayOfWeek = dayOfWeekService.update(idDayOfWeek, dayOfWeekDto);
        // Retourne une réponse HTTP avec le DTO de la DayOfWeek mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedDayOfWeek, HttpStatus.ACCEPTED);
    }




    // Supprimer une DayOfWeek par son identifiant
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete day of week by ID")
    public ResponseEntity<Void> deleteDayOfWeek(@PathVariable("id") Integer idDayOfWeek) {

        dayOfWeekService.deleteById(idDayOfWeek);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
