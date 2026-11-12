package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.Combo;
import com.sistemas.productos_alimentos.service.ComboService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/combos")
@CrossOrigin(origins = "*")
public class ComboController {

    private final ComboService comboService;

    public ComboController(ComboService comboService) {
        this.comboService = comboService;
    }

    // GET http://localhost:8080/api/combos
    @GetMapping
    public ResponseEntity<List<Combo>> listarTodos() {
        return ResponseEntity.ok(comboService.obtenerTodos());
    }

    // GET http://localhost:8080/api/combos/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Combo>> listarActivos() {
        return ResponseEntity.ok(comboService.obtenerActivos());
    }

    // GET http://localhost:8080/api/combos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Combo> buscarPorId(@PathVariable Integer id) {
        Combo combo = comboService.buscarPorId(id);
        return combo != null ? ResponseEntity.ok(combo) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/combos/buscar?descripcion=familiar
    @GetMapping("/buscar")
    public ResponseEntity<List<Combo>> buscarPorDescripcion(@RequestParam String descripcion) {
        return ResponseEntity.ok(comboService.buscarPorDescripcion(descripcion));
    }

    // POST http://localhost:8080/api/combos
    @PostMapping
    public ResponseEntity<Combo> crearCombo(@RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.guardar(combo));
    }

    // PUT http://localhost:8080/api/combos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Combo> editarCombo(@PathVariable Integer id, @RequestBody Combo combo) {
        Combo actualizado = comboService.editar(id, combo);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/combos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCombo(@PathVariable Integer id) {
        return comboService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

