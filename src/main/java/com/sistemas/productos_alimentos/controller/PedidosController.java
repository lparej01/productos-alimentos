package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.Pedidos;
import com.sistemas.productos_alimentos.service.PedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidosController {

    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    // GET http://localhost:8080/api/pedidos
    @GetMapping
    public ResponseEntity<List<Pedidos>> listarTodos() {
        return ResponseEntity.ok(pedidosService.obtenerTodos());
    }

    // GET http://localhost:8080/api/pedidos/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Pedidos>> listarActivos() {
        return ResponseEntity.ok(pedidosService.obtenerActivos());
    }

    // GET http://localhost:8080/api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> buscarPorId(@PathVariable Integer id) {
        Pedidos pedido = pedidosService.buscarPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/pedidos/buscar-fecha?fecha=2026-05-25
    @GetMapping("/buscar-fecha")
    public ResponseEntity<List<Pedidos>> buscarPorFecha(@RequestParam String fecha) {
        return ResponseEntity.ok(pedidosService.buscarPorFecha(fecha));
    }

    // GET http://localhost:8080/api/pedidos/proveedor/{provId}
    @GetMapping("/proveedor/{provId}")
    public ResponseEntity<List<Pedidos>> buscarPorProveedor(@PathVariable Integer provId) {
        return ResponseEntity.ok(pedidosService.buscarPorProveedor(provId));
    }

    // POST http://localhost:8080/api/pedidos
    @PostMapping
    // @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    public ResponseEntity<Pedidos> crearPedido(@RequestBody Pedidos pedido) {
        return ResponseEntity.ok(pedidosService.guardar(pedido));
    }

    // PUT http://localhost:8080/api/pedidos/{id}
    @PutMapping("/{id}")
    // @PreAuthorize("hasAnyAuthority('ADMIN_ROLE', 'USER_ROLE')")
    public ResponseEntity<Pedidos> editarPedido(@PathVariable Integer id, @RequestBody Pedidos pedido) {
        Pedidos actualizado = pedidosService.editar(id, pedido);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/pedidos/{id}
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id) {
        return pedidosService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
