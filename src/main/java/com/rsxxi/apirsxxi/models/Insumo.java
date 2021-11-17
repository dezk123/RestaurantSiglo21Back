package com.rsxxi.apirsxxi.models;

import lombok.Getter;
import lombok.Setter;

public class Insumo {

  @Getter @Setter
  private int idInsumo;

  @Getter @Setter
  private int idCategoriaInsumo;

  @Getter @Setter
  private String nombreInsumo;

  @Getter @Setter
  private int precioUnitario;

  @Getter @Setter
  private int existencia;

  public Insumo(){}

  public Insumo(int idInsumo, int idCategoriaInsumo, String nombreInsumo, int precioUnitario, int existencia) {
    this.idInsumo = idInsumo;
    this.idCategoriaInsumo = idCategoriaInsumo;
    this.nombreInsumo = nombreInsumo;
    this.precioUnitario = precioUnitario;
    this.existencia = existencia;
  }
}
