package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.Model.Entity.Availability;

public class AvailabilityMapper {

    public static AvailabilityDTO mapToAvailabilityDTO(Availability availability) {
        return new AvailabilityDTO(
                availability.getIdAvailability()
        );
    }

    public static Availability mapToAvailabilityEntity(AvailabilityDTO availabilityDTO) {
        return new Availability(
                availabilityDTO.getIdAvailability()
        );
    }
}
