package com.compras.service;

import java.util.List;
import com.compras.model.Compra;
import com.compras.model.DetalleCompra;

public interface DetalleCompraService {

	List<DetalleCompra> findByCompra(Compra compra);

	void save(Compra compra);

	List<DetalleCompra> obtenerDetalleActual();

	List<DetalleCompra> agregarAlDetalleActual(DetalleCompra detalle);

	List<DetalleCompra> borrarDetalleActual();

}
