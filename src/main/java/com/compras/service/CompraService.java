package com.compras.service;

import java.util.List;
import java.util.Optional;

import com.compras.model.Compra;

public interface CompraService {

	Compra save(Compra compra);

	Optional<Compra> findById(Integer id);

	List<Compra> findAll();

	void delete(Integer id);

}
