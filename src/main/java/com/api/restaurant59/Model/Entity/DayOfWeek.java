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
@Table(name = "dayofweek")
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_day")
    private Integer idDay;

    @Column(name = "day", nullable = false)
    private String day;


    @ManyToMany(mappedBy = "days")
    private Set<Availability> availabilities = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "day_schedule",
            joinColumns = @JoinColumn(name = "id_day"),
            inverseJoinColumns = @JoinColumn(name = "id_schedule")
    )
    private Set<Schedule> schedules = new HashSet<>();



}