package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Mesa {
    @Getter @Setter
    private int idMesa;

    @Getter @Setter
    private int cantidadSilla;

    @Getter  @Setter
    private char idestadoMesa;

    public Mesa() {}

    public Mesa(int idMesa, int cantidadSilla, char idestadoMesa) {
        this.idMesa = idMesa;
        this.cantidadSilla = cantidadSilla;
        this.idestadoMesa = idestadoMesa;
    }
}
