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
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaService;

	/**
	 * @return Retorna un ResponseEntity con un listado de todos los productos
	 *         existentes.
	 */
	@GetMapping
	public ResponseEntity<List<Producto>> listadoDeProductos() {
		List<Producto> productos = new ArrayList<>();
		productos = productoService.findAll();
		return ResponseEntity.ok(productos);
	}

	/**
	 * @param catId
	 * @return Retorna un ResponseEntity con un listado de productos de una
	 *         categoria específica segun el id pasado por parametros. Si la
	 *         categoria no existe retorna un estado "No content".
	 */
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

	/**
	 * @param producto
	 * @param catId
	 * @return Retorna un ResponseEntiity con un producto. Primero verifica que la
	 *         categoria exista en la BBDD. Si existe, agrega el producto con los
	 *         criterios de ProductoService.save(). Si no existe, retorna un error
	 *         422 (UnprecessableEntity).
	 */
	@PostMapping("/{catId}")
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @PathVariable Integer catId) {
		if (categoriaService.findById(catId).isPresent()) {
			Categoria cat = new Categoria();
			cat.setId(catId);
			producto.setCategoria(cat);
			return ResponseEntity.ok(productoService.save(producto));
		}
		return ResponseEntity.unprocessableEntity().build();
	}

	/**
	 * @param valorFiltro
	 * @return Retorna un ResponseEntity con un listado de productos filtrados por
	 *         nombre a partir del parametro valor filtro.
	 */
	@GetMapping("/filtrado/{valorFiltro}")
	public ResponseEntity<List<Producto>> listadoDeProductosFiltrados(@PathVariable String valorFiltro) {
		List<Producto> productos = new ArrayList<>();
		productos = productoService.findFiltered(valorFiltro);
		return ResponseEntity.ok(productos);
	}

}
