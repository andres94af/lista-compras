package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.model.Categoria;
import com.compras.model.Producto;
import com.compras.service.CategoriaService;
import com.compras.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;

	// Retorna listado de todas los productos registrados
	@GetMapping
	public ResponseEntity<List<Producto>> listadoDeProductos() {
		List<Producto> productos = new ArrayList<>();
		productos = productoService.findAll();
		return ResponseEntity.ok(productos);
	}

	// Inserta nuevo item de producto y lo retorna
	@PostMapping("/nuevo/{catId}")
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @PathVariable Integer catId) {
		if(categoriaService.findById(catId).isPresent()) {
			Categoria cat= new Categoria();
			cat.setId(catId);
			producto.setCategoria(cat);
			return ResponseEntity.ok(productoService.save(producto));
		}
		return ResponseEntity.unprocessableEntity().build();
	}

}
