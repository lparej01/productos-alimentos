package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.Proveedores;
import com.sistemas.productos_alimentos.repository.ProveedoresRepository;
import com.sistemas.productos_alimentos.service.ProveedoresService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresServiceImpl(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<Proveedores> obtenerTodos() {
        return proveedoresRepository.findAll();
    }

    @Override
    public List<Proveedores> obtenerActivos() {
        return proveedoresRepository.findByActivo(1);
    }

    @Override
    public Proveedores buscarPorId(Integer provId) {
        return proveedoresRepository.findById(provId).orElse(null);
    }

    @Override
    public List<Proveedores> buscarPorNombre(String nombre) {
        return proveedoresRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Proveedores guardar(Proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public Proveedores editar(Integer provId, Proveedores detalles) {
        Optional<Proveedores> opcion = proveedoresRepository.findById(provId);
        if (opcion.isPresent()) {
            Proveedores existente = opcion.get();

            existente.setNombre(detalles.getNombre());
            existente.setContacto(detalles.getContacto());
            existente.setTelf1(detalles.getTelf1());
            existente.setTelf2(detalles.getTelf2());
            existente.setTelfContacto(detalles.getTelfContacto());
            existente.setEmail(detalles.getEmail());
            existente.setEmailContacto(detalles.getEmailContacto());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return proveedoresRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer provId) {
        if (proveedoresRepository.existsById(provId)) {
            proveedoresRepository.deleteById(provId);
            return true;
        }
        return false;
    }
}

