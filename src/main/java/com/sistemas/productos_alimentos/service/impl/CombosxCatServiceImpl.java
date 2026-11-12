package com.sistemas.productos_alimentos.service.impl;



import com.sistemas.productos_alimentos.models.CombosxCat;
import com.sistemas.productos_alimentos.models.CombosxCatId;
import com.sistemas.productos_alimentos.repository.CombosxCatRepository;
import com.sistemas.productos_alimentos.service.CombosxCatService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CombosxCatServiceImpl implements CombosxCatService {

    private final CombosxCatRepository combosxCatRepository;

    public CombosxCatServiceImpl(CombosxCatRepository combosxCatRepository) {
        this.combosxCatRepository = combosxCatRepository;
    }

    @Override
    public List<CombosxCat> obtenerTodos() {
        return combosxCatRepository.findAll();
    }

    @Override
    public List<CombosxCat> obtenerActivos() {
        return combosxCatRepository.findByActivo(1);
    }

    @Override
    public CombosxCat buscarPorId(CombosxCatId id) {
        return combosxCatRepository.findById(id).orElse(null);
    }

    @Override
    public List<CombosxCat> buscarPorCategoria(Integer catId) {
        return combosxCatRepository.findById_CatId(catId);
    }

    @Override
    public List<CombosxCat> buscarPorCombo(Integer comboId) {
        return combosxCatRepository.findById_ComboId(comboId);
    }

    @Override
    public CombosxCat guardar(CombosxCat combosxCat) {
        return combosxCatRepository.save(combosxCat);
    }

    @Override
    public CombosxCat editar(CombosxCatId id, CombosxCat detalles) {
        Optional<CombosxCat> opcion = combosxCatRepository.findById(id);
        if (opcion.isPresent()) {
            CombosxCat existente = opcion.get();

            // Mapeo de columnas editables de la tabla intermedia
            existente.setCantUM(detalles.getCantUM());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return combosxCatRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(CombosxCatId id) {
        if (combosxCatRepository.existsById(id)) {
            combosxCatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

