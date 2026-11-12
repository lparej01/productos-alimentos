package com.sistemas.productos_alimentos.controller;


import com.sistemas.productos_alimentos.models.Plan;
import com.sistemas.productos_alimentos.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "*")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    // GET http://localhost:8080/api/planes
    @GetMapping
    public ResponseEntity<List<Plan>> listarTodos() {
        return ResponseEntity.ok(planService.obtenerTodos());
    }

    // GET http://localhost:8080/api/planes/activos
    @GetMapping("/activos")
    public ResponseEntity<List<Plan>> listarActivos() {
        return ResponseEntity.ok(planService.obtenerActivos());
    }

    // GET http://localhost:8080/api/planes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Plan> buscarPorId(@PathVariable Integer id) {
        Plan plan = planService.buscarPorId(id);
        return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/planes/tipo?tipo=Semanal
    @GetMapping("/tipo")
    public ResponseEntity<List<Plan>> buscarPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(planService.buscarPorTipo(tipo));
    }

    // GET http://localhost:8080/api/planes/buscar-fecha-plan?fecha=2026-05-25
    @GetMapping("/buscar-fecha-plan")
    public ResponseEntity<List<Plan>> buscarPorFechaPlan(@RequestParam String fecha) {
        return ResponseEntity.ok(planService.buscarPorFechaPlan(fecha));
    }

    // POST http://localhost:8080/api/planes
    @PostMapping
    public ResponseEntity<Plan> crearPlan(@RequestBody Plan plan) {
        return ResponseEntity.ok(planService.guardar(plan));
    }

    // PUT http://localhost:8080/api/planes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Plan> editarPlan(@PathVariable Integer id, @RequestBody Plan plan) {
        Plan actualizado = planService.editar(id, plan);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/planes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Integer id) {
        return planService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


