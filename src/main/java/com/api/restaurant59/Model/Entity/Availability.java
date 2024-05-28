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
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_availability")
    private Integer idAvailability;

    @OneToMany(mappedBy = "idAvailability", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Restaurant> restaurants = new HashSet<>();


    // Relation ManyToMany avec l'entité Schedule.
    @ManyToMany()
    @JoinTable(
            name = "availability_schedules",
            joinColumns = @JoinColumn(name = "id_availability"),
            inverseJoinColumns = @JoinColumn(name = "id_schedule")
    )
    private Set<Schedule> schedules = new HashSet<>();

}
