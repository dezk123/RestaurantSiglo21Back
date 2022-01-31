package com.rsxxi.apirsxxi.utils;

import lombok.Getter;
import lombok.Setter;

public class User {
  @Getter @Setter
  private String nombre;

  @Getter @Setter
  private String token;

  @Getter @Setter
  private int idUsuario;

  public User(String nombre, String token, int idUsuario) {
    this.nombre = nombre;
    this.token = token;
    this.idUsuario = idUsuario;
  }
}
