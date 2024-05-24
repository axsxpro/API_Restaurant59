package com.api.restaurant59.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Integer idCity;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "postal_code", nullable = false, length = 5)
    private String postalCode;

    @Column(name = "insee_code", nullable = false, length = 5)
    private String inseeCode;

    @OneToMany(mappedBy = "idCity")
    private Set<Restaurant> restaurants = new HashSet<>();

}