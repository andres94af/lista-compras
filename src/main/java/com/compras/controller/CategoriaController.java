package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProductoService productoService;
	
	//Retorna un listado de todas las categorias existentes.
	@GetMapping
	public ResponseEntity<List<Categoria>> listadoDeCategorias(){
		List<Categoria> categorias = new ArrayList<>();
		categorias = categoriaService.findAll();
		return ResponseEntity.ok(categorias);
	}
	
	//Retorna un listado de productos de una categoria específica.
	//Si la categoria no existe retorna un estado "No content".
	@GetMapping("/{catId}")
	public ResponseEntity<List<Producto>> listadoDeProductosPorCategoria(@PathVariable Integer catId){
		Optional<Categoria> categoria = categoriaService.findById(catId);
		
		if (categoria.isPresent()) {
			List<Producto> productos = new ArrayList<>();
			productos = productoService.findByCategoria(categoria.get());
			return ResponseEntity.ok(productos);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	//Inserta nuevo item de categoria y lo retorna
	@PostMapping
	public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.ok(categoriaService.save(categoria));
	}
	
}
