package com.compras;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.compras.model.Compra;
import com.compras.service.CompraService;

@SpringBootTest
class ListaComprasBackEndApplicationTests {

	@Autowired
	CompraService compraService;
	
	@Test
	void contextLoads() {
		Compra compra = new Compra();
		compra.setId(1);
		compra.setCompletada(false);
		compra.setFecha(LocalDate.now());
		compraService.save(compra);
	}

}
