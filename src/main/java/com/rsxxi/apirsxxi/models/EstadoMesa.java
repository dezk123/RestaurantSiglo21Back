package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class EstadoMesa {

    @Getter @Setter
     private char idEstado;

     @Getter @Setter
     private String estado;

    public EstadoMesa(){}

    public EstadoMesa(char idEstado, String estado){
        this.idEstado = idEstado;
        this.estado = estado;
    }

}
