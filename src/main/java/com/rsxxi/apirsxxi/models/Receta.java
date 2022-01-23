package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Receta {
    @Getter @Setter
    private int idPlato;

    @Getter @Setter
    private int idInsumo;

    @Getter @Setter
    private int idCategoria;

   private int cantidadInsumo;


    public Receta() {}

    public Receta(int idPlato, int idInsumo, int idCategoria, int cantidadInsumo) {
        this.idPlato = idPlato;
        this.idInsumo = idInsumo;
        this.idCategoria = idCategoria;
        this.cantidadInsumo = cantidadInsumo;
    }
}
