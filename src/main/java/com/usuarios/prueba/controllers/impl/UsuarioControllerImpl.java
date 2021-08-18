package com.usuarios.prueba.controllers.impl;

import com.usuarios.prueba.controllers.UsuarioController;
import com.usuarios.prueba.entity.Usuario;
import com.usuarios.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
public class UsuarioControllerImpl implements UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    /**
     * Metodo que lista todos los empleados desde la url, extrae toda la informacion
     */
    @Override
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = "application/json")
    public List<Usuario> getUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    /**
     * Metodo que extrae unicamente el Json que se le envia por Id
     *
     * @param id codigo con el que se extrae la informacion
     */
    @Override
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET, produces = "application/json")
    public Optional<Usuario> getUsuarioById(@PathVariable int id) {
        return usuarioService.findUsuarioById(id);
    }

    /**
     * Metodo para adicionar un empleado nuevo
     *
     * @param usuario Empleado sin ID para ser añadido en la Lista
     */
    @Override
    @RequestMapping(value = "/usuarios/", method = RequestMethod.POST, produces = "application/json")
    public boolean addUsuario(@RequestBody Usuario usuario) {
        usuario = validateEmail(usuario);
        usuario.setUpdateAt(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        usuario.setCreatedAt(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        if (validateDocument(usuario.getDocument())) {
            return false;
        }
        usuarioService.saveUsuario(usuario);
        return true;

    }

    /**
     * Metodo para validar que un documento no este repetido en un empleado
     *
     * @param document
     * @return existencia del documento
     */
    private boolean validateDocument(String document) {
        List<Usuario> validDocuments = getUsuarios();
        for (Usuario documents : validDocuments) {
            if (documents.getDocument().contains(document)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para eliminar empleados por el id
     *
     * @param id es el objeto- registro que deseamos eliminar
     */

    @Override
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public boolean deleteUsuario(@PathVariable int id) {
        usuarioService.deleteUsuario(id);
        return true;
    }

    /**
     * Metodos para modificar el empleado lo envia al servicio
     *
     * @param usuarioNew el objeto que se reemplazara
     */
    // http://localhost:8080/usuarios/update (PUT)
    @Override
    @PutMapping(value = "/usuario/{id}")
    public boolean updateUsuario(@RequestBody Usuario usuarioNew) {
        usuarioNew = validateEmail(usuarioNew);
        return usuarioService.updateUsuario(usuarioNew);
    }

    /**
     * Metodos para modificar o crear el email de un empleado
     *
     * @param usuario
     * @return existencia del documento
     */
    public Usuario validateEmail(Usuario usuario) {
        List<Usuario> validEmails = getUsuarios();
        String email = usuario.getFirstName() + "." + usuario.getLastName() + "@prueba.com";

        int index = 0;
        email = email.replace("\\s", "");
        email = email.toLowerCase();
        String emailaux = email;

        for (Usuario mail : validEmails) {
            if (mail.getEmail().contains(email)) {
                email = emailaux.replace("@", "." + (++index) + "@");
            }
        }
        usuario.setEmail(email);
        usuario.setState("ACTIVO");
        return usuario;

    }

}