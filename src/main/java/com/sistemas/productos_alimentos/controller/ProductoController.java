package com.sistemas.productos_alimentos.controller;


import com.sistemas.productos_alimentos.models.Producto;
import com.sistemas.productos_alimentos.models.ProductoId;
import com.sistemas.productos_alimentos.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET http://localhost:8080/api/productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    // GET http://localhost:8080/api/productos/id?comboId=5&periodo=202605
    @GetMapping("/id")
    public ResponseEntity<Producto> buscarPorId(@RequestParam Integer comboId, @RequestParam Integer periodo) {
        ProductoId id = new ProductoId(); // Asumiendo constructor con parámetros en tu ProductoId
        Producto producto = productoService.buscarPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/productos/periodo/{periodo}
    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<List<Producto>> buscarPorPeriodo(@PathVariable Integer periodo) {
        return ResponseEntity.ok(productoService.buscarPorPeriodo(periodo));
    }

    // GET http://localhost:8080/api/productos/combo/{comboId}
    @GetMapping("/combo/{comboId}")
    public ResponseEntity<List<Producto>> buscarPorCombo(@PathVariable Integer comboId) {
        return ResponseEntity.ok(productoService.buscarPorCombo(comboId));
    }

    // POST http://localhost:8080/api/productos
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    // PUT http://localhost:8080/api/productos/editar?comboId=5&periodo=202605
    @PutMapping("/editar")
    public ResponseEntity<Producto> editarProducto(
            @RequestParam Integer comboId,
            @RequestParam Integer periodo,
            @RequestBody Producto producto) {
        ProductoId id = new ProductoId();
        Producto actualizado = productoService.editar(id, producto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/productos/eliminar?comboId=5&periodo=202605
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarProducto(@RequestParam Integer comboId, @RequestParam Integer periodo) {
        ProductoId id = new ProductoId();
        return productoService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
