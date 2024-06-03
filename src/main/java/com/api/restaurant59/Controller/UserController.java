package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.UserDTO;
import com.api.restaurant59.Service.EntityService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    @Operation(summary = "Create new user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        UserDTO createdUser = userService.create(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.readAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/pagination")
    @Operation(summary = "Get All users with pagination")
    public ResponseEntity<Page<UserDTO>> getAllUsersWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Page<UserDTO> users = userService.readAll(page, size);

        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer idUser) {
        UserDTO user = userService.getById(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update user by ID")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer idUser, @RequestBody UserDTO userDto) {
        UserDTO updatedUser = userService.update(idUser, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer idUser) {
        userService.deleteById(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

