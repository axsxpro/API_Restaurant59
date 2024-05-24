package com.api.restaurant59.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeekDTO {

    private Integer idDay;
    private String day;
    private Set<ScheduleDTO> scheduleDTOS;

}
