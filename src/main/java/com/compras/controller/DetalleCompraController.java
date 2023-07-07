package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Compra;
import com.compras.model.DetalleCompra;
import com.compras.service.DetalleCompraService;

@RestController
@RequestMapping("/detalle")
@CrossOrigin("http://localhost:4200")
public class DetalleCompraController {

	@Autowired
	DetalleCompraService detalleService;

	List<DetalleCompra> detalleActual = new ArrayList<>();

	/**
	 * @return Retorna un ResponseEntity con el listado actual de detalles.
	 */
	@GetMapping
	public ResponseEntity<List<DetalleCompra>> obtenerDetalleActual() {
		return ResponseEntity.ok(detalleActual);
	}

	/**
	 * @param detalle
	 * @return Agrega el detalle obtenido por parametros a la lista actual de
	 *         detalles. Retorna un ResponseEntity con el listado actual de
	 *         detalles.
	 */
	@PostMapping("/agregar")
	public ResponseEntity<List<DetalleCompra>> agregarDetalle(@RequestBody DetalleCompra detalle) {
		detalleActual.add(detalle);
		return ResponseEntity.ok(detalleActual);
	}

	/**
	 * @param compra
	 * @return Asigna la nueva compra a cada detalle de la lista y guarda los mismos
	 *         en la BBDD Retorna un ResponseEntity con un nuevo listado de
	 *         detalles;
	 */
	@PostMapping("/guardar")
	public ResponseEntity<List<DetalleCompra>> guardarDetalles(@RequestBody Compra compra) {
		for (DetalleCompra detalle : detalleActual) {
			detalle.setCompra(compra);
			detalleService.save(detalle);
		}
		detalleActual = new ArrayList<>();
		return ResponseEntity.ok(detalleActual);
	}

	/**
	 * @return Retorna un ResponseEntity con un nuevo listado de detalles
	 *         eliminandos todos los detalles agregados
	 */
	@GetMapping("/eliminar")
	public ResponseEntity<List<DetalleCompra>> eliminarDetallesDelListado() {
		detalleActual = new ArrayList<>();
		return ResponseEntity.ok(detalleActual);
	}

}
