package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CulinarySpecialityDTO;
import com.api.restaurant59.Model.Entity.CulinarySpeciality;

public class CulinarySpecialityMapper {

    // Méthode pour mapper une entité CulinarySpeciality vers un DTO CulinarySpecialityDTO
    public static CulinarySpecialityDTO mapToCulinarySpecialityDTO(CulinarySpeciality culinarySpeciality) {

        // Crée un nouveau CulinarySpecialityDTO avec les attributs de l'entité CulinarySpeciality
        return new CulinarySpecialityDTO(
                culinarySpeciality.getIdSpeciality(),
                culinarySpeciality.getDescription()
        );

    }

    // Méthode pour mapper un DTO CulinarySpecialityDTO vers une entité CulinarySpeciality
    public static CulinarySpeciality mapToCulinarySpecialityEntity(CulinarySpecialityDTO culinarySpecialityDTO) {

        // Crée une nouvelle entité CulinarySpeciality avec les attributs du DTO CulinarySpecialityDTO
        CulinarySpeciality culinarySpeciality = new CulinarySpeciality();
        culinarySpeciality.setIdSpeciality(culinarySpecialityDTO.getIdSpeciality());
        culinarySpeciality.setDescription(culinarySpecialityDTO.getDescription());

        return culinarySpeciality;
    }

}

