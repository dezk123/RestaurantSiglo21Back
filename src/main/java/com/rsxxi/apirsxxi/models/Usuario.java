package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {

  @Getter @Setter
  private int idUsuario;

  @Getter @Setter
  private String idTipoUsuario;

  @Getter @Setter
  private String correo;

  @Getter @Setter
  private String contrasena;

  @Getter @Setter
  private String nombre;

  @Getter @Setter
  private String apellido;

  @Getter @Setter
  private String direccion;

  @Getter @Setter
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
