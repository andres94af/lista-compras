package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Compra;
import com.compras.model.Usuario;
import com.compras.service.CompraService;
import com.compras.service.DetalleCompraService;
import com.compras.service.UsuarioService;

@RestController
@RequestMapping("/compra")
@CrossOrigin("http://localhost:4200")
public class CompraController {

	@Autowired
	CompraService compraService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DetalleCompraService detalleCompraService;

	// Retorna listado de todas las compras registradas
	@GetMapping("/{idCompra}")
	public ResponseEntity<Compra> compra(@PathVariable Integer idCompra) {
		Compra compra = compraService.findById(idCompra).get();
		return ResponseEntity.ok(compra);
	}

	/**
	 * @param idUsuario
	 * @return Retorna un ResponseEntity con un listado de todas las compras hechas
	 *         por un usuario con id pasado por parametro. Si el usuario no existe
	 *         retorna un estado "No content".
	 */
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Compra>> listaDeComprasPorUsuario(@PathVariable Integer idUsuario) {
		Optional<Usuario> usuario = usuarioService.findById(idUsuario);
		if (usuario.isPresent()) {
			List<Compra> listado = new ArrayList<>();
			listado = compraService.findAllByUsuario(usuario.get());
			return ResponseEntity.ok(listado);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{idUsuario}")
	public ResponseEntity<Compra> generarNuevaCompra(@RequestBody Compra compra, @PathVariable Integer idUsuario) {
		Optional<Usuario> usuarioOpt = usuarioService.findById(idUsuario);
		if (usuarioOpt.isPresent()) {
			compra.setUsuario(usuarioOpt.get());
			Compra nuevaCompra = compraService.save(compra);
			detalleCompraService.save(compra);
			return ResponseEntity.ok(nuevaCompra);
		}
		return ResponseEntity.ok(compra);
	}

}
