package com.compras.service.impl;

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


	@Override
	public List<DetalleCompra> findByCompra(Compra compra) {
		return dCompraRepository.findByCompra(compra);
	}

	@Override
	public void save(Compra compra, List<DetalleCompra> detalles) {
		for (DetalleCompra d : detalles) {
			d.setCompra(compra);
			dCompraRepository.save(d);
		}
	}
}
