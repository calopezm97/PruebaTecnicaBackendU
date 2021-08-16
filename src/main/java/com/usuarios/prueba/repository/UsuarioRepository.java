package com.usuarios.prueba.repository;

import java.util.Optional;

import com.usuarios.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Carlos Lopez
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Void save(Optional<Usuario> customerToUpdate);
}