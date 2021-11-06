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
    private String estado;

    public Reserva() {}

    public Reserva(int idReserva, int idUsuario, String tipoUsuario, int idMesa, Date fecha, int cantidadPersonas, String estado) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.cantidadPersona = cantidadPersonas;
        this.estado = estado;
    }
}
