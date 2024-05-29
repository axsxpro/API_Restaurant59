package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.RoleDTO;
import com.api.restaurant59.Service.EntityService.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    @Operation(summary = "Create new role")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDto) {
        RoleDTO createdRole = roleService.create(roleDto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.readAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by ID")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Integer idRole) {
        RoleDTO role = roleService.getById(idRole);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update role by ID")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Integer idRole, @RequestBody RoleDTO roleDto) {
        RoleDTO updatedRole = roleService.update(idRole, roleDto);
        return new ResponseEntity<>(updatedRole, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete role by ID")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer idRole) {
        roleService.deleteById(idRole);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
