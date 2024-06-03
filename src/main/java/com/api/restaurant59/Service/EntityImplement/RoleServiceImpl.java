package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.RoleDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.RoleMapper;
import com.api.restaurant59.Model.Entity.Role;
import com.api.restaurant59.Model.Repository.RoleRepository;
import com.api.restaurant59.Service.EntityService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleDTO create(RoleDTO roleDto) {

        // Mappe le DTO en entité Role
        Role role = RoleMapper.mapToRoleEntity(roleDto);

        // Sauvegarde l'entité Role dans le repository
        Role savedRole = roleRepository.save(role);

        // Mappe l'entité Role sauvegardée en DTO et la retourne
        return RoleMapper.mapToRoleDTO(savedRole);
    }


    @Override
    public List<RoleDTO> readAll() {
        // Récupère toutes les entités Role du repository et les mappe en DTO
        return roleRepository.findAll().stream()
                .map(RoleMapper::mapToRoleDTO)
                .collect(Collectors.toList());
    }


    //pagination
    @Override
    public Page<RoleDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idRole").ascending());
        // Utilise le repository pour trouver toutes les entités Role en fonction de l'objet Pageable.
        Page<Role> rolePage = roleRepository.findAll(pageable);

        // Mappe chaque entité Role de la page à un RoleDTO en utilisant le mapper spécifié.
        return rolePage.map(RoleMapper::mapToRoleDTO);
    }


    @Override
    public RoleDTO getById(Integer idRole) {
        // Cherche une entité Role par son identifiant et la mappe en DTO
        return roleRepository.findById(idRole)
                .map(RoleMapper::mapToRoleDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + idRole));
    }


    @Override
    public RoleDTO update(Integer idRole, RoleDTO roleDto) {

        // Cherche l'entité Role à mettre à jour par son identifiant
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + idRole));

        // Met à jour le champ name de l'entité Role avec la valeur du DTO
        role.setName(roleDto.getName());

        // Sauvegarde l'entité Role mise à jour dans le repository
        Role updatedRole = roleRepository.save(role);

        // Mappe l'entité Role mise à jour en DTO et la retourne
        return RoleMapper.mapToRoleDTO(updatedRole);
    }


    @Override
    public void deleteById(Integer idRole) {
        // Supprime l'entité Role par son identifiant
        roleRepository.deleteById(idRole);
    }

}
