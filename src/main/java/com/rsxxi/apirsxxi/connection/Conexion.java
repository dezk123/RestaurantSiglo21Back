package com.rsxxi.apirsxxi.connection;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  @Getter @Setter
  private String url;

  @Getter @Setter
  private String usuario;

  @Getter @Setter
  private String contrasena;

  public Conexion() {}

  public Conexion(String url, String usuario, String contrasena) {
    this.url = url;
    this.usuario = usuario;
    this.contrasena = contrasena;
  }

  public Connection obtenerConexion() throws SQLException{
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    Connection con = DriverManager.getConnection(this.url, this.usuario, this.contrasena);

    return con;
  }
}

