package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CulinarySpecialityDTO;
import com.api.restaurant59.Model.Entity.CulinarySpeciality;

public class CulinarySpecialityMapper {

    public static CulinarySpecialityDTO mapToCulinarySpecialityDTO(CulinarySpeciality culinarySpeciality) {
        return new CulinarySpecialityDTO(
                culinarySpeciality.getIdSpeciality(),
                culinarySpeciality.getDescription()
        );
    }

    public static CulinarySpeciality mapToCulinarySpecialityEntity(CulinarySpecialityDTO culinarySpecialityDTO) {
        return new CulinarySpeciality(
                culinarySpecialityDTO.getIdSpeciality(),
                culinarySpecialityDTO.getDescription()
        );
    }
}
