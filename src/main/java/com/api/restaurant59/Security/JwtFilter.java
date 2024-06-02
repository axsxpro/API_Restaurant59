package com.api.restaurant59.Security;

import com.api.restaurant59.Service.EntityImplement.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;

    // Cette méthode est appelée pour chaque requête entrante, interceptant la requête pour vérifier un token JWT.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Récupère le header Authorization de la requête pour vérifier la présence d'un token JWT.
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String username = null;
        String token = null;

        // Si le header Authorization est présent et commence par "Bearer ", le token JWT est extrait.
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // Le token JWT est extrait du header Authorization.
            // Le préfixe "Bearer " a une longueur de 7 caractères, donc nous utilisons substring(7) pour obtenir uniquement le token.
            token = authorizationHeader.substring(7);
            // Le nom d'utilisateur est extrait du token JWT en utilisant la méthode extractUsername du JwtUtil.
            username = jwtUtil.extractUsername(token);
        }


        // Si le nom d'utilisateur a été extrait et qu'il n'y a pas déjà une authentification en cours pour cette requête.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Les détails de l'utilisateur sont chargés à partir de la base de données en utilisant le UserDetailsServiceImpl.
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

            // Le token JWT est validé pour vérifier son authenticité et sa validité.
            if (jwtUtil.validateToken(token, userDetails)) {

                // Si le token est valide, un objet UsernamePasswordAuthenticationToken est créé avec les détails de l'utilisateur.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // Les détails de la requête sont ajoutés à l'objet d'authentification pour un contexte de sécurité complet.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // L'objet d'authentification est placé dans le contexte de sécurité de Spring, permettant l'accès sécurisé aux ressources.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // La chaîne de filtres continue avec la requête et la réponse, permettant aux filtres suivants de traiter la requête.
        filterChain.doFilter(request, response);
    }
}

