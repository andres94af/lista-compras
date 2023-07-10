package com.compras.model;

import java.util.List;

import lombok.Data;

@Data
public class CompraDetallesDTO {
	
	private Compra compra;
	private List<DetalleCompra> detalles;

}
