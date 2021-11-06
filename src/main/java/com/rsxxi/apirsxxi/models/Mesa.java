package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Mesa {
    @Getter @Setter
    private int idMesa;

    @Getter @Setter
    private int cantidadSilla;

    public Mesa() {}

    public Mesa(int idMesa, int cantidadSilla) {
        this.idMesa = idMesa;
        this.cantidadSilla = cantidadSilla;
    }
}
