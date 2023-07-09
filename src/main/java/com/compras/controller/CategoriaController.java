package com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Categoria;
import com.compras.service.CategoriaService;
import com.compras.service.ProductoService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	ProductoService productoService;

	/**
	 * @return Retorna un ResponseEntity con un listado de todas las categorias
	 *         existentes en la BBDD.
	 */
	@GetMapping("/listar")
	public ResponseEntity<List<Categoria>> listadoDeCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		categorias = categoriaService.findAll();
		return ResponseEntity.ok(categorias);
	}

	/**
	 * @param categoria
	 * @return Retorna un ResponseEntity con la nueva Categoria segun los criterios
	 *         de creación del servicio CategoriaService.save()
	 */
	@PostMapping
	public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaService.save(categoria));
	}

}
