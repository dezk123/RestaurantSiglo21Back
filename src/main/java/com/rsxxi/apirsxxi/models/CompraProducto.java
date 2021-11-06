package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class CompraProducto {
    @Getter @Setter
    private int idCompra;

    @Getter @Setter
    private Date fechaCompra;

    @Getter @Setter
    private int valorNeto;

    @Getter @Setter
    private int IVA;

    @Getter @Setter
    private int total;

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String idTipoUsuario;

    public CompraProducto() {}

    public CompraProducto(int idCompra, Date fechaCompra, int valorNeto, int IVA, int total, int idUsuario, String idTipoUsuario) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.valorNeto = valorNeto;
        this.IVA = IVA;
        this.total = total;
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
    }
}
