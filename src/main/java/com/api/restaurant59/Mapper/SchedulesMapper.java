package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.SchedulesDTO;
import com.api.restaurant59.Model.Entity.Schedules;

public class SchedulesMapper {

    public static SchedulesDTO mapToSchedulesDTO(Schedules schedules) {
        return new SchedulesDTO(

                schedules.getIdSchedules(),
                schedules.getMorningOpeningTime(),
                schedules.getMorningClosingTime(),
                schedules.getEveningOpeningTime(),
                schedules.getEveningClosingTime()
        );
    }

    public static Schedules mapToSchedulesEntity(SchedulesDTO schedulesDTO) {
        return new Schedules(

                schedulesDTO.getIdSchedules(),
                schedulesDTO.getMorningOpeningTime(),
                schedulesDTO.getMorningClosingTime(),
                schedulesDTO.getEveningOpeningTime(),
                schedulesDTO.getEveningClosingTime()
        );
    }
}
