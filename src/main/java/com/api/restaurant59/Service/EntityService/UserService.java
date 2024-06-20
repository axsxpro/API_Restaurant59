package com.api.restaurant59.Service.EntityService;

import com.api.restaurant59.DTO.UserDTO;
import com.api.restaurant59.Service.GenericService;

// une interface étend une autre interface, elle hérite de toutes les méthodes de l'interface parent sans les implémenter
// UserService peut ajouter des méthodes spécifiques aux utilisateurs en plus de celles définies dans GenericService
public interface UserService extends GenericService<UserDTO, Integer> {


}
