package com.sistemas.productos_alimentos.controller;



import com.sistemas.productos_alimentos.models.Categoria;
import com.sistemas.productos_alimentos.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Categoria>> listarActivas() {
        return ResponseEntity.ok(categoriaService.obtenerActivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }
    //http://localhost:8080/api/categorias/buscar?nombre=Salsa Napoli
    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre));
    }


    // POST http://localhost:8080/api/categorias
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        System.out.println("=== INICIANDO INSERCIÓN DEFENSIVA DE CATEGORÍA EN SQLITE ===");
        System.out.println("Categoría recibida: [" + categoria.getCategoria() + "]");

        // 🛡️ CONTROL DEFENSIVO DE CAMPOS OBLIGATORIOS (NOT NULL en SQLite)
        if (categoria.getActivo() == null) {
            categoria.setActivo(1); // 1 = Activo por defecto
        }

        if (categoria.getUsuario() == null || categoria.getUsuario().trim().isEmpty()) {
            categoria.setUsuario("SYSTEM");
        }

        // Formateamos la marca de tiempo exacta con la longitud física requerida (25 caracteres)
        if (categoria.getTimestamp() == null || categoria.getTimestamp().trim().isEmpty()) {
            String isoTimestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            categoria.setTimestamp(isoTimestamp);
        }

        // Imprimimos diagnóstico rápido en la terminal de Java para validar los datos contables
        System.out.println("Costo Unitario: " + categoria.getCostoUnitario());
        System.out.println("Precio Venta: " + categoria.getPrecio());
        System.out.println("Peso: " + categoria.getPeso());
        System.out.println("==========================================================");

        Categoria guardada = categoriaService.guardar(categoria);
        return ResponseEntity.ok(guardada);
    }


    // PUT http://localhost:8080/api/categorias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        Categoria update = categoriaService.editar(id, categoria);
        if (update != null) {
            return ResponseEntity.ok(update);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/categorias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        boolean delete = categoriaService.eliminar(id);
        if (delete) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

