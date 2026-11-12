package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.MovInv;
import com.sistemas.productos_alimentos.service.MovInvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos-inventario")
@CrossOrigin(origins = "*")
public class MovInvController {

    private final MovInvService movInvService;

    public MovInvController(MovInvService movInvService) {
        this.movInvService = movInvService;
    }

    // GET http://localhost:8080/api/movimientos-inventario
    @GetMapping
    public ResponseEntity<List<MovInv>> listarTodos() {
        return ResponseEntity.ok(movInvService.obtenerTodos());
    }

    // GET http://localhost:8080/api/movimientos-inventario/activos
    @GetMapping("/activos")
    public ResponseEntity<List<MovInv>> listarActivos() {
        return ResponseEntity.ok(movInvService.obtenerActivos());
    }

    // GET http://localhost:8080/api/movimientos-inventario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MovInv> buscarPorId(@PathVariable Integer id) {
        MovInv mov = movInvService.buscarPorId(id);
        return mov != null ? ResponseEntity.ok(mov) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/movimientos-inventario/tipo?tipo=ENTRADA
    @GetMapping("/tipo")
    public ResponseEntity<List<MovInv>> buscarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(movInvService.buscarPorTipo(tipo));
    }

    // GET http://localhost:8080/api/movimientos-inventario/fecha?fecha=2026-05-25
    @GetMapping("/fecha")
    public ResponseEntity<List<MovInv>> buscarPorFecha(@RequestParam String fecha) {
        return ResponseEntity.ok(movInvService.buscarPorFecha(fecha));
    }

    // GET http://localhost:8080/api/movimientos-inventario/pedido/{pedidoId}
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<MovInv>> buscarPorPedido(@PathVariable Integer pedidoId) {
        return ResponseEntity.ok(movInvService.buscarPorPedido(pedidoId));
    }

    // POST http://localhost:8080/api/movimientos-inventario
    @PostMapping
    public ResponseEntity<MovInv> crearMovimiento(@RequestBody MovInv movInv) {
        return ResponseEntity.ok(movInvService.guardar(movInv));
    }

    // PUT http://localhost:8080/api/movimientos-inventario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MovInv> editarMovimiento(@PathVariable Integer id, @RequestBody MovInv movInv) {
        MovInv actualizado = movInvService.editar(id, movInv);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/movimientos-inventario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Integer id) {
        return movInvService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


