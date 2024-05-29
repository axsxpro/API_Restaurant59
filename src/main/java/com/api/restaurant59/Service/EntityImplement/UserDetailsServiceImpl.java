package com.api.restaurant59.Service.EntityImplement;


import com.api.restaurant59.Model.Entity.User;
import com.api.restaurant59.Model.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    // Méthode pour charger les détails(username) de l'utilisateur à partir de la base de données
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Rechercher l'utilisateur dans la base de données par son nom d'utilisateur
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));


        // Créer un objet UserDetails à partir des informations récupérées de la base de données
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // Nom d'utilisateur
                user.getPassword(), // Mot de passe
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList()) // Rôles de l'utilisateur convertis en autorités
        );
    }


}
