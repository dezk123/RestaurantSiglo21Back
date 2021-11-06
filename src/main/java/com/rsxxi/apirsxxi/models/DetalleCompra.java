package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class DetalleCompra {
    @Getter @Setter
    private int cantidad;

    @Getter @Setter
    private int idCategoria;

    @Getter @Setter
    private int idCompra;

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String rutProveedor;

    @Getter @Setter
    private int idProducto;

    public DetalleCompra() {}

    public DetalleCompra(int cantidad, int idCategoria, int idCompra, int idUsuario, String rutProveedor, int idProducto) {
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.rutProveedor = rutProveedor;
        this.idProducto = idProducto;
    }
}
