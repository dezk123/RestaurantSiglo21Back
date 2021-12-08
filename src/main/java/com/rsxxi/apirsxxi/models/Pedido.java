package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Pedido {

    @Getter @Setter
    private int idPedido;

    @Getter @Setter
    private int idMesa;

    @Getter @Setter
    private String idEstadoPedido;

    @Getter @Setter
    private  Date fecha;

    @Getter @Setter
    private int subTotal;

    @Getter @Setter
    private int propina;

    @Getter @Setter
    private  int total;

    public Pedido(){}

    public Pedido(int idPedido, int idMesa, String idEstadoPedido, Date fecha, int subTotal, int propina, int total) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idEstadoPedido = idEstadoPedido;
        this.fecha = fecha;
        this.subTotal = subTotal;
        this.propina = propina;
        this.total = total;
    }


}

