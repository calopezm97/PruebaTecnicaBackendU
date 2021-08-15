package com.usuarios.prueba.service;

import java.util.List;
import java.util.Optional;
import com.usuarios.prueba.entity.Usuario;

/**
 * La interface no presta los servicios para ser creados s
 * 
 * @author Carlos Lopez
 *
 */
public interface UsuarioService {
	public List<Usuario> findAllUsuarios();

	public Optional<Usuario> findUsuarioById(int id);

	public boolean saveUsuario(Usuario usuarioNew);

	public boolean deleteUsuario(int id);

	public boolean updateUsuario(Usuario usuarioNew);
}
