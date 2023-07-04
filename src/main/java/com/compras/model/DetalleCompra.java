package com.compras.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detalles")
public class DetalleCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Double cantidad;
	private Double precio;
	private Double total;

	@JsonBackReference
	@ManyToOne
	private Compra compra;

	public DetalleCompra(String nombre, Double cantidad, Double precio, Compra compra) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = cantidad * precio;
		this.compra = compra;
	}
	
	

}
