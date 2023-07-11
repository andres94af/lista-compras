package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Compra;
import com.compras.model.CompraDetallesDTO;
import com.compras.model.Usuario;
import com.compras.security.jwt.JwtUtils;
import com.compras.service.CompraService;
import com.compras.service.DetalleCompraService;
import com.compras.service.UsuarioService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	CompraService compraService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DetalleCompraService detalleCompraService;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * @param idUsuario
	 * @return Retorna un ResponseEntity con un listado de todas las compras hechas
	 *         por un usuario con id pasado por parametro. Si el usuario no existe
	 *         retorna un estado "No content".
	 */
	@GetMapping("/usuario")
	public ResponseEntity<List<Compra>> listaDeComprasPorUsuario(@RequestHeader("Authorization") String bearerToken) {
		String username = jwtUtils.getUsernameFromBearerToken(bearerToken);
		Optional<Usuario> usuario = usuarioService.findByUsername(username);
		if (usuario.isPresent()) {
			List<Compra> listado = new ArrayList<>();
			listado = compraService.findAllByUsuario(usuario.get());
			return ResponseEntity.ok(listado);
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param compraDetalleDto
	 * @param bearerToken
	 * @return Retorna un ResponseEntity con la nueva compra y sus detalles
	 *         generados a partir del parametro compraDetalleDto
	 */
	@PostMapping
	public ResponseEntity<Compra> generarNuevaCompra(@RequestBody CompraDetallesDTO compraDetalleDto,
			@RequestHeader("Authorization") String bearerToken) {
		String username = jwtUtils.getUsernameFromBearerToken(bearerToken);
		Optional<Usuario> usuarioOpt = usuarioService.findByUsername(username);
		if (usuarioOpt.isPresent()) {
			compraDetalleDto.getCompra().setUsuario(usuarioOpt.get());
			Compra nuevaCompra = compraService.save(compraDetalleDto.getCompra());
			detalleCompraService.save(compraDetalleDto.getCompra(), compraDetalleDto.getDetalles());
			return ResponseEntity.ok(nuevaCompra);
		}
		return ResponseEntity.ok(compraDetalleDto.getCompra());
	}

	/**
	 * @param compraId
	 * @return Elimina la compra con id que viene en el parametro compraId, y
	 *         retorna el mismo id recibido.
	 */
	@DeleteMapping("/{compraId}")
	public ResponseEntity<?> eliminarCompra(@PathVariable Integer compraId) {
		compraService.delete(compraId);
		return ResponseEntity.ok(compraId);
	}

}
