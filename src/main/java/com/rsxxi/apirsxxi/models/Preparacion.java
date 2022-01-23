package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Preparacion {
    @Getter
    @Setter
    private int idPlato;

    @Getter @Setter
    private String descPreparacion;



    public Preparacion() {}

    public Preparacion(int idPlato, String descPreparacion) {
        this.idPlato = idPlato;
        this.descPreparacion = descPreparacion;
    }
}
