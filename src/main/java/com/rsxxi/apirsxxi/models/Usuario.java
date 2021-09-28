package com.rsxxi.apirsxxi.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
    private int idUsuario;

    private String idTipoUsuario;

    private String correo;

    private String contrasena;

    private String nombre;

    private String apellido;

    private String direccion;

    private String run;

    public Usuario(){}

    public Usuario(int idUsuario, String idTipoUsuario, String correo, String contrasena, String nombre, String apellido, String direccion, String run) {
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.run = run;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Usuario buscarUsuario(Connection con, String correo) throws SQLException {
        Usuario aux = new Usuario();
        Statement statement = con.createStatement();
        String query = String.format("SELECT * FROM USUARIO WHERE CORREO = '%s'", correo);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            aux.setIdUsuario(resultSet.getInt(1));
            aux.setIdTipoUsuario(resultSet.getString(2));
            aux.setCorreo(resultSet.getString(3));
            aux.setContrasena(resultSet.getString(4));
            aux.setNombre(resultSet.getString(5));
            aux.setApellido(resultSet.getString(6));
            aux.setDireccion(resultSet.getString(7));
            aux.setRun(resultSet.getString(8));
        }
        return aux;
    }


}
