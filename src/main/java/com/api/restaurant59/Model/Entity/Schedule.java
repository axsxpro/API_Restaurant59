package com.api.restaurant59.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Integer idSchedule;

    @Column(name = "morning_opening_time")
    private Time morningOpeningTime;

    @Column(name = "morning_closing_time")
    private Time morningClosingTime;

    @Column(name = "evening_opening_time")
    private Time eveningOpeningTime;

    @Column(name = "evening_closing_time")
    private Time eveningClosingTime;

    @ManyToMany(mappedBy = "schedules")
    private Set<DayOfWeek> days = new HashSet<>();

}