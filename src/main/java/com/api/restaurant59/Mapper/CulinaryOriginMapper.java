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

    public static CulinaryOrigin mapToCulinaryOriginEntity(CulinaryOriginDTO culinaryOriginDTO) {
        return new CulinaryOrigin(
                culinaryOriginDTO.getIdCulinaryOrigin(),
                culinaryOriginDTO.getCountry()
        );
    }
}
