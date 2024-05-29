package com.api.restaurant59.DTO;


import com.api.restaurant59.Model.Entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Integer idRole;
    private RoleType name;

}
