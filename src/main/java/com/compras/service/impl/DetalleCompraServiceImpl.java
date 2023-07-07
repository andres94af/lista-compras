package com.compras.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compras.model.Compra;
import com.compras.model.DetalleCompra;
import com.compras.repository.DetalleCompraRepository;
import com.compras.service.DetalleCompraService;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

	@Autowired
	DetalleCompraRepository dCompraRepository;

	List<DetalleCompra> detalleActual = new ArrayList<>();

	@Override
	public List<DetalleCompra> findByCompra(Compra compra) {
		return dCompraRepository.findByCompra(compra);
	}

	@Override
	public void save(Compra compra) {
		for (DetalleCompra d : detalleActual) {
			d.setCompra(compra);
			dCompraRepository.save(d);
		}
	}

	@Override
	public List<DetalleCompra> obtenerDetalleActual() {
		return detalleActual;
	}

	@Override
	public List<DetalleCompra> agregarAlDetalleActual(DetalleCompra detalle) {
		detalleActual.add(detalle);
		return detalleActual;
	}

	@Override
	public List<DetalleCompra> borrarDetalleActual() {
		detalleActual = new ArrayList<>();
		return detalleActual;
	}

}
