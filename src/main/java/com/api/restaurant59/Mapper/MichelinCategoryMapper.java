package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.MichelinCategoryDTO;
import com.api.restaurant59.Model.Entity.MichelinCategory;

public class MichelinCategoryMapper {

    public static MichelinCategoryDTO mapToMichelinCategoryDTO(MichelinCategory michelinCategory) {
        return new MichelinCategoryDTO(
                michelinCategory.getIdMichelinCategory(),
                michelinCategory.getCategoryName()
        );
    }

    public static MichelinCategory mapToMichelinCategoryEntity(MichelinCategoryDTO michelinCategoryDTO) {
        return new MichelinCategory(
                michelinCategoryDTO.getIdMichelinCategory(),
                michelinCategoryDTO.getCategoryName()
        );
    }
}

