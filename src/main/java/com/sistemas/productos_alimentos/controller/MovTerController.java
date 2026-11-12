package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.MovTer;
import com.sistemas.productos_alimentos.service.MovTerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos-producto-terminado")
@CrossOrigin(origins = "*")
public class MovTerController {

    private final MovTerService movTerService;

    public MovTerController(MovTerService movTerService) {
        this.movTerService = movTerService;
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado
    @GetMapping
    public ResponseEntity<List<MovTer>> listarTodos() {
        return ResponseEntity.ok(movTerService.obtenerTodos());
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado/activos
    @GetMapping("/activos")
    public ResponseEntity<List<MovTer>> listarActivos() {
        return ResponseEntity.ok(movTerService.obtenerActivos());
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MovTer> buscarPorId(@PathVariable Integer id) {
        MovTer mov = movTerService.buscarPorId(id);
        return mov != null ? ResponseEntity.ok(mov) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado/tipo?tipo=ENTRADA
    @GetMapping("/tipo")
    public ResponseEntity<List<MovTer>> buscarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(movTerService.buscarPorTipo(tipo));
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado/fecha?fecha=2026-05-25
    @GetMapping("/fecha")
    public ResponseEntity<List<MovTer>> buscarPorFecha(@RequestParam String fecha) {
        return ResponseEntity.ok(movTerService.buscarPorFecha(fecha));
    }

    // GET http://localhost:8080/api/movimientos-producto-terminado/plan/{planId}
    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<MovTer>> buscarPorPlan(@PathVariable Integer planId) {
        return ResponseEntity.ok(movTerService.buscarPorPlan(planId));
    }

    // POST http://localhost:8080/api/movimientos-producto-terminado
    @PostMapping
    public ResponseEntity<MovTer> crearMovimiento(@RequestBody MovTer movTer) {
        return ResponseEntity.ok(movTerService.guardar(movTer));
    }

    // PUT http://localhost:8080/api/movimientos-producto-terminado/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MovTer> editarMovimiento(@PathVariable Integer id, @RequestBody MovTer movTer) {
        MovTer actualizado = movTerService.editar(id, movTer);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/movimientos-producto-terminado/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Integer id) {
        return movTerService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


