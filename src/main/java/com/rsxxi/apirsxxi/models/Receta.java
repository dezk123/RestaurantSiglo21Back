package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Receta {
    @Getter @Setter
    private int cantidadInsumo;

    @Getter @Setter
    private int idInsumo;

    @Getter @Setter
    private int idCategoria;

    @Getter @Setter
    private int idPlato;


    public Receta() {}

    public Receta(int cantidadInsumo, int idInsumo, int idCategoria, int idPlato) {
        this.cantidadInsumo = cantidadInsumo;
        this.idInsumo = idInsumo;
        this.idPlato = idPlato;
        this.idCategoria = idCategoria;
    }
}
