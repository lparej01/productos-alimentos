package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.Periodo;
import com.sistemas.productos_alimentos.repository.ExistenciaRepository;
import com.sistemas.productos_alimentos.repository.PeriodoRepository;
import com.sistemas.productos_alimentos.service.PeriodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PeriodoServiceImpl implements PeriodoService {


    private final PeriodoRepository periodoRepository;
    private final ExistenciaRepository existenciaRepository;

    @Override
    public List<Periodo> obtenerTodos() {
        return periodoRepository.findAll();
    }

    @Override
    public Periodo buscarPorId(Integer idPeriodo) {
        return periodoRepository.findById(idPeriodo).orElse(null);
    }

    @Override
    public List<Periodo> buscarPorAnio(Integer anio) {
        return periodoRepository.findByAnio(anio);
    }

    @Override
    public Periodo obtenerPeriodoActual() {
        return periodoRepository.findByEsActual(1).orElse(null);
    }

    @Override
    public List<Periodo> obtenerPeriodosAbiertos() {
        return periodoRepository.findByEsCerrado(0); // 0 significa abierto
    }

    @Override
    public Periodo guardar(Periodo periodo) {
        return periodoRepository.save(periodo);
    }

    @Override
    public Periodo editar(Integer idPeriodo, Periodo detalles) {
        Optional<Periodo> opcion = periodoRepository.findById(idPeriodo);
        if (opcion.isPresent()) {
            Periodo existente = opcion.get();

            existente.setAnio(detalles.getAnio());
            existente.setMes(detalles.getMes());
            existente.setMesNombre(detalles.getMesNombre());
            existente.setEsCerrado(detalles.getEsCerrado());
            existente.setEsActual(detalles.getEsActual());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return periodoRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer idPeriodo) {
        if (periodoRepository.existsById(idPeriodo)) {
            periodoRepository.deleteById(idPeriodo);
            return true;
        }
        return false;
    }

    @Override
    public void aperturarSiguientePeriodo(String usuarioAnfitrion) {
        // 1. Obtener el periodo actual activo
        Periodo periodoActual = periodoRepository.findByEsActual(1)
                .orElseThrow(() -> new RuntimeException("No hay ningún periodo activo actualmente."));

        Integer idActual = periodoActual.getIdPeriodo();

        // 2. Calcular cronológicamente el siguiente periodo
        Integer idSiguiente = calcularSiguientePeriodo(idActual);

        // 3. Crear el nuevo periodo en la base de datos
        Periodo nuevoPeriodo = new Periodo();
        nuevoPeriodo.setIdPeriodo(idSiguiente); // Ejemplo: 202502

        int anio = idSiguiente / 100;
        int numeroMes = idSiguiente % 100; // ⚠️ CORRECCIÓN: El mes se obtiene con residuo 100 del idSiguiente, no del año.

        // Definimos los meses
        String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
                "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

        // ⚠️ CORRECCIÓN: Los arrays en Java empiezan en 0. Si el mes es 2 (Febrero), debes buscar meses[1].
        String nombreMes = "DESCONOCIDO";
        if (numeroMes >= 1 && numeroMes <= 12) {
            nombreMes = meses[numeroMes - 1];
        }

        // Formateador de tiempo
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestampNuevoPeriodo = LocalDateTime.now().format(formateador);

        nuevoPeriodo.setEsActual(1); // Abierto
        nuevoPeriodo.setEsCerrado(0);
        nuevoPeriodo.setAnio(anio);
        nuevoPeriodo.setMes(numeroMes);
        nuevoPeriodo.setMesNombre(nombreMes); // El mes ya es dinámico según el idSiguiente
        nuevoPeriodo.setTimestamp(timestampNuevoPeriodo);
        nuevoPeriodo.setUsuario(usuarioAnfitrion);

        periodoRepository.save(nuevoPeriodo);

        // 4. Copiar existencias de SKU activos al nuevo periodo
        String timestampActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        existenciaRepository.aperturarNuevoPeriodo(idActual, idSiguiente, timestampActual, usuarioAnfitrion);

        // 5. Cerrar el periodo anterior automáticamente
        periodoActual.setEsActual(0);
        periodoActual.setEsCerrado(1); // Cerrado / Histórico

        periodoRepository.save(periodoActual);
    }
    @Override
    public Integer calcularSiguientePeriodo(Integer periodoActual) {
        int anio = periodoActual / 100;  //me da el año
        int mes = periodoActual % 100;

        mes++;
        if (mes > 12) {
            mes = 1;
            anio++;
        }

        return (anio * 100) + mes;
    }


}
