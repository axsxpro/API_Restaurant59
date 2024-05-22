package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.DietaryPreferenceDTO;
import com.api.restaurant59.Model.Entity.DietaryPreference;

public class DietaryPreferenceMapper {

    public static DietaryPreferenceDTO mapToDietaryPreferenceDTO(DietaryPreference dietaryPreference) {
        return new DietaryPreferenceDTO(
                dietaryPreference.getIdDietaryPreference(),
                dietaryPreference.getDescription()
        );
    }

    public static DietaryPreference mapToDietaryPreferenceEntity(DietaryPreferenceDTO dietaryPreferenceDTO) {
        return new DietaryPreference(
                dietaryPreferenceDTO.getIdDietaryPreference(),
                dietaryPreferenceDTO.getDescription()
        );
    }
}
