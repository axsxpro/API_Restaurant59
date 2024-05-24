package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.MichelinCategoryDTO;
import com.api.restaurant59.Model.Entity.MichelinCategory;

public class MichelinCategoryMapper {


    // Méthode pour mapper une entité MichelinCategory vers un DTO MichelinCategoryDTO
    public static MichelinCategoryDTO mapToMichelinCategoryDTO(MichelinCategory michelinCategory) {

        // Crée un nouveau MichelinCategoryDTO avec les attributs de l'entité MichelinCategory
        return new MichelinCategoryDTO(
                michelinCategory.getIdMichelinCategory(),
                michelinCategory.getCategoryName()
        );
    }


    // Méthode pour mapper un DTO MichelinCategoryDTO vers une entité MichelinCategory
    public static MichelinCategory mapToMichelinCategoryEntity(MichelinCategoryDTO michelinCategoryDTO) {

        // Crée une nouvelle entité MichelinCategory avec les attributs du DTO MichelinCategoryDTO
        MichelinCategory michelinCategory = new MichelinCategory();
        michelinCategory.setIdMichelinCategory(michelinCategoryDTO.getIdMichelinCategory());
        michelinCategory.setCategoryName(michelinCategoryDTO.getCategoryName());

        return michelinCategory;
    }

}


