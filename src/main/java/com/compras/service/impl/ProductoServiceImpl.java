package com.compras.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.model.Categoria;
import com.compras.model.Producto;
import com.compras.repository.ProductoRepository;
import com.compras.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Override
	public Optional<Producto> findById(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public List<Producto> findAll() {
		List<Producto> listaDesordenada = productoRepository.findAll();
		List<Producto> listaOrdenada = listaDesordenada.stream().sorted(Comparator.comparing(Producto::getNombre))
				.toList();
		return listaOrdenada;
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public List<Producto> findByCategoria(Categoria categoria) {
		List<Producto> listaDesordenada = productoRepository.findByCategoria(categoria);
		List<Producto> listaOrdenada = listaDesordenada.stream().sorted(Comparator.comparing(Producto::getNombre))
				.toList();
		return listaOrdenada;
	}

	@Override
	public List<Producto> findFiltered(String valorFiltro) {
		List<Producto> listadoCompleto = findAll();
		listadoCompleto = listadoCompleto.stream().filter(p -> p.getNombre().toLowerCase().contains(valorFiltro.toLowerCase())).toList();
		return listadoCompleto;
	}

}
