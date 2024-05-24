package com.api.restaurant59.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Integer idCity;
    private String cityName;
    private String postalCode;
    private String inseeCode;


}

