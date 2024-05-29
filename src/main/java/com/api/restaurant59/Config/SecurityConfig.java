package com.api.restaurant59.Config;


import com.api.restaurant59.Service.EntityImplement.UserDetailsServiceImpl;
import com.api.restaurant59.Util.JwtFilter;
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


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtFilter jwtFilter;



    // Configuration de l'authentification avec UserDetailsServiceImpl
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //ne créera pas de session pour les utilisateurs. Aucune information de session n'est stockée côté serveur
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users").hasRole("ADMIN") // Restreindre l'accès à /api/users pour les administrateurs
                        .requestMatchers("/api/roles").hasRole("ADMIN") // Restreindre l'accès à /api/roles pour les administrateurs
                        .anyRequest().permitAll()) // Permettre l'accès sans authentification pour tous les autres endpoints
                .httpBasic(httpBasic -> httpBasic.disable());

        // Ajout du filtre JWT avant le filtre UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // configuration d'un 'PasswordEncoder' pour hacher les mots de passe
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
