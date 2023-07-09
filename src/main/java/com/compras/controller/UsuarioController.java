package com.compras.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compras.model.Usuario;
import com.compras.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	PasswordEncoder passEncoder = new BCryptPasswordEncoder();

	// Inserta nuevo usuario de producto si no existe en la bbdd
	@PostMapping("/nuevo")
	public ResponseEntity<Map<String, Object>> crearNuevoUsuario(@RequestBody Usuario usuario) {
		Map<String, Object> datos = new HashMap<>();
		if (!usuarioService.findByUsername(usuario.getUsername()).isPresent()) {
			usuario.setPassword(passEncoder.encode(usuario.getPassword()));
			usuario = usuarioService.save(usuario);
			datos.put("Mensaje", "Usuario guardado con exito");
			datos.put("Datos del usuario", usuario);
			return ResponseEntity.ok(datos);
		}
		datos.put("Mensaje", "Existe un usuario con el username: " + usuario.getUsername());
		return ResponseEntity.ok(datos);
	}

}
