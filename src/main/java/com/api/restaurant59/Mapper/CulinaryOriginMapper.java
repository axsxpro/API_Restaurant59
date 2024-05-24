package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CulinaryOriginDTO;
import com.api.restaurant59.Model.Entity.CulinaryOrigin;

public class CulinaryOriginMapper {

    public static CulinaryOriginDTO mapToCulinaryOriginDTO(CulinaryOrigin culinaryOrigin) {

        return new CulinaryOriginDTO(

                culinaryOrigin.getIdCulinaryOrigin(),
                culinaryOrigin.getCountry()
        );
    }


    // Méthode pour mapper un DTO CulinaryOriginDTO vers une entité CulinaryOrigin
    public static CulinaryOrigin mapToCulinaryOriginEntity(CulinaryOriginDTO culinaryOriginDTO) {

        // Crée une nouvelle entité CulinaryOrigin avec les attributs du DTO CulinaryOriginDTO
        CulinaryOrigin culinaryOrigin = new CulinaryOrigin();

        culinaryOrigin.setIdCulinaryOrigin(culinaryOriginDTO.getIdCulinaryOrigin());
        culinaryOrigin.setCountry(culinaryOriginDTO.getCountry());

        return culinaryOrigin;
    }

}
