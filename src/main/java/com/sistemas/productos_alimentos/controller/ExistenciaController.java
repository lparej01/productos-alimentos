package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.dto.ExistenciaResponseDTO;
import com.sistemas.productos_alimentos.models.Existencia;
import com.sistemas.productos_alimentos.models.ExistenciaId;
import com.sistemas.productos_alimentos.models.Periodo;
import com.sistemas.productos_alimentos.service.ExistenciaService;
import com.sistemas.productos_alimentos.service.PeriodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/existencia")
@CrossOrigin(origins = "*")
public class ExistenciaController {

    private final ExistenciaService existenciaService;

    private final PeriodoService periodoService;

    // 🌟 2. La inyectamos en el constructor junto con ExistenciaService
    public ExistenciaController(ExistenciaService existenciaService, PeriodoService periodoService) {
        this.existenciaService = existenciaService;
        this.periodoService = periodoService;
    }

    // 🔄 REFORMULADO: GET http://localhost:8080/api/existencia
    @GetMapping
    public ResponseEntity<List<ExistenciaResponseDTO>> listarTodas() {
        // 🌟 3. Obtenemos el periodo activo dinámicamente desde el sistema
        Periodo periodoActivo = periodoService.obtenerPeriodoActual();

        if (periodoActivo == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // 🌟 4. Le pasamos el periodo al método del servicio para que filtre en SQLite
        List<ExistenciaResponseDTO> actuales = existenciaService.obtenerExistenciasDelPeriodo(periodoActivo.getIdPeriodo());
        return ResponseEntity.ok(actuales);
    }
    // GET http://localhost:8080/api/existencia/id?
    @GetMapping("/id")
    public ResponseEntity<Existencia> buscarPorId(@RequestParam Integer sku, @RequestParam Integer periodo) {
        ExistenciaId id = new ExistenciaId(); // Requiere constructor con parámetros en ExistenciaId
        Existencia existencia = existenciaService.buscarPorId(id);
        return existencia != null ? ResponseEntity.ok(existencia) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/existencias/periodo/{periodo}
    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<List<Existencia>> buscarPorPeriodo(@PathVariable Integer periodo) {
        return ResponseEntity.ok(existenciaService.buscarPorPeriodo(periodo));
    }

    // GET http://localhost:8080/api/existencias/sku/{sku}
    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<Existencia>> buscarPorSku(@PathVariable Integer sku) {
        return ResponseEntity.ok(existenciaService.buscarPorSku(sku));
    }

    // POST http://localhost:8080/api/existencias
    @PostMapping
    public ResponseEntity<Existencia> crearExistencia(@RequestBody Existencia existencia) {
        return ResponseEntity.ok(existenciaService.guardar(existencia));
    }

    // PUT http://localhost:8080/api/existencias/sku
    @PutMapping("/{sku}")
    public ResponseEntity<?> editarExistencia( // 🌟 Cambiamos a ResponseEntity<?> para poder devolver un mensaje de error textualmente si falla
                                               @PathVariable Integer sku,
                                               @RequestBody Map<String, Object> payload) {

        // 1. Extraemos el periodo del mapa de forma segura
        Integer periodoRecibido = null;
        if (payload.containsKey("periodo")) {
            periodoRecibido = (Integer) payload.get("periodo");
        } else if (payload.containsKey("idPeriodo")) {
            periodoRecibido = (Integer) payload.get("idPeriodo");
        } else if (payload.containsKey("id")) {
            Map<?, ?> idMap = (Map<?, ?>) payload.get("id");
            if (idMap != null && idMap.containsKey("periodo")) {
                periodoRecibido = (Integer) idMap.get("periodo");
            }
        }

        if (periodoRecibido == null) {
            return ResponseEntity.badRequest().body("El periodo es obligatorio.");
        }

        // 2. Construimos el ID compuesto y buscamos en la BD
        ExistenciaId id = new ExistenciaId();
        id.setSku(sku);
        id.setPeriodo(periodoRecibido);

        Existencia existenciaExistente = existenciaService.buscarPorId(id);
        if (existenciaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // 3. Extraemos la nueva merma que intentan ingresar
        double nuevaMerma = 0.0;
        if (payload.containsKey("merma")) {
            Number mermaNum = (Number) payload.get("merma");
            nuevaMerma = mermaNum.doubleValue();
        }

        // 4. 🛑 VALIDACIÓN DE SEGURIDAD CONTRA NEGATIVOS
        // Calculamos el inventario disponible ANTES de aplicar la nueva merma
        double stockDisponible = existenciaExistente.getInvInicial()
                + existenciaExistente.getEntradas()
                - existenciaExistente.getSalidas();

        if (stockDisponible <= 0) {
            // 🌟 Devolvemos un 200 OK, pero con un JSON que Angular puede leer pacíficamente
            return ResponseEntity.ok(Map.of(
                    "status", "advertencia",
                    "mensaje", "No se puede registrar merma porque el producto no cuenta con saldo disponible."
            ));
        }

        if (nuevaMerma > stockDisponible) {
            return ResponseEntity.ok(Map.of(
                    "status", "advertencia",
                    "mensaje", "La merma ingresada (" + nuevaMerma + ") supera al saldo disponible (" + stockDisponible + ")."
            ));
        }

        // 5. Si pasa las validaciones, asignamos la merma
        existenciaExistente.setMerma(nuevaMerma);

        // 6. 🧮 RECALCULAMOS EL INVENTARIO FINAL de manera segura
        double invFinal = stockDisponible - existenciaExistente.getMerma();
        existenciaExistente.setInvFinal(invFinal);

        // Datos de auditoría estándar
        if (payload.containsKey("usuario")) {
            existenciaExistente.setUsuario((String) payload.get("usuario"));
        }
        if (payload.containsKey("timestamp")) {
            existenciaExistente.setTimestamp((String) payload.get("timestamp"));
        }

        // 7. Guardamos en SQLite
        Existencia actualizada = existenciaService.guardar(existenciaExistente);
        return ResponseEntity.ok(actualizada);
    }

    // 🎯 AGREGA ESTE MÉTODO EN TU ExistenciaController
    @GetMapping("/{sku}/{periodo}")
    public ResponseEntity<Existencia> obtenerPorSkuAndPeriodo(
            @PathVariable Integer sku,
            @PathVariable Integer periodo) {

        ExistenciaId id = new ExistenciaId();
        id.setSku(sku);
        id.setPeriodo(periodo);

        Existencia existencia = existenciaService.buscarPorId(id);
        return existencia != null ? ResponseEntity.ok(existencia) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/existencias/eliminar?sku=1002&periodo=202605
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarExistencia(@RequestParam Integer sku, @RequestParam Integer periodo) {
        ExistenciaId id = new ExistenciaId();
        return existenciaService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


