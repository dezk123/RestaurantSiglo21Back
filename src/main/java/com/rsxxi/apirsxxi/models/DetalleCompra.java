package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class DetalleCompra {
    @Getter @Setter
    private int cantidad;

    @Getter @Setter
    private int idCategoria;

    @Getter @Setter
    private String rutProveedor;

    @Getter @Setter
    private int idInsumo;

    @Getter @Setter
    private int idCompra;

    @Getter @Setter
    private int idEmpleado;

    public DetalleCompra() {}

    public DetalleCompra(int cantidad, int idCategoria, String rutProveedor, int idInsumo, int idCompra, int idEmpleado) {
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.rutProveedor = rutProveedor;
        this.idInsumo = idInsumo;
        this.idCompra = idCompra;
        this.idEmpleado = idEmpleado;
    }
}
