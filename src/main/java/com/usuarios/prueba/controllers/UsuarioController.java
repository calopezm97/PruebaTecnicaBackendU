package com.usuarios.prueba.controllers;

import java.util.List;
import java.util.Optional;

import com.usuarios.prueba.entity.Usuario;

/**
 * Interface que controla el empleado, brinda las opciones de CRUD
 * 
 * @author Carlos Lopez
 *
 */
public interface UsuarioController {
	public List<Usuario> getUsuarios();

	public Optional<Usuario> getUsuarioById(int id);

	public boolean addUsuario(Usuario Usuario);

	public boolean deleteUsuario(int id);

	public boolean updateUsuario(Usuario UsuarioNew);
}
