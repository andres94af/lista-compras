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

import com.compras.model.Categoria;
import com.compras.model.Producto;
import com.compras.service.CategoriaService;
import com.compras.service.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin("http://localhost:4200")
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

	// Retorna un listado de productos de una categoria específica.
	// Si la categoria no existe retorna un estado "No content".
	@GetMapping("/{catId}")
	public ResponseEntity<List<Producto>> listadoDeProductosPorCategoria(@PathVariable Integer catId) {
		Optional<Categoria> categoria = categoriaService.findById(catId);

		if (categoria.isPresent()) {
			List<Producto> productos = new ArrayList<>();
			productos = productoService.findByCategoria(categoria.get());
			return ResponseEntity.ok(productos);
		}

		return ResponseEntity.noContent().build();
	}

	// Inserta nuevo item de producto y lo retorna
	@PostMapping("/nuevo/{catId}")
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @PathVariable Integer catId) {
		if (categoriaService.findById(catId).isPresent()) {
			Categoria cat = new Categoria();
			cat.setId(catId);
			producto.setCategoria(cat);
			return ResponseEntity.ok(productoService.save(producto));
		}
		return ResponseEntity.unprocessableEntity().build();
	}

	// Retorna listado de todas los productos filtrados
	@GetMapping("/filtrado/{valorFiltro}")
	public ResponseEntity<List<Producto>> listadoDeProductosFiltrados(@PathVariable String valorFiltro) {
		List<Producto> productos = new ArrayList<>();
		productos = productoService.findFiltered(valorFiltro);
		return ResponseEntity.ok(productos);
	}

}
