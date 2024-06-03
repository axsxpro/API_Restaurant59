package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Service.EntityService.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;


    // Créer une nouvelle instance d'une entité Restaurant
    @PostMapping("/create")
    @Operation(summary = "Create a new restaurant")
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDto) {
        // Utilise le service pour créer une nouvelle Restaurant à partir du DTO reçu
        RestaurantDTO createdRestaurant = restaurantService.create(restaurantDto);
        // Retourne une réponse HTTP avec le DTO de la Restaurant créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }


    // Récupérer toutes les données d'une entité Restaurant
    @GetMapping
    @Operation(summary = "Get All restaurant")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.readAll();
        // Retourne une réponse HTTP statut 200 (OK)
        return ResponseEntity.ok(restaurants);
    }


    // récupérer restaurant avec pagination
    @GetMapping("/pagination")
    @Operation(summary = "Get All restaurants with pagination")
    public ResponseEntity<Page<RestaurantDTO>> getAllRestaurantsWithPagination(
            // Définit un paramètre de requête "page" avec une valeur par défaut de 0 (1ère page).
            @RequestParam(defaultValue = "0") int page,
            // Définit un paramètre de requête "size" avec une valeur par défaut de 3 (3 élements par page).
            @RequestParam(defaultValue = "3") int size) {

        // Appelle le service pour récupérer les restaurants avec pagination en utilisant les paramètres de page et de taille.
        Page<RestaurantDTO> restaurants = restaurantService.readAll(page, size);

        // Retourne une réponse HTTP 200 (OK) contenant la page de restaurants.
        return ResponseEntity.ok(restaurants);
    }


    // Récupérer une Restaurant par son identifiant
    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") Integer idRestaurant) {
        RestaurantDTO restaurant = restaurantService.getById(idRestaurant);
        // Retourne une réponse HTTP avec le DTO de la Restaurant et un statut 200 (OK)
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    // Mettre à jour une Restaurant par son identifiant
    @PutMapping("/update/{id}")
    @Operation(summary = "Update restaurant by ID")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable("id") Integer idRestaurant, @RequestBody RestaurantDTO restaurantDto) {
        RestaurantDTO updatedRestaurant = restaurantService.update(idRestaurant, restaurantDto);
        // Retourne une réponse HTTP avec le DTO de la Restaurant mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.ACCEPTED);
    }


    // Supprimer une Restaurant par son identifiant
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete restaurant by ID")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Integer idRestaurant) {
        restaurantService.deleteById(idRestaurant);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

