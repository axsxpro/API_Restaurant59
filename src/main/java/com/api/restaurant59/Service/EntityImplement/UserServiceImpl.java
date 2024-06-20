package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.UserDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.UserMapper;
import com.api.restaurant59.Model.Entity.Role;
import com.api.restaurant59.Model.Entity.User;
import com.api.restaurant59.Model.Repository.RoleRepository;
import com.api.restaurant59.Model.Repository.UserRepository;
import com.api.restaurant59.Service.EntityService.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO create(UserDTO userDto) {

        // Mappe le DTO en entité User
        User user = UserMapper.mapToUserEntity(userDto);

        // Vérifie si des rôles sont associés au DTO User
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<Role> roles = userDto.getRoles().stream()
                    .map(roleDTO -> roleRepository.findById(roleDTO.getIdRole())
                            .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleDTO.getIdRole())))
                    .collect(Collectors.toSet());
            // Associe les rôles trouvés à l'utilisateur
            user.setRoles(roles);
        }

        // Hacher le mot de passe avant de sauvegarder
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Sauvegarde l'entité User dans le repository
        User savedUser = userRepository.save(user);

        // Mappe l'entité User sauvegardée en DTO et la retourne
        return UserMapper.mapToUserDTO(savedUser);
    }


    //get all users sans pagination
    @Override
    public List<UserDTO> readAll() {
        // Récupère toutes les entités User du repository et les mappe en DTO
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }


    //pagination
    @Override
    public Page<UserDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idUser").ascending());
        // Utilise le repository pour trouver toutes les entités User en fonction de l'objet Pageable.
        Page<User> userPage = userRepository.findAll(pageable);

        // Mappe chaque entité User de la page à un UserDTO en utilisant le mapper spécifié.
        return userPage.map(UserMapper::mapToUserDTO);
    }


    @Override
    public UserDTO getById(Integer idUser) {
        // Cherche une entité User par son identifiant et la mappe en DTO
        return userRepository.findById(idUser)
                .map(UserMapper::mapToUserDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idUser));
    }


    @Override
    public UserDTO update(Integer idUser, UserDTO userDto) {

        // Cherche l'entité User à mettre à jour par son identifiant
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idUser));

        // Met à jour les champs de l'entité User avec les valeurs du DTO
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        // Vérifie si des rôles sont associés au DTO User
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<Role> roles = userDto.getRoles().stream()
                    .map(roleDTO -> roleRepository.findById(roleDTO.getIdRole())
                            .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleDTO.getIdRole())))
                    .collect(Collectors.toSet());

            // Associe les rôles trouvés à l'utilisateur
            user.setRoles(roles);
        }

        // Hacher le mot de passe seulement s' il est mis à jour
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        // Sauvegarde l'entité User mise à jour dans le repository
        User updatedUser = userRepository.save(user);
        // Mappe l'entité User mise à jour en DTO et la retourne
        return UserMapper.mapToUserDTO(updatedUser);
    }


    @Override
    public void deleteById(Integer idUser) {
        // Supprime l'entité User par son identifiant
        userRepository.deleteById(idUser);
    }

}

