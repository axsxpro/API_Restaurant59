package com.api.restaurant59.Config;


import com.api.restaurant59.Service.EntityImplement.UserDetailsServiceImpl;
import com.api.restaurant59.Security.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity // Active la sécurité web dans l'application.
@AllArgsConstructor
public class SecurityConfig {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl; // Injecte l'implémentation de UserDetailsService.
    @Autowired
    private JwtFilter jwtFilter; // Injecte le filtre JWT.


    // Configure l'authentification avec UserDetailsServiceImpl et un PasswordEncoder.
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource())) // Configure le CORS.
                .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // création de session comme stateless.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users", "/api/roles").hasRole("ADMIN") // Autorise uniquement les administrateurs à accéder à ces endpoints.
                        .anyRequest().permitAll()) // Permet à tous les autres endpoints d'être accessibles sans authentification.
                .httpBasic(httpBasic -> httpBasic.disable()); // Désactive l'authentification HTTP Basic.

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Ajoute le filtre JWT avant le filtre UsernamePasswordAuthentication.

        return http.build(); // Construit la chaîne de filtres de sécurité.
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // CORS(Cross-Origin Resource Sharing): autorise l'échange de ressources ayant des domaines différents.
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Méthodes HTTP autorisées.
        configuration.setAllowedHeaders(List.of("*")); // Autorise tous les headers.
        configuration.setAllowCredentials(true); // Permet l'envoi des cookies d'authentification.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Applique cette configuration à toutes les requêtes.
        return source; // Retourne la source de configuration CORS.
    }


    // PasswordEncoder pour hacher les mots de passe.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
