package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Aux {
  @Getter @Setter
  private String nombre;

  @Getter @Setter
  private String token;

  public Aux(String nombre, String token) {
    this.nombre = nombre;
    this.token = token;
  }
}
