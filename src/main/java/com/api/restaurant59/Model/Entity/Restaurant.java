package com.api.restaurant59.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.HashSet;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {


    @Id
    //Configure la clé primaire pour être auto-générée par la base de données.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Integer idRestaurant;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "additional_address")
    private String additionalAddress;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "siren", nullable = false)
    private String siren;

    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private City idCity;

    @ManyToOne
    @JoinColumn(name = "id_michelin_category", nullable = false)
    private MichelinCategory idMichelinCategory;

    @ManyToOne
    @JoinColumn(name = "id_availability", nullable = false)
    private Availability idAvailability;


    //relation avec restaurant_type
    @ManyToMany
    @JoinTable(
            name = "restaurant_types",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_type")
    )
    private Set<RestaurantType> restaurantTypes = new HashSet<>();


    //relation avec dietary_preference
    @ManyToMany
    @JoinTable(
            name = "restaurant_dietary",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_dietary_preference")
    )
    private Set<DietaryPreference> dietaryPreferences = new HashSet<>();


    //relation avec culinary_origin
    @ManyToMany
    @JoinTable(
            name = "restaurant_origin",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_culinary_origin")
    )
    private Set<CulinaryOrigin> culinaryOrigins = new HashSet<>();


    //relation avec culinary_speciality
    @ManyToMany
    @JoinTable(
            name = "restaurant_speciality",
            joinColumns = @JoinColumn(name = "id_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "id_speciality")
    )
    private Set<CulinarySpeciality> culinarySpecialities = new HashSet<>();

}
