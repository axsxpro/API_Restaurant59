package com.api.restaurant59.DTO;

import com.api.restaurant59.Model.Entity.Availability;
import com.api.restaurant59.Model.Entity.City;
import com.api.restaurant59.Model.Entity.MichelinCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private City idCity;
    private MichelinCategory idMichelinCategory;
    private Availability idAvailability;
}
