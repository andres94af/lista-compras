package com.compras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Categoria;
import com.compras.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	List<Producto> findByCategoria(Categoria categoria);
	Optional<Producto> findByNombre(String nombre);

}
