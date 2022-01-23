package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;


import java.sql.Date;


public class Reserva {
    @Getter @Setter
    private int idReserva;

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String tipoUsuario;

    @Getter @Setter
    private int idMesa;

    @Getter @Setter
    private Date fecha;

    @Getter @Setter
    private int cantidadPersona;

    @Getter @Setter
    private boolean estado;

    public Reserva() {}

    public Reserva(int idReserva, int cantidadPersonas, Date fecha, int idMesa, int idUsuario, String tipoUsuario, boolean estado) {
        this.idReserva = idReserva;
        this.cantidadPersona = cantidadPersonas;
        this.fecha = fecha;
        this.idMesa = idMesa;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }
}
