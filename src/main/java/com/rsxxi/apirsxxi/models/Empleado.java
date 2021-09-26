package com.rsxxi.apirsxxi.models;

import java.util.Date;

public class Empleado {
    private int idEmpleado;
    private int idUsuario;
    private String idTipoUsuario;
    private int sueldo;
    private int empleadoJefe;
    private Date fechaContrato;
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

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getEmpleadoJefe() {
        return empleadoJefe;
    }

    public void setEmpleadoJefe(int empleadoJefe) {
        this.empleadoJefe = empleadoJefe;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }
}
