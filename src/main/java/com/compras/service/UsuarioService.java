package com.compras.service;

import java.util.Optional;

import com.compras.model.Usuario;

public interface UsuarioService {

	Optional<Usuario> findByUsername(String username);

	Optional<Usuario> findById(Integer id);

	Usuario save(Usuario usuario);

	void update(Usuario usuario);

	void delete(Integer id);

}
