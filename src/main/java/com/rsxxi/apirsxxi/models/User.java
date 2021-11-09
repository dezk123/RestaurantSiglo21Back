package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class User {
  @Getter @Setter
  private String nombre;

  @Getter @Setter
  private String token;

  public User(String nombre, String token) {
    this.nombre = nombre;
    this.token = token;
  }
}
