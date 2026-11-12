package com.sistemas.productos_alimentos.controller;


import com.sistemas.productos_alimentos.models.Proveedores;
import com.sistemas.productos_alimentos.service.ProveedoresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    // GET http://localhost:8080/api/proveedores
    @GetMapping
    public ResponseEntity<List<Proveedores>> listarTodos() {
        return ResponseEntity.ok(proveedoresService.obtenerTodos());
    }

    // GET http://localhost:8080/api/proveedores/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Proveedores>> listarActivos() {
        return ResponseEntity.ok(proveedoresService.obtenerActivos());
    }

    // GET http://localhost:8080/api/proveedores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> buscarPorId(@PathVariable Integer id) {
        Proveedores prov = proveedoresService.buscarPorId(id);
        return prov != null ? ResponseEntity.ok(prov) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/proveedores/buscar?nombre=distribuidora
    @GetMapping("/buscar")
    public ResponseEntity<List<Proveedores>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(proveedoresService.buscarPorNombre(nombre));
    }

    // POST http://localhost:8080/api/proveedores
    @PostMapping
    public ResponseEntity<Proveedores> crearProveedor(@RequestBody Proveedores proveedor) {
        return ResponseEntity.ok(proveedoresService.guardar(proveedor));
    }

    // PUT http://localhost:8080/api/proveedores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Proveedores> editarProveedor(@PathVariable Integer id, @RequestBody Proveedores proveedor) {
        Proveedores actualizado = proveedoresService.editar(id, proveedor);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/proveedores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer id) {
        return proveedoresService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


