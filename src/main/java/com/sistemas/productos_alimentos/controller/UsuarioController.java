package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.Usuario;
import com.sistemas.productos_alimentos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET http://localhost:8080/api/usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // GET http://localhost:8080/api/usuarios/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listarActivos() {
        return ResponseEntity.ok(usuarioService.obtenerActivos());
    }

    // GET http://localhost:8080/api/usuarios/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable String userId) {
        Usuario usuario = usuarioService.buscarPorId(userId);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    // POST http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    // PUT http://localhost:8080/api/usuarios/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable String userId, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.editar(userId, usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/usuarios/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String userId) {
        return usuarioService.eliminar(userId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


