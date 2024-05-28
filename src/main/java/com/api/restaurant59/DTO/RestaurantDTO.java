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
    private CityDTO city;
    private MichelinCategoryDTO michelinCategory;
    private AvailabilityDTO availability;

    //Many to Many
    private Set<RestaurantTypeDTO> restaurantTypes;
    private Set<DietaryPreferenceDTO> dietaryPreferences;
    private Set<CulinaryOriginDTO> culinaryOrigins;
    private Set<CulinarySpecialityDTO> culinarySpecialities;

}
