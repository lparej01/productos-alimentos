package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    List<Usuario> obtenerActivos();
    Usuario buscarPorId(String userId);
    Usuario guardar(Usuario usuario);
    Usuario editar(String userId, Usuario usuario);
    boolean eliminar(String userId);
}

