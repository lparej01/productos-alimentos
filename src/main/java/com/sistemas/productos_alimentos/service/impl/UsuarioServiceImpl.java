package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.Usuario;
import com.sistemas.productos_alimentos.repository.UsuarioRepository;
import com.sistemas.productos_alimentos.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder; // 👈 Para encriptar la clave
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Inyectamos tu encriptador

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> obtenerActivos() {
        return usuarioRepository.findByActive(1);
    }

    @Override
    public Usuario buscarPorId(String userId) {
        return usuarioRepository.findById(userId).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        // 🔐 Encriptamos la contraseña antes de guardarla en SQLite
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editar(String userId, Usuario detalles) {
        Optional<Usuario> opcion = usuarioRepository.findById(userId);
        if (opcion.isPresent()) {
            Usuario existente = opcion.get();

            // Si el cliente envía una nueva contraseña, la encriptamos
            if (detalles.getPassword() != null && !detalles.getPassword().isEmpty()) {
                existente.setPassword(passwordEncoder.encode(detalles.getPassword()));
            }
            existente.setActive(detalles.getActive());
            existente.setRole(detalles.getRole());

            return usuarioRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(String userId) {
        if (usuarioRepository.existsById(userId)) {
            usuarioRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}

