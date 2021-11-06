package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Plato {
    @Getter @Setter
    private int idPlato;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private int precio;

    public Plato() {}

    public Plato(int idPlato, String descripcion, int precio) {
        this.idPlato = idPlato;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}
