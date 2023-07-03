package com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

}
