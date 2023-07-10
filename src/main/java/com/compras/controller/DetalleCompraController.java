package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Compra;
import com.compras.model.DetalleCompra;
import com.compras.service.CompraService;
import com.compras.service.DetalleCompraService;

@RestController
@RequestMapping("/detalle")
public class DetalleCompraController {

	@Autowired
	DetalleCompraService detalleService;

	@Autowired
	CompraService compraService;

	/**
	 * @param idCompra
	 * @return Retorna un ResponseEntity con un listado de los detalles de una
	 *         compra con el idCompra pasado por parámetro. Si la compra no existe
	 *         retorna un estado "No content".
	 */
	@GetMapping("/{idCompra}")
	public ResponseEntity<List<DetalleCompra>> detallesDeCompra(@PathVariable Integer idCompra) {
		Optional<Compra> compra = compraService.findById(idCompra);
		if (compra.isPresent()) {
			List<DetalleCompra> listado = new ArrayList<>();
			listado = detalleService.findByCompra(compra.get());
			return ResponseEntity.ok(listado);
		}
		return ResponseEntity.noContent().build();
	}
}
