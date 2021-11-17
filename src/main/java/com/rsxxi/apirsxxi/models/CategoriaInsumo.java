package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class CategoriaInsumo {

  @Getter @Setter
  private int idCategoria;

  @Getter @Setter
  private String descripcion;

  public CategoriaInsumo(){}

  public CategoriaInsumo(int idCategoria, String descripcion) {
    this.idCategoria = idCategoria;
    this.descripcion = descripcion;
  }
}
