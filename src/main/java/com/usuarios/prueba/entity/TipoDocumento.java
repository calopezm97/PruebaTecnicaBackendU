package com.usuarios.prueba.entity;

import lombok.Getter;

@Getter
public enum TipoDocumento {

    CC("CC","Cedula De Ciudadania"),
    TI("TI","Tarjeta De Identidad");

    private String sigla;
    private String nombre;

    TipoDocumento(String sigla, String nombre) {

        this.sigla = sigla;
        this.nombre = nombre;
    }
}
