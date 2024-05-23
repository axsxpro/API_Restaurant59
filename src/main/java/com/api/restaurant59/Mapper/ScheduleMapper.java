package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Model.Entity.Schedule;

public class ScheduleMapper {

    public static ScheduleDTO mapToSchedulesDTO(Schedule schedule) {
        return new ScheduleDTO(

                schedule.getIdSchedule(),
                schedule.getMorningOpeningTime(),
                schedule.getMorningClosingTime(),
                schedule.getEveningOpeningTime(),
                schedule.getEveningClosingTime()
        );
    }

    public static Schedule mapToSchedulesEntity(ScheduleDTO scheduleDTO) {
        return new Schedule(

                scheduleDTO.getIdSchedule(),
                scheduleDTO.getMorningOpeningTime(),
                scheduleDTO.getMorningClosingTime(),
                scheduleDTO.getEveningOpeningTime(),
                scheduleDTO.getEveningClosingTime()
        );
    }
}
