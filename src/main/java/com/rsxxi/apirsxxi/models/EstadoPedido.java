package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class EstadoPedido {

    @Getter @Setter
    private String idEstadoPedido;

    @Getter @Setter
    private String descripcion;

    public EstadoPedido(){}

    public EstadoPedido(String idEstadoPedido, String descripcion) {
        this.idEstadoPedido = idEstadoPedido;
        this.descripcion = descripcion;
    }

}
