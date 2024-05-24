package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.DietaryPreferenceDTO;
import com.api.restaurant59.Model.Entity.DietaryPreference;

public class DietaryPreferenceMapper {


    // Méthode pour mapper une entité DietaryPreference vers un DTO DietaryPreferenceDTO
    public static DietaryPreferenceDTO mapToDietaryPreferenceDTO(DietaryPreference dietaryPreference) {

        // Crée un nouveau DietaryPreferenceDTO avec les attributs de l'entité DietaryPreference
        return new DietaryPreferenceDTO(
                dietaryPreference.getIdDietaryPreference(),
                dietaryPreference.getDescription()
        );
    }

    // Méthode pour mapper un DTO DietaryPreferenceDTO vers une entité DietaryPreference
    public static DietaryPreference mapToDietaryPreferenceEntity(DietaryPreferenceDTO dietaryPreferenceDTO) {

        // Crée une nouvelle entité DietaryPreference avec les attributs du DTO DietaryPreferenceDTO
        DietaryPreference dietaryPreference = new DietaryPreference();
        dietaryPreference.setIdDietaryPreference(dietaryPreferenceDTO.getIdDietaryPreference());
        dietaryPreference.setDescription(dietaryPreferenceDTO.getDescription());

        return dietaryPreference;
    }

}

