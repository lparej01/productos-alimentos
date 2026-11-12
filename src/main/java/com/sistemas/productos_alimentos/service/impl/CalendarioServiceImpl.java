package com.sistemas.productos_alimentos.service.impl;



import com.sistemas.productos_alimentos.models.Calendario;
import com.sistemas.productos_alimentos.repository.CalendarioRepository;
import com.sistemas.productos_alimentos.service.CalendarioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioServiceImpl implements CalendarioService {

    private final CalendarioRepository calendarioRepository;

    public CalendarioServiceImpl(CalendarioRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }

    @Override
    public List<Calendario> obtenerTodos() {
        return calendarioRepository.findAll();
    }

    @Override
    public Calendario buscarPorId(String id) {
        return calendarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Calendario> buscarPorAnio(Integer anio) {
        return calendarioRepository.findByAnio(anio);
    }

    @Override
    public List<Calendario> buscarPorMesYAnio(Integer anio, Integer mes) {
        return calendarioRepository.findByAnioAndMes(anio, mes);
    }

    @Override
    public List<Calendario> obtenerFeriados() {
        return calendarioRepository.findByEsFeriado(1);
    }

    @Override
    public List<Calendario> obtenerFinesDeSemana() {
        return calendarioRepository.findByEsWeekend(1);
    }

    @Override
    public Calendario guardar(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    @Override
    public Calendario editar(String id, Calendario detalles) {
        Optional<Calendario> opcion = calendarioRepository.findById(id);
        if (opcion.isPresent()) {
            Calendario existente = opcion.get();

            existente.setPeriodo(detalles.getPeriodo());
            existente.setDia(detalles.getDia());
            existente.setMes(detalles.getMes());
            existente.setAnio(detalles.getAnio());
            existente.setDiaSem(detalles.getDiaSem());
            existente.setDiaSemNum(detalles.getDiaSemNum());
            existente.setSemIso(detalles.getSemIso());
            existente.setNombreMes(detalles.getNombreMes());
            existente.setEsWeekend(detalles.getEsWeekend());
            existente.setEsFeriado(detalles.getEsFeriado());

            return calendarioRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(String id) {
        if (calendarioRepository.existsById(id)) {
            calendarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

