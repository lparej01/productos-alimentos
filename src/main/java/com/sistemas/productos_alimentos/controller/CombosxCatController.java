package com.sistemas.productos_alimentos.controller;

import com.sistemas.productos_alimentos.models.CombosxCat;
import com.sistemas.productos_alimentos.models.CombosxCatId;
import com.sistemas.productos_alimentos.service.CombosxCatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/combos-categorias")
@CrossOrigin(origins = "*")
public class CombosxCatController {

    private final CombosxCatService combosxCatService;

    public CombosxCatController(CombosxCatService combosxCatService) {
        this.combosxCatService = combosxCatService;
    }
    // GET http://localhost:8080/api/combos-categorias/ping
    @GetMapping("/ping")
    public ResponseEntity<String> pingPrueba() {
        return ResponseEntity.ok("El controlador está vivo y la seguridad lo dejó pasar");
    }

    // GET http://localhost:8080/api/combos-categorias
    @GetMapping
    public ResponseEntity<List<CombosxCat>> listarTodos() {
        return ResponseEntity.ok(combosxCatService.obtenerTodos());
    }

    // GET http://localhost:8080/api/combos-categorias/activos
    @GetMapping("/activos")
    public ResponseEntity<List<CombosxCat>> listarActivos() {
        return ResponseEntity.ok(combosxCatService.obtenerActivos());
    }

    // GET http://localhost:8080/api/combos-categorias/id?catId=1&comboId=5
    @GetMapping("/id")
    public ResponseEntity<CombosxCat> buscarPorId(@RequestParam Integer catId, @RequestParam Integer comboId) {
        CombosxCatId id = new CombosxCatId();
        // 🌟 ASIGNA LOS VALORES RECIBIDOS:
        id.setCatId(catId);
        id.setComboId(comboId);

        CombosxCat resultado = combosxCatService.buscarPorId(id);
        return resultado != null ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    // GET http://localhost:8080/api/combos-categorias/categoria/{catId}
    @GetMapping("/categoria/{catId}")
    public ResponseEntity<List<CombosxCat>> buscarPorCategoria(@PathVariable Integer catId) {
        return ResponseEntity.ok(combosxCatService.buscarPorCategoria(catId));
    }

    // GET http://localhost:8080/api/combos-categorias/combo/{comboId}
    @GetMapping("/combo/{comboId}")
    public ResponseEntity<List<CombosxCat>> buscarPorCombo(@PathVariable Integer comboId) {
        return ResponseEntity.ok(combosxCatService.buscarPorCombo(comboId));
    }

    // POST http://localhost:8080/api/combos-categorias
    @PostMapping
    public ResponseEntity<CombosxCat> crearRelacion(@RequestBody CombosxCat combosxCat) {
        return ResponseEntity.ok(combosxCatService.guardar(combosxCat));
    }

    // PUT http://localhost:8080/api/combos-categorias/editar?catId=1&comboId=5
    @PutMapping("/editar")
    public ResponseEntity<CombosxCat> editarRelacion(
            @RequestParam Integer catId,
            @RequestParam Integer comboId,
            @RequestBody CombosxCat combosxCat) {
        CombosxCatId id = new CombosxCatId();
        // 🌟 ASIGNA LOS VALORES RECIBIDOS:
        id.setCatId(catId);
        id.setComboId(comboId);

        CombosxCat actualizado = combosxCatService.editar(id, combosxCat);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE http://localhost:8080/api/combos-categorias/eliminar?catId=1&comboId=5
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarRelacion(@RequestParam Integer catId, @RequestParam Integer comboId) {
        CombosxCatId id = new CombosxCatId();
        // 🌟 ASIGNA LOS VALORES RECIBIDOS:
        id.setCatId(catId);
        id.setComboId(comboId);

        return combosxCatService.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


