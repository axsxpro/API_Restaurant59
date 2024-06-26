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
@Table(name = "culinary_origin")
public class CulinaryOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_culinary_origin")
    private Integer idCulinaryOrigin;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToMany(mappedBy = "culinaryOrigins")
    private Set<Restaurant> restaurants = new HashSet<>();

}