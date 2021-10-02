package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class TipoUsuario {

  @Getter @Setter
  private String idTipoUsuario;

  @Getter @Setter
  private  String descripcion;

  public TipoUsuario(){}

  public TipoUsuario(String idTipoUsuario, String descripcion) {
    this.idTipoUsuario = idTipoUsuario;
    this.descripcion = descripcion;
  }
}
