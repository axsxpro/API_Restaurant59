package com.api.restaurant59.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedules")
    private Integer idSchedules;

    @Column(name = "morning_opening_time", nullable = false)
    private Time morningOpeningTime;

    @Column(name = "morning_closing_time")
    private Time morningClosingTime;

    @Column(name = "evening_opening_time")
    private Time eveningOpeningTime;

    @Column(name = "evening_closing_time", nullable = false)
    private Time eveningClosingTime;
}