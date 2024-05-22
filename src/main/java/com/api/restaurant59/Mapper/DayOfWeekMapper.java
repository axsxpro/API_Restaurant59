package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.Model.Entity.DayOfWeek;

public class DayOfWeekMapper {

    public static DayOfWeekDTO mapToDayOfWeekDTO(DayOfWeek dayOfWeek) {
        return new DayOfWeekDTO(
                dayOfWeek.getIdDays(),
                dayOfWeek.getDays()
        );
    }

    public static DayOfWeek mapToDayOfWeekEntity(DayOfWeekDTO dayOfWeekDTO) {
        return new DayOfWeek(
                dayOfWeekDTO.getIdDays(),
                dayOfWeekDTO.getDays()
        );
    }
}
