package com.compras.service;

import java.util.List;
import java.util.Optional;

import com.compras.model.Compra;
import com.compras.model.Usuario;

public interface CompraService {

	Compra save(Compra compra);

	Optional<Compra> findById(Integer id);

	List<Compra> findAll();

	void delete(Integer id);

	List<Compra> findAllByUsuario(Usuario usuario);

	Compra update(Compra compra);

}
