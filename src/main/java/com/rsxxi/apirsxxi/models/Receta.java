package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Receta {
    @Getter @Setter
    private int idReceta;

    @Getter @Setter
    private int idProducto;

    @Getter @Setter
    private int cantidad;

    @Getter @Setter
    private int idPlato;

    @Getter @Setter
    private int idCategoria;

    public Receta() {}

    public Receta(int idReceta, int idProducto, int cantidad, int idPlato, int idCategoria) {
        this.idReceta = idReceta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idPlato = idPlato;
        this.idCategoria = idCategoria;
    }
}
