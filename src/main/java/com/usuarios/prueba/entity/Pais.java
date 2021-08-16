package com.usuarios.prueba.entity;

import lombok.Getter;

@Getter
public enum Pais {

    COLOMBIA("Colombia", "com.co"),
    ESTADOS_UNIDOS("Estados Unidos","com.us");

    private String nombre;
    private String indicativo;

    Pais(String nombre, String indicativo) {

        this.nombre = nombre;
        this.indicativo = indicativo;
    }
}
