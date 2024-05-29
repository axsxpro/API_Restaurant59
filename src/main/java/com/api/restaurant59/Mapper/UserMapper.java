package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RoleDTO;
import com.api.restaurant59.DTO.UserDTO;
import com.api.restaurant59.Model.Entity.Role;
import com.api.restaurant59.Model.Entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {



    // Méthode pour mapper une entité User vers un DTO UserDTO
    public static UserDTO mapToUserDTO(User user) {

        // Crée un nouveau DTO UserDTO
        UserDTO userDTO = new UserDTO();

        // Affecte les champs de l'entité User au DTO UserDTO
        userDTO.setIdUser(user.getIdUser());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        // Vérifie si des rôles sont associés à l'entité User
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            // Création d'une collection pour récupérer les rôles sous forme de DTO
            Set<RoleDTO> roleDTOs = user.getRoles().stream()
                    .map(RoleMapper::mapToRoleDTO)
                    .collect(Collectors.toSet());

            // Affecte l'ensemble des rôles au DTO
            userDTO.setRoles(roleDTOs);
        } else {
            // Si aucun rôle n'est fourni dans l'entité, initialise un ensemble vide de rôles
            userDTO.setRoles(new HashSet<>());
        }

        // Retourne le DTO UserDTO
        return userDTO;
    }



    // Méthode pour mapper un DTO UserDTO vers une entité User
    public static User mapToUserEntity(UserDTO userDTO) {


        // Crée une nouvelle entité User
        User user = new User();

        // Affecte les champs du DTO UserDTO à l'entité User
        user.setIdUser(userDTO.getIdUser());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());


        // Vérifie si des rôles sont associés au DTO UserDTO
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            // Création d'une collection pour récupérer les rôles sous forme d'entité
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(RoleMapper::mapToRoleEntity)
                    .collect(Collectors.toSet());

            // Affecte les rôles à l'entité User
            user.setRoles(roles);
        } else {
            // Si aucun rôle n'est associé dans le DTO, initialise un ensemble vide de rôles
            user.setRoles(new HashSet<>());
        }

        // Retourne l'entité User
        return user;
    }

}
