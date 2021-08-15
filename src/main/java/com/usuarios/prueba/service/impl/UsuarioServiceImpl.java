package com.usuarios.prueba.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.usuarios.prueba.entity.Usuario;
import com.usuarios.prueba.repository.UsuarioRepository;
import com.usuarios.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Implementador del servicio
 * 
 * @author Carlos Lopez
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	@CrossOrigin(maxAge = 3600)
	@Override
	public List<Usuario> findAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findUsuarioById(int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario;
	}
	
	/**
	 * Metodo para guardar un empleado 
	 */

	@Override
	public boolean saveUsuario(Usuario usuarioNew) {

		if (usuarioNew != null) {
			usuarioNew.setEmail(validateEmail(usuarioNew));
			usuarioNew.setCreado(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
			usuarioNew.setActualizado(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
			usuarioRepository.save(usuarioNew);
			return true;
		}
		return false;
	}

	/**
	 * Metodo para eliminar el empleado
	 */
	@Override
	public boolean deleteUsuario(int id) {
		if (usuarioRepository.findById(id).isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
	/**
	 * Metodo para validar que un documento no este repetido en un empleado
	 * 
	 * @param document
	 * @return existencia del documento
	 */
	private boolean validateDocument(String document) {
		List<Usuario> validDocuments = findAllUsuarios();
		for (Usuario documents : validDocuments) {
			if (documents.getDocumento().equalsIgnoreCase(document)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Servicio que hace update del empleado
	 */
	@Override
	public boolean updateUsuario(Usuario usuarioUpdated) {
		int num = usuarioUpdated.getId();
		Optional<Usuario> actual = usuarioRepository.findById(num);
		Usuario usuarioToUpdate = new Usuario();
		if (usuarioRepository.findById(num).isPresent()) {
			if (actual.get().getNombre().equalsIgnoreCase(usuarioUpdated.getNombre())
					&& actual.get().getApellido().equalsIgnoreCase(usuarioUpdated.getApellido())
					&& actual.get().getPais().getNombre().equals(usuarioToUpdate.getPais().getNombre())) {
				usuarioToUpdate.setEmail(actual.get().getEmail());
			} else {
				usuarioToUpdate.setEmail(validateEmail(usuarioUpdated));
			}
			usuarioToUpdate.setDocumento(usuarioUpdated.getDocumento());
			if (!actual.get().getDocumento().equals(usuarioToUpdate.getDocumento())) {
				if (validateDocument(usuarioToUpdate.getDocumento())) {
					return false;
				}
			}
//			usuarioToUpdate.setCreatedAt(actual.get().getCreatedAt());
//			usuarioToUpdate.setAddmissionDate(UsuarioUpdated.getAddmissionDate());
//			usuarioToUpdate.setId(UsuarioUpdated.getId());
//			usuarioToUpdate.setFirstName(UsuarioUpdated.getFirstName());
//			usuarioToUpdate.setMiddleName(UsuarioUpdated.getMiddleName());
//			usuarioToUpdate.setLastName(UsuarioUpdated.getLastName());
//			usuarioToUpdate.setSecondLastName(UsuarioUpdated.getSecondLastName());
//			usuarioToUpdate.setCountry(UsuarioUpdated.getCountry());
//			usuarioToUpdate.setDocumentType(UsuarioUpdated.getDocumentType());
//
//			usuarioToUpdate.setArea(UsuarioUpdated.getArea());
//			usuarioToUpdate.setState(UsuarioUpdated.getState());
//			usuarioToUpdate.setUpdateAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
			usuarioRepository.save(usuarioToUpdate);
			return true;
		}
		return false;
	}

	/**
	 * Validacion del email en caso de que el usuario haya cambiado de nombre y apellido
	 * @param usuario
	 * @return
	 */
	public String validateEmail(Usuario usuario) {
		List<Usuario> validEmails = findAllUsuarios();
		String email = usuario.getNombre() + "." + usuario.getApellido() + "@prueba.com.";
		email = email.replaceAll(" ", "");
		email +=usuario.getPais().getIndicativo();
		int index = 0;
		email = email.toLowerCase();
		String emailaux = email;

		for (Usuario mail : validEmails) {
			if (mail.getEmail().contains(email)) {
				email = emailaux.replace("@", "." + (++index) + "@");
			}
		}
		email = email.replaceAll(" ", "");
		usuario.setEmail(email);
		usuario.setEstado("Activo");
		return usuario.getEmail();

	}
}