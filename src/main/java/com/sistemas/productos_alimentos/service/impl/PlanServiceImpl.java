package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.Plan;
import com.sistemas.productos_alimentos.repository.PlanRepository;
import com.sistemas.productos_alimentos.service.PlanService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public List<Plan> obtenerTodos() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> obtenerActivos() {
        return planRepository.findByActivo(1);
    }

    @Override
    public Plan buscarPorId(Integer id) {
        return planRepository.findById(id).orElse(null);
    }

    @Override
    public List<Plan> buscarPorTipo(String tipoPlan) {
        return planRepository.findByTipoPlan(tipoPlan);
    }

    @Override
    public List<Plan> buscarPorFechaPlan(String fechaPlan) {
        return planRepository.findByFechaPlan(fechaPlan);
    }

    @Override
    public List<Plan> buscarPorFechaProd(String fechaProd) {
        return planRepository.findByFechaProd(fechaProd);
    }

    @Override
    public Plan guardar(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan editar(Integer id, Plan detalles) {
        Optional<Plan> opcion = planRepository.findById(id);
        if (opcion.isPresent()) {
            Plan existente = opcion.get();

            existente.setComboPlan(detalles.getComboPlan());
            existente.setComboProd(detalles.getComboProd());
            existente.setTipoPlan(detalles.getTipoPlan());
            existente.setFechaPlan(detalles.getFechaPlan());
            existente.setFechaProd(detalles.getFechaProd());
            existente.setCantCombosPlan(detalles.getCantCombosPlan());
            existente.setCantCombosProd(detalles.getCantCombosProd());
            existente.setStRequisicion(detalles.getStRequisicion());
            existente.setStProduccion(detalles.getStProduccion());
            existente.setMerma(detalles.getMerma());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return planRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

