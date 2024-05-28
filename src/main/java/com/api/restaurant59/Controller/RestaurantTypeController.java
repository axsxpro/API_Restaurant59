package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.RestaurantTypeDTO;
import com.api.restaurant59.Service.EntityService.RestaurantTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
@AllArgsConstructor
public class RestaurantTypeController {


    private RestaurantTypeService restaurantTypeService;

    // Créer une nouvelle instance d'une entité RestaurantType
    @PostMapping("/create")
    @Operation(summary = "Create new restaurant type")
    public ResponseEntity<RestaurantTypeDTO> createRestaurantType(@RequestBody RestaurantTypeDTO restaurantTypeDto) {
        // Utilise le service pour créer une nouvelle RestaurantType à partir du DTO reçu
        RestaurantTypeDTO createdRestaurantType = restaurantTypeService.create(restaurantTypeDto);
        // Retourne une réponse HTTP avec le DTO de la RestaurantType créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdRestaurantType, HttpStatus.CREATED);
    }

    // Récupérer toutes les données d'une entité RestaurantType
    @GetMapping
    @Operation(summary = "Get all restaurants types ")
    public ResponseEntity<List<RestaurantTypeDTO>> getAllRestaurantTypes() {
        List<RestaurantTypeDTO> restaurantTypes = restaurantTypeService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(restaurantTypes);
    }

    // Récupérer une RestaurantType par son identifiant
    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant type by ID ")
    public ResponseEntity<RestaurantTypeDTO> getRestaurantTypeById(@PathVariable("id") Integer idRestaurantType) {
        RestaurantTypeDTO restaurantType = restaurantTypeService.getById(idRestaurantType);
        // Retourne une réponse HTTP avec le DTO de la RestaurantType et un statut 200 (OK)
        return new ResponseEntity<>(restaurantType, HttpStatus.OK);
    }

    // Mettre à jour une RestaurantType par son identifiant
    @PutMapping("/update/{id}")
    @Operation(summary = "Update restaurant type by ID ")
    public ResponseEntity<RestaurantTypeDTO> updateRestaurantType(@PathVariable("id") Integer idRestaurantType, @RequestBody RestaurantTypeDTO restaurantTypeDto) {
        RestaurantTypeDTO updatedRestaurantType = restaurantTypeService.update(idRestaurantType, restaurantTypeDto);
        // Retourne une réponse HTTP avec le DTO de la RestaurantType mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedRestaurantType, HttpStatus.ACCEPTED);
    }

    // Supprimer une RestaurantType par son identifiant
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete restaurant type by ID ")
    public ResponseEntity<Void> deleteRestaurantType(@PathVariable("id") Integer idRestaurantType) {
        restaurantTypeService.deleteById(idRestaurantType);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

