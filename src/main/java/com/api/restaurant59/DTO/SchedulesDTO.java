package com.api.restaurant59.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulesDTO {

    private Integer idSchedules;
    private Time morningOpeningTime;
    private Time morningClosingTime;
    private Time eveningOpeningTime;
    private Time eveningClosingTime;

}
