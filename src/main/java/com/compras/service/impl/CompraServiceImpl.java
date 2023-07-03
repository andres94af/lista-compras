package com.compras.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.model.Compra;
import com.compras.repository.CompraRepository;
import com.compras.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	CompraRepository compraRepository;

	@Override
	public Compra save(Compra compra) {
		return compraRepository.save(compra);
	}

	@Override
	public Optional<Compra> findById(Integer id) {
		return compraRepository.findById(id);
	}

	@Override
	public List<Compra> findAll() {
		return compraRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		compraRepository.deleteById(id);
	}

}