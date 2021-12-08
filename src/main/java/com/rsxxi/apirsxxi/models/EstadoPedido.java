package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class EstadoPedido {

    @Getter @Setter
    private String idEstadoPedido;

    @Getter @Setter
    private String descripcionEstado;

    public EstadoPedido(){}

    public EstadoPedido(String idEstadoPedido, String descripcionEstado) {
        this.idEstadoPedido = idEstadoPedido;
        this.descripcionEstado = descripcionEstado;
    }

}
