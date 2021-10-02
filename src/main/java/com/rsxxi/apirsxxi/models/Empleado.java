package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Empleado {

    @Getter @Setter
    private int idEmpleado;

    @Getter @Setter
    private int idUsuario;

    @Getter @Setter
    private String idTipoUsuario;

    @Getter @Setter
    private int sueldo;

    @Getter @Setter
    private int empleadoJefe;

    @Getter @Setter
    private Date fechaContrato;

    @Getter @Setter
    private String numeroContacto;

    public Empleado(){}

    public Empleado(int idEmpleado, int idUsuario, String idTipoUsuario, int sueldo, int empleadoJefe, Date fechaContrato, String numeroContacto) {
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.sueldo = sueldo;
        this.empleadoJefe = empleadoJefe;
        this.fechaContrato = fechaContrato;
        this.numeroContacto = numeroContacto;
    }
}
