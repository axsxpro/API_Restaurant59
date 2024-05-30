package com.api.restaurant59.Controller;


import com.api.restaurant59.Model.Entity.AuthRequest;
import com.api.restaurant59.Service.EntityImplement.UserDetailsServiceImpl;
import com.api.restaurant59.Util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            // Authentifier l'utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    ));
        } catch (BadCredentialsException e) {

            throw new Exception("Incorrect username or Password: ", e);
        }

        // Générer le token JWT
        // va faire appel de la classe userDetailsServiceImpl qui va rechercher si le username est existant dans la BDD
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());
        // si l'user existe, retourne les détails/infos de l'utilisateur pour générer le token
        String jwt = jwtUtil.generateToken(userDetails);

        // Retourner le token JWT dans une réponse JSON
        return ResponseEntity.ok(Map.of("token", jwt));
    }

}
