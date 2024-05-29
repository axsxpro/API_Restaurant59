package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RoleDTO;
import com.api.restaurant59.Model.Entity.Role;

public class RoleMapper {


    // Méthode pour mapper une entité Role vers un DTO RoleDTO
    public static RoleDTO mapToRoleDTO(Role role) {

        // Crée un nouveau DTO RoleDTO
        RoleDTO roleDTO = new RoleDTO();

        // Affecte les champs de l'entité Role au DTO RoleDTO
        roleDTO.setIdRole(role.getIdRole());
        roleDTO.setName(role.getName());

        // Retourne le DTO RoleDTO
        return roleDTO;
    }


    // Méthode pour mapper un DTO RoleDTO vers une entité Role
    public static Role mapToRoleEntity(RoleDTO roleDTO) {

        // Crée une nouvelle entité Role
        Role role = new Role();

        // Affecte les champs du DTO RoleDTO à l'entité Role
        role.setIdRole(roleDTO.getIdRole());
        role.setName(roleDTO.getName());

        // Retourne l'entité Role
        return role;
    }

}
