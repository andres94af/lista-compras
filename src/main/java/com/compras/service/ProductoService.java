package com.compras.service;

import java.util.List;
import java.util.Optional;

import com.compras.model.Categoria;
import com.compras.model.Producto;

public interface ProductoService {

	Optional<Producto> findById(Integer id);

	List<Producto> findAll();

	void delete(Integer id);

	Producto save(Producto producto);
	
	List<Producto> findByCategoria(Categoria categoria);

	List<Producto> findFiltered(String valorFiltro);

}
