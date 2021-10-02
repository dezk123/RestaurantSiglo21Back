package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Producto {

    @Getter @Setter
    private int idProducto;

    @Getter @Setter
    private int idCategoriaProducto;

    @Getter @Setter
    private String nombreProducto;

    @Getter @Setter
    private int precioUnitario;

    @Getter @Setter
    private int existencia;

    public Producto(){}

    public Producto(int idProducto, int idCategoriaProducto, String nombreProducto, int precioUnitario, int existencia) {
        this.idProducto = idProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.existencia = existencia;
    }
}
