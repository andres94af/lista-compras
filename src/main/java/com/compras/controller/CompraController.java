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
import com.compras.model.Usuario;
import com.compras.service.CompraService;
import com.compras.service.UsuarioService;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	CompraService compraService;
	
	@Autowired
	UsuarioService usuarioService;

	//Prueba controlador compra. Mensaje "Bienvenido!"
	@GetMapping
	public ResponseEntity<String> bienvenido(){
		return ResponseEntity.ok("Bienvenido!");
	}
	
	//Retorna un listado de todas las compras hechas por un usuario.
	//Si el usuario no existe retorna un estado "No content".
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Compra>> listaDeComprasPorUsuario(@PathVariable Integer idUsuario){
		Optional<Usuario> usuario = usuarioService.findById(idUsuario);
		
		if(usuario.isPresent()) {
			List<Compra> listado = new ArrayList<>();
			listado = usuario.get().getCompras();
			return ResponseEntity.ok(listado);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	//Retorna un listado de los detalles de una compra
	//Si la compra no existe retorna un estado "No content".
	@GetMapping("/{idCompra}")
	public ResponseEntity<List<DetalleCompra>> detallesDeCompra(@PathVariable Integer idCompra){
		Optional<Compra> compra = compraService.findById(idCompra);
		
		if(compra.isPresent()) {
			List<DetalleCompra> listado = new ArrayList<>();
			listado = compra.get().getDetalles();
			return ResponseEntity.ok(listado);
		}
		
		return ResponseEntity.noContent().build();
	}

}
