package com.sistemas.productos_alimentos.controller;


import com.sistemas.productos_alimentos.models.Sku;
import com.sistemas.productos_alimentos.service.SkuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/sku")
@CrossOrigin(origins = "*")
public class SkuController {

    private final SkuService skuService;


    public SkuController(SkuService skuService) {
        this.skuService = skuService;

    }


    // GET http://localhost:8080/api/sku
    @GetMapping
    public ResponseEntity<List<Sku>> listarTodos() {
        return ResponseEntity.ok(skuService.obtenerTodos());
    }

    // GET http://localhost:8080/api/sku/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Sku>> listarActivos() {
        return ResponseEntity.ok(skuService.obtenerActivos());
    }

    // GET http://localhost:8080/api/sku/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Sku> buscarPorId(@PathVariable Integer id) {
        Sku sku = skuService.buscarPorId(id);
        return sku != null ? ResponseEntity.ok(sku) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/sku/buscar?descripcion=leche
    @GetMapping("/buscar")
    public ResponseEntity<List<Sku>> buscarPorDescripcion(@RequestParam String descripcion) {
        return ResponseEntity.ok(skuService.buscarPorDescripcion(descripcion));
    }

    // GET http://localhost:8080/api/sku/categoria/{catId}
    @GetMapping("/categoria/{catId}")
    public ResponseEntity<List<Sku>> buscarPorCategoria(@PathVariable Integer catId) {
        return ResponseEntity.ok(skuService.buscarPorCategoria(catId));
    }

    // POST http://localhost:8080/api/sku
    @PostMapping
    public ResponseEntity<Sku> crearSku(@RequestBody Sku sku) {
        return ResponseEntity.ok(skuService.guardar(sku));
    }

    // PUT http://localhost:8080/api/sku/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Sku> editarSku(@PathVariable Integer id, @RequestBody Sku sku) {
        Sku actualizado = skuService.editar(id, sku);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/sku/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSku(@PathVariable Integer id) {
        return skuService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


