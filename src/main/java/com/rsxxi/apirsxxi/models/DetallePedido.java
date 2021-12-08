package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class DetallePedido {

    @Getter @Setter
    private int idPedido;

    @Getter @Setter
    private  int idPlato;

    @Getter @Setter
    private int idInsumo;

    @Getter @Setter
    private int idCategoria;

    @Getter @Setter
    private int cantidadPlato;

    @Getter @Setter
    private int precioPedido;

    public DetallePedido(){}

    public DetallePedido(int idPedido, int idPlato, int idInsumo, int idCategoria, int cantidadPlato, int precioPedido) {
        this.idPedido = idPedido;
        this.idPlato = idPlato;
        this.idInsumo = idInsumo;
        this.idCategoria = idCategoria;
        this.cantidadPlato = cantidadPlato;
        this.precioPedido = precioPedido;
    }
}
