package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.Usuario;
import com.sistemas.productos_alimentos.repository.UsuarioRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Mapeamos el rol tal cual ("ADMIN_ROLE" o "USER_ROLE") como una Authority directa
        return new User(
                usuario.getUserId(),
                usuario.getPassword(),
                usuario.getActive() == 1, // true si es 1 (activo)
                true, true, true,
                List.of(new SimpleGrantedAuthority(usuario.getRole()))
        );
    }
}

