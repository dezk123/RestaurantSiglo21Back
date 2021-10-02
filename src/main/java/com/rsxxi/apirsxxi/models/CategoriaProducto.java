package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class CategoriaProducto {

    @Getter @Setter
    private int idCategoria;

    @Getter @Setter
    private String descripcion;

    public CategoriaProducto(){}

    public CategoriaProducto(int idCategoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }
}
