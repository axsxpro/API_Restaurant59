package com.api.restaurant59.DTO;

import com.api.restaurant59.Model.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Integer idRestaurant;
    private String name;
    private String address;
    private String additionalAddress;
    private String phone;
    private String email;
    private String website;
    private String siren;

    //Many To One
    private City idCity;
    private MichelinCategory idMichelinCategory;
    private Availability idAvailability;

    //Many to Many
    private Set<RestaurantType> restaurantTypes;
    private Set<DietaryPreference> dietaryPreferences;
    private Set<CulinaryOrigin> culinaryOrigins;
    private Set<CulinarySpeciality> culinarySpecialities;

}
