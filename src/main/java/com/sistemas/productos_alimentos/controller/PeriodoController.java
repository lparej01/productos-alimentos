package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.Periodo;
import com.sistemas.productos_alimentos.service.PeriodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/periodos")
@CrossOrigin(origins = "*")
public class PeriodoController {

    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    // GET http://localhost:8080/api/periodos
    @GetMapping
    public ResponseEntity<List<Periodo>> listarTodos() {
        return ResponseEntity.ok(periodoService.obtenerTodos());
    }

    // GET http://localhost:8080/api/periodos/{id} (ej: /api/periodos/202605)
    @GetMapping("/{id}")
    public ResponseEntity<Periodo> buscarPorId(@PathVariable Integer id) {
        Periodo periodo = periodoService.buscarPorId(id);
        return periodo != null ? ResponseEntity.ok(periodo) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/periodos/actual
    @GetMapping("/actual")
    public ResponseEntity<Periodo> obtenerActual() {
        Periodo periodo = periodoService.obtenerPeriodoActual();
        return periodo != null ? ResponseEntity.ok(periodo) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/periodos/anio/{anio}
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Periodo>> buscarPorAnio(@PathVariable Integer anio) {
        return ResponseEntity.ok(periodoService.buscarPorAnio(anio));
    }

    // GET http://localhost:8080/api/periodos/abiertos
    @GetMapping("/abiertos")
    public ResponseEntity<List<Periodo>> listarAbiertos() {
        return ResponseEntity.ok(periodoService.obtenerPeriodosAbiertos());
    }

    // POST http://localhost:8080/api/periodos
    @PostMapping
    public ResponseEntity<Periodo> crearPeriodo(@RequestBody Periodo periodo) {
        return ResponseEntity.ok(periodoService.guardar(periodo));
    }

    // PUT http://localhost:8080/api/periodos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Periodo> editarPeriodo(@PathVariable Integer id, @RequestBody Periodo periodo) {
        Periodo actualizado = periodoService.editar(id, periodo);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/periodos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPeriodo(@PathVariable Integer id) {
        return periodoService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @PostMapping("/aperturar")
    public ResponseEntity<Map<String, String>> aperturar(@RequestParam String usuarioAnfitrion) {
        periodoService.aperturarSiguientePeriodo(usuarioAnfitrion);

        // Creamos una respuesta JSON válida
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Periodo aperturado correctamente");

        return ResponseEntity.ok(respuesta); // Devuelve un HTTP 200 con el JSON
    }

}


