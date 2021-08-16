package com.usuarios.prueba.entity;

import lombok.Getter;

@Getter
public enum Estado {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private String nombre;

    Estado(String nombre) {

        this.nombre = nombre;
    }
}
