package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.DietaryPreferenceDTO;
import com.api.restaurant59.Service.EntityService.DietaryPreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dietary")
@AllArgsConstructor
public class DietaryPreferenceController {


    private DietaryPreferenceService dietaryPreferenceService;


    // Créer une nouvelle instance d'une entité DietaryPreference
    @PostMapping("/create")
    public ResponseEntity<DietaryPreferenceDTO> createDietaryPreference(@RequestBody DietaryPreferenceDTO dietaryPreferenceDto) {

        // Utilise le service pour créer une nouvelle DietaryPreference à partir du DTO reçu
        DietaryPreferenceDTO createdDietaryPreference = dietaryPreferenceService.create(dietaryPreferenceDto);
        // Retourne une réponse HTTP avec le DTO de la DietaryPreference créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdDietaryPreference, HttpStatus.CREATED);
    }


    // Récupérer toutes les données de la table DietaryPreference
    @GetMapping
    public ResponseEntity<List<DietaryPreferenceDTO>> getAllDietaryPreferences() {

        List<DietaryPreferenceDTO> dietaryPreferences = dietaryPreferenceService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(dietaryPreferences);
    }


    // Récupérer une DietaryPreference par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<DietaryPreferenceDTO> getDietaryPreferenceById(@PathVariable("id") Integer idDietaryPreference) {

        DietaryPreferenceDTO dietaryPreference = dietaryPreferenceService.getById(idDietaryPreference);
        // Retourne une réponse HTTP avec le DTO de la DietaryPreference et un statut 200 (OK)
        return new ResponseEntity<>(dietaryPreference, HttpStatus.OK);
    }


    // Mettre à jour une DietaryPreference par son identifiant
    @PutMapping("/update/{id}")
    public ResponseEntity<DietaryPreferenceDTO> updateDietaryPreference(@PathVariable("id") Integer idDietaryPreference, @RequestBody DietaryPreferenceDTO dietaryPreferenceDto) {

        DietaryPreferenceDTO updatedDietaryPreference = dietaryPreferenceService.update(idDietaryPreference, dietaryPreferenceDto);
        // Retourne une réponse HTTP avec le DTO de la DietaryPreference mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedDietaryPreference, HttpStatus.ACCEPTED);
    }


    // Supprimer une DietaryPreference par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDietaryPreference(@PathVariable("id") Integer idDietaryPreference) {

        dietaryPreferenceService.deleteById(idDietaryPreference);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
