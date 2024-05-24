package com.api.restaurant59.DTO;

import com.api.restaurant59.Model.Entity.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private Integer idSchedule;
    private Time morningOpeningTime;
    private Time morningClosingTime;
    private Time eveningOpeningTime;
    private Time eveningClosingTime;

}
