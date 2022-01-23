package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Insumo {

  @Getter @Setter
  private int idInsumo;

  @Getter @Setter
  private int idCategoria;

  @Getter @Setter
  private String nombreInsumo;

  @Getter @Setter
  private int precioUnitario;

  @Getter @Setter
  private int existencia;

  public Insumo(){}

  public Insumo(int idInsumo, int idCategoria, String nombreInsumo, int precioUnitario, int existencia) {
    this.idInsumo = idInsumo;
    this.idCategoria = idCategoria;
    this.nombreInsumo = nombreInsumo;
    this.precioUnitario = precioUnitario;
    this.existencia = existencia;
  }
}
