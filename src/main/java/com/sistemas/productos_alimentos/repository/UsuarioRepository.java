package com.sistemas.productos_alimentos.repository;


import com.sistemas.productos_alimentos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> { // Id es String

    // Buscar usuarios activos (active = 1)
    List<Usuario> findByActive(Integer active);

    // Buscar un usuario específico por su userId
    Optional<Usuario> findByUserId(String userId);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUuidv4(String uuidv4);
}


