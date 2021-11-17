package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class CompraInsumo {
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
    private int idEmpleado;

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String idTipoUsuario;

    public CompraInsumo() {}

    public CompraInsumo(int idCompra, Date fechaCompra, int valorNeto, int IVA, int total, int idEmpleado, int idUsuario, String idTipoUsuario) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.valorNeto = valorNeto;
        this.IVA = IVA;
        this.total = total;
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
    }
}
