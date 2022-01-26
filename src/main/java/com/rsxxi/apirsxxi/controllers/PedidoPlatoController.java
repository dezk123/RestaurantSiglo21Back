package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Plato;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://localhost:44379", "https://localhost:44379"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class PedidoPlatoController {
  @Autowired
  private JWTUtil jwtUtil;

  // Conexion con la base de datos
  private Connection configuracion() throws SQLException {
    Conexion conexion = new Conexion();
    return conexion.obtenerConexion();
  }

  // Validar token
  private String validarToken(String token) { return jwtUtil.getValue(token); }

  // Obtener platos
  @RequestMapping(value = "api/platos", method = RequestMethod.GET)
  public List<Plato> obtenerPlatos(/*@RequestHeader(value = "Authorization") String token*/) throws SQLException {
    //if (validarToken(token) == null) { return null; }
    Connection connection = configuracion();
    CallableStatement statement = connection.prepareCall("{? = call FN_LISTARPLATOS}");
    statement.registerOutParameter(1, Types.REF_CURSOR);
    statement.execute();
    ResultSet resultSet = (ResultSet) statement.getObject(1);
    List<Plato> platos = new ArrayList<>();
    while (resultSet.next()) {
      Plato plato = new Plato(
          resultSet.getInt(1),
          resultSet.getString(2),
          resultSet.getInt(3)
      );
      platos.add(plato);
    }
    return platos;
  }
}
