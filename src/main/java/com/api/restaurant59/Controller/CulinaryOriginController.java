package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.CulinaryOriginDTO;
import com.api.restaurant59.Service.EntityService.CulinaryOriginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/origin")
@AllArgsConstructor
public class CulinaryOriginController {


    private CulinaryOriginService culinaryOriginService;


    // Créer une nouvelle instance d'une entité CulinaryOrigin
    // @RequestBody : indiquer que le corps de la requête HTTP doit être converti en un objet de type CulinaryOriginDTO
    @PostMapping("/create")
    public ResponseEntity<CulinaryOriginDTO> createCulinaryOrigin(@RequestBody CulinaryOriginDTO culinaryOriginDto) {

        // Utilise le service pour créer une nouvelle CulinaryOrigin à partir du DTO reçu
        CulinaryOriginDTO createdCulinaryOrigin = culinaryOriginService.create(culinaryOriginDto);
        // Retourne une réponse HTTP avec le DTO de la CulinaryOrigin créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdCulinaryOrigin, HttpStatus.CREATED);
    }


    // Récupérer toutes les données d'une entité CulinaryOrigin
    @GetMapping
    public ResponseEntity<List<CulinaryOriginDTO>> getAllCulinaryOrigins() {

        List<CulinaryOriginDTO> culinaryOrigins = culinaryOriginService.readAll();
        // Retourne une réponse HTTP statut 200 (OK).
        return ResponseEntity.ok(culinaryOrigins);
    }


    // Récupérer une CulinaryOrigin par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<CulinaryOriginDTO> getCulinaryOriginById(@PathVariable("id") Integer idCulinaryOrigin) {

        CulinaryOriginDTO culinaryOrigin = culinaryOriginService.getById(idCulinaryOrigin);
        // Retourne une réponse HTTP avec le DTO de la CulinaryOrigin et un statut 200 (OK)
        return new ResponseEntity<>(culinaryOrigin, HttpStatus.OK);
    }


    // Mettre à jour une CulinaryOrigin par son identifiant
    @PutMapping("/update/{id}")
    public ResponseEntity<CulinaryOriginDTO> updateCulinaryOrigin(@PathVariable("id") Integer idCulinaryOrigin, @RequestBody CulinaryOriginDTO culinaryOriginDto) {

        CulinaryOriginDTO updatedCulinaryOrigin = culinaryOriginService.update(idCulinaryOrigin, culinaryOriginDto);
        // Retourne une réponse HTTP avec le DTO de la CulinaryOrigin mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedCulinaryOrigin, HttpStatus.ACCEPTED);
    }


    // Supprimer une CulinaryOrigin par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCulinaryOrigin(@PathVariable("id") Integer idCulinaryOrigin) {

        culinaryOriginService.deleteById(idCulinaryOrigin);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

