package com.sistemas.productos_alimentos.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // Delegamos todo lo relacionado a JWT aquí

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 1. Si va a la ruta de autenticación, saltamos el filtro de inmediato
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // 2. Extraemos el usuario usando tu JwtUtil
                String userId = jwtUtil.extractUserId(token);

                // 3. Validamos el token y verificamos que no exista una autenticación previa en el contexto
                if (userId != null && jwtUtil.validateToken(token, userId) && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // 4. 🌟 CORRECCIÓN DE SEGURIDAD: Extraer el rol de forma segura usando tu JwtUtil
                    // Nota: Si no tienes el método 'extractRole' en tu JwtUtil, puedes implementar el fragmento de abajo.
                    String role = jwtUtil.extractRole(token);
                    if (role == null) {
                        role = "ROLE_USER"; // Rol por defecto si viene vacío
                    }

                    // 5. Inyectamos las credenciales validadas en el contexto de Spring Security
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority(role))
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Evitamos romper la app en consola, solo notificamos el fallo de autenticación
                logger.error("Error en la autenticación JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
