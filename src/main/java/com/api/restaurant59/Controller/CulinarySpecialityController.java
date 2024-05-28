package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.CulinarySpecialityDTO;
import com.api.restaurant59.Service.EntityService.CulinarySpecialityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speciality")
@AllArgsConstructor
public class CulinarySpecialityController {


    private CulinarySpecialityService culinarySpecialityService;


    // Créer une nouvelle instance d'une entité CulinarySpeciality
    @PostMapping("/create")
    @Operation(summary = "Create culinary speciality")
    public ResponseEntity<CulinarySpecialityDTO> createCulinarySpeciality(@RequestBody CulinarySpecialityDTO culinarySpecialityDto) {

        // Utilise le service pour créer une nouvelle CulinarySpeciality à partir du DTO reçu
        CulinarySpecialityDTO createdCulinarySpeciality = culinarySpecialityService.create(culinarySpecialityDto);
        // Retourne une réponse HTTP avec le DTO de la CulinarySpeciality créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdCulinarySpeciality, HttpStatus.CREATED);
    }



    // Récupérer toutes les données d'une entité CulinarySpeciality
    @GetMapping
    @Operation(summary = "Get all culinary speciality")
    public ResponseEntity<List<CulinarySpecialityDTO>> getAllCulinarySpecialities() {

        List<CulinarySpecialityDTO> culinarySpecialities = culinarySpecialityService.readAll();
        // Retourne une réponse HTTP statut 200 (OK).
        return ResponseEntity.ok(culinarySpecialities);
    }



    // Récupérer une CulinarySpeciality par son identifiant
    @GetMapping("/{id}")
    @Operation(summary = "Get culinary speciality by ID")
    public ResponseEntity<CulinarySpecialityDTO> getCulinarySpecialityById(@PathVariable("id") Integer idCulinarySpeciality) {
        CulinarySpecialityDTO culinarySpeciality = culinarySpecialityService.getById(idCulinarySpeciality);
        // Retourne une réponse HTTP avec le DTO de la CulinarySpeciality et un statut 200 (OK)
        return new ResponseEntity<>(culinarySpeciality, HttpStatus.OK);
    }



    // Mettre à jour une CulinarySpeciality par son identifiant
    @PutMapping("/update/{id}")
    @Operation(summary = "Update culinary speciality by ID")
    public ResponseEntity<CulinarySpecialityDTO> updateCulinarySpeciality(@PathVariable("id") Integer idCulinarySpeciality, @RequestBody CulinarySpecialityDTO culinarySpecialityDto) {
        CulinarySpecialityDTO updatedCulinarySpeciality = culinarySpecialityService.update(idCulinarySpeciality, culinarySpecialityDto);
        // Retourne une réponse HTTP avec le DTO de la CulinarySpeciality mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedCulinarySpeciality, HttpStatus.ACCEPTED);
    }



    // Supprimer une CulinarySpeciality par son identifiant
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete culinary speciality by ID")
    public ResponseEntity<Void> deleteCulinarySpeciality(@PathVariable("id") Integer idCulinarySpeciality) {
        culinarySpecialityService.deleteById(idCulinarySpeciality);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
