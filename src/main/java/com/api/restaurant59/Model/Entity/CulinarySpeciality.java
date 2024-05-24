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
@Table(name = "culinary_speciality")
public class CulinarySpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_speciality")
    private Integer idSpeciality;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "culinarySpecialities")
    private Set<Restaurant> restaurants = new HashSet<>();
}
