package com.compras.service;

import java.util.List;

import com.compras.model.Categoria;

public interface CategoriaService {

	Categoria save(Categoria categoria);

	List<Categoria> findAll();

}
