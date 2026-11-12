package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.Combo;
import com.sistemas.productos_alimentos.repository.ComboRepository;
import com.sistemas.productos_alimentos.service.ComboService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComboServiceImpl implements ComboService {

    private final ComboRepository comboRepository;

    public ComboServiceImpl(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    @Override
    public List<Combo> obtenerTodos() {
        return comboRepository.findAll();
    }

    @Override
    public List<Combo> obtenerActivos() {
        return comboRepository.findByActivo(1);
    }

    @Override
    public Combo buscarPorId(Integer comboId) {
        return comboRepository.findById(comboId).orElse(null);
    }

    @Override
    public List<Combo> buscarPorDescripcion(String descCombo) {
        return comboRepository.findByDescComboContainingIgnoreCase(descCombo);
    }

    @Override
    public Combo guardar(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo editar(Integer comboId, Combo detalles) {
        Optional<Combo> opcion = comboRepository.findById(comboId);
        if (opcion.isPresent()) {
            Combo existente = opcion.get();

            existente.setDescCombo(detalles.getDescCombo());
            existente.setPeso(detalles.getPeso());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return comboRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer comboId) {
        if (comboRepository.existsById(comboId)) {
            comboRepository.deleteById(comboId);
            return true;
        }
        return false;
    }
}
