package com.compras;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.compras.service.CompraService;

@SpringBootTest
class ListaComprasBackEndApplicationTests {

	@Autowired
	CompraService compraService;
	
	@Test
	void contextLoads() {
	}

}
