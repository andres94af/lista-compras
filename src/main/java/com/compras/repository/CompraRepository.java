package com.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Compra;
import com.compras.model.Usuario;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
	
	List<Compra> findByUsuario(Usuario usuario);

}
