package com.api.restaurant59.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //ne créera pas de session pour les utilisateurs. Aucune information de session n'est stockée côté serveur
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users").hasRole("ADMIN") // Restreindre l'accès à /api/users pour les administrateurs
                        .requestMatchers("/api/roles").hasRole("ADMIN") // Restreindre l'accès à /api/roles pour les administrateurs
                        .anyRequest().permitAll()) // Permettre l'accès sans authentification pour tous les autres endpoints
                .httpBasic(httpBasic -> httpBasic.disable()); // Désactiver HTTP Basic si nécessaire

        return http.build();
    }


    // configuration d'un 'PasswordEncoder' pour hacher les mots de passe
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
