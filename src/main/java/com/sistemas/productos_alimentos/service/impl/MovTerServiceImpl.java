package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.MovTer;
import com.sistemas.productos_alimentos.repository.MovTerRepository;
import com.sistemas.productos_alimentos.service.MovTerService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovTerServiceImpl implements MovTerService {

    private final MovTerRepository movTerRepository;

    public MovTerServiceImpl(MovTerRepository movTerRepository) {
        this.movTerRepository = movTerRepository;
    }

    @Override
    public List<MovTer> obtenerTodos() {
        return movTerRepository.findAll();
    }

    @Override
    public List<MovTer> obtenerActivos() {
        return movTerRepository.findByActivo(1);
    }

    @Override
    public MovTer buscarPorId(Integer movTerId) {
        return movTerRepository.findById(movTerId).orElse(null);
    }

    @Override
    public List<MovTer> buscarPorTipo(String tipoMovTer) {
        return movTerRepository.findByTipoMovTer(tipoMovTer);
    }

    @Override
    public List<MovTer> buscarPorFecha(String fechaMovTer) {
        return movTerRepository.findByFechaMovTer(fechaMovTer);
    }

    @Override
    public List<MovTer> buscarPorPlan(Integer planId) {
        return movTerRepository.findByPlan_PlanId(planId);
    }

    @Override
    public MovTer guardar(MovTer movTer) {
        return movTerRepository.save(movTer);
    }

    @Override
    public MovTer editar(Integer movTerId, MovTer detalles) {
        Optional<MovTer> opcion = movTerRepository.findById(movTerId);
        if (opcion.isPresent()) {
            MovTer existente = opcion.get();

            existente.setCombo(detalles.getCombo());
            existente.setTipoMovTer(detalles.getTipoMovTer());
            existente.setFechaMovTer(detalles.getFechaMovTer());
            existente.setCant(detalles.getCant());
            existente.setPlan(detalles.getPlan());
            existente.setReferencia(detalles.getReferencia());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return movTerRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer movTerId) {
        if (movTerRepository.existsById(movTerId)) {
            movTerRepository.deleteById(movTerId);
            return true;
        }
        return false;
    }


}

