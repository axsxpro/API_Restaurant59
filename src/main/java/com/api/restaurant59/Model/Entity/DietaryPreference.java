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
@Table(name = "dietary_preference")
public class DietaryPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dietary_preference")
    private Integer idDietaryPreference;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "dietaryPreferences")
    private Set<Restaurant> restaurants = new HashSet<>();

}