package com.api.restaurant59.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer idUser;
    private String username;

    //la propriété annotée avec @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) peut être utilisée pour définir le mdp à partir d'une requête JSON (deserialisation), mais ne sera pas inclus dans les réponses JSON (serialisation)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;
    private Set<RoleDTO> roles;

}
