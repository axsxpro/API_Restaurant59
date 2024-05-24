package com.api.restaurant59.DTO;

import com.api.restaurant59.Model.Entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDTO {

    private Integer idAvailability;
    private Set<DayOfWeekDTO> dayOfWeekDTOS;
}

