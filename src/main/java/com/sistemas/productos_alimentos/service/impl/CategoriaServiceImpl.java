package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.Categoria;
import com.sistemas.productos_alimentos.repository.CategoriaRepository;
import com.sistemas.productos_alimentos.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> obtenerActivas() {
        // En SQLite el entero 1 suele representar el estado activo
        return categoriaRepository.findByActivo(1);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria editar(Integer id, Categoria categoriaDetalles) {
        Optional<Categoria> opcion = categoriaRepository.findById(id);
        if (opcion.isPresent()) {
            Categoria existente = opcion.get();

            // Modificamos todos los campos usando los métodos que genera Lombok
            existente.setCategoria(categoriaDetalles.getCategoria());
            existente.setCostoUnitario(categoriaDetalles.getCostoUnitario());
            existente.setPrecio(categoriaDetalles.getPrecio());
            existente.setPeso(categoriaDetalles.getPeso());
            existente.setActivo(categoriaDetalles.getActivo());
            existente.setTimestamp(categoriaDetalles.getTimestamp());
            existente.setUsuario(categoriaDetalles.getUsuario());

            return categoriaRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Categoria> buscarPorNombre(String nombre) {
        // Llama directamente al método "ContainingIgnoreCase" que ya creaste en tu repositorio
        return categoriaRepository.findByCategoriaContainingIgnoreCase(nombre);
    }
}
