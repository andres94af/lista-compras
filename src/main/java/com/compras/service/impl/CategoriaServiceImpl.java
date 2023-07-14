package com.compras.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compras.model.Categoria;
import com.compras.repository.CategoriaRepository;
import com.compras.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	/**
	 * Verifica que no exista una categoria con el mismo nombre que viene por
	 * parametro. Si no existe, la guarda en la BBDD. Si existe, retorna la
	 * categoria existente
	 */
	@Override
	public Categoria save(Categoria categoria) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findByNombre(categoria.getNombre());
		if (!categoriaOpt.isPresent()) {
			return categoriaRepository.save(categoria);
		}
		return categoriaOpt.get();
	}

	/**
	 * Retorna un listado ordenado alfabeticamente de categorias existentes en la
	 * BBDD
	 */
	@Override
	public List<Categoria> findAll() {
		List<Categoria> lista = categoriaRepository.findAll();
		lista = lista.stream().sorted(Comparator.comparing(Categoria::getNombre)).toList();
		return lista;
	}

	/**
	 * Retorna un Optional<> de la entidad Categoria, para luego verificar si existe
	 * o no.
	 */
	@Override
	public Optional<Categoria> findById(Integer id) {
		return categoriaRepository.findById(id);
	}

}
