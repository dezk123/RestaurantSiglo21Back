package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Proveedor {
    @Getter @Setter
    private int rutProveedor;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String direccion;

    @Getter @Setter
    private String telefono;

    public Proveedor() {}

    public Proveedor(int rutProveedor, String nombre, String direccion, String telefono) {
        this.rutProveedor = rutProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
