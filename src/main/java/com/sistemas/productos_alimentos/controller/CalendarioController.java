package com.sistemas.productos_alimentos.controller;


import com.sistemas.productos_alimentos.models.Calendario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sistemas.productos_alimentos.service.CalendarioService;


@RestController
@RequestMapping("/api/calendario")
@CrossOrigin(origins = "*")
public class CalendarioController {

    private final CalendarioService calendarioService;

    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }

    // GET http://localhost:8080/api/calendario
    @GetMapping
    public ResponseEntity<List<Calendario>> listarTodos() {
        return ResponseEntity.ok(calendarioService.obtenerTodos());
    }

    // GET http://localhost:8080/api/calendario/{id} (ej. /api/calendario/2026-05-25)
    @GetMapping("/{id}")
    public ResponseEntity<Calendario> buscarPorId(@PathVariable String id) {
        Calendario cal = calendarioService.buscarPorId(id);
        return cal != null ? ResponseEntity.ok(cal) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/calendario/anio/2026
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Calendario>> buscarPorAnio(@PathVariable Integer anio) {
        return ResponseEntity.ok(calendarioService.buscarPorAnio(anio));
    }

    // GET http://localhost:8080/api/calendario/filtrar?anio=2026&mes=5
    @GetMapping("/filtrar")
    public ResponseEntity<List<Calendario>> buscarPorMesYAnio(@RequestParam Integer anio, @RequestParam Integer mes) {
        return ResponseEntity.ok(calendarioService.buscarPorMesYAnio(anio, mes));
    }

    // GET http://localhost:8080/api/calendario/feriados
    @GetMapping("/feriados")
    public ResponseEntity<List<Calendario>> listarFeriados() {
        return ResponseEntity.ok(calendarioService.obtenerFeriados());
    }

    // GET http://localhost:8080/api/calendario/weekends
    @GetMapping("/weekends")
    public ResponseEntity<List<Calendario>> listarWeekends() {
        return ResponseEntity.ok(calendarioService.obtenerFinesDeSemana());
    }

    // POST http://localhost:8080/api/calendario
    @PostMapping
    public ResponseEntity<Calendario> crearFecha(@RequestBody Calendario calendario) {
        return ResponseEntity.ok(calendarioService.guardar(calendario));
    }

    // PUT http://localhost:8080/api/calendario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Calendario> editarFecha(@PathVariable String id, @RequestBody Calendario calendario) {
        Calendario actualizado = calendarioService.editar(id, calendario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/calendario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFecha(@PathVariable String id) {
        return calendarioService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
