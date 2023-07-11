package com.compras.controller;

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

	/**
	 * @param usuario
	 * @return Crea un nuevo usuario en la BBDD con la informacion obtenida en el
	 *         parametro usuario. Si ya existe en la BBDD un usuario con el mismo
	 *         nombre de usuario retorna un ResponseEntity.noContent().
	 */
	@PostMapping("/nuevo")
	public ResponseEntity<Usuario> crearNuevoUsuario(@RequestBody Usuario usuario) {
		if (!usuarioService.findByUsername(usuario.getUsername()).isPresent()) {
			usuario.setPassword(passEncoder.encode(usuario.getPassword()));
			usuario = usuarioService.save(usuario);
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.noContent().build();
	}

}
