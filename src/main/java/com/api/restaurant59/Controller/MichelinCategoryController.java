package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.MichelinCategoryDTO;
import com.api.restaurant59.Service.EntityService.MichelinCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/michelin")
@AllArgsConstructor
public class MichelinCategoryController {


    private MichelinCategoryService michelinCategoryService;


    // Créer une nouvelle instance d'une entité MichelinCategory
    @PostMapping("/create")
    public ResponseEntity<MichelinCategoryDTO> createMichelinCategory(@RequestBody MichelinCategoryDTO michelinCategoryDto) {
        // Utilise le service pour créer une nouvelle MichelinCategory à partir du DTO reçu
        MichelinCategoryDTO createdMichelinCategory = michelinCategoryService.create(michelinCategoryDto);
        // Retourne une réponse HTTP avec le DTO de la MichelinCategory créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdMichelinCategory, HttpStatus.CREATED);
    }


    // Récupérer toutes les données d'une entité MichelinCategory
    @GetMapping
    public ResponseEntity<List<MichelinCategoryDTO>> getAllMichelinCategories() {
        List<MichelinCategoryDTO> michelinCategories = michelinCategoryService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(michelinCategories);
    }


    // Récupérer une MichelinCategory par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<MichelinCategoryDTO> getMichelinCategoryById(@PathVariable("id") Integer idMichelinCategory) {
        MichelinCategoryDTO michelinCategory = michelinCategoryService.getById(idMichelinCategory);
        // Retourne une réponse HTTP avec le DTO de la MichelinCategory et un statut 200 (OK)
        return new ResponseEntity<>(michelinCategory, HttpStatus.OK);
    }


    // Mettre à jour une MichelinCategory par son identifiant
    @PutMapping("/update/{id}")
    public ResponseEntity<MichelinCategoryDTO> updateMichelinCategory(@PathVariable("id") Integer idMichelinCategory, @RequestBody MichelinCategoryDTO michelinCategoryDto) {
        MichelinCategoryDTO updatedMichelinCategory = michelinCategoryService.update(idMichelinCategory, michelinCategoryDto);
        // Retourne une réponse HTTP avec le DTO de la MichelinCategory mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedMichelinCategory, HttpStatus.ACCEPTED);
    }


    // Supprimer une MichelinCategory par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMichelinCategory(@PathVariable("id") Integer idMichelinCategory) {
        michelinCategoryService.deleteById(idMichelinCategory);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
