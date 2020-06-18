package com.api.cleverit.app.service;

import java.util.List;

import com.api.cleverit.app.domain.Usuario;

public interface UsuarioApiService {

	List<Usuario> list();

	Usuario findUsuario(String id);

	String createUsuario(Usuario usuario);

	boolean deleteUsuario(String id);

	boolean editUsuario(Usuario usuario);

}
