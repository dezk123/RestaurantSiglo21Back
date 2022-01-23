package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Insumo;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://localhost:5001", "http://localhost:3000/"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class InventarioController {
  @Autowired
  private JWTUtil jwtUtil;

  // Conexion base de datos
  private Connection configuracion() throws SQLException {
    Conexion con = new Conexion();
    return con.obtenerConexion();
  }

  // Validar token
  private String validarToken(String token) { return jwtUtil.getValue(token); }

  // Obtener productos
  @RequestMapping(value = "api/insumo", method = RequestMethod.GET)
  public List<Insumo> obtenerInsumo(@RequestHeader(value = "Authorization") String token) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return null; }
    Connection connection = configuracion();
    Statement statement = connection.createStatement();
    List<Insumo> productos = new ArrayList<>();
    String query = "SELECT * FROM INSUMO";
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()){
      Insumo aux = new Insumo(
          resultSet.getInt(1),
          resultSet.getInt(2),
          resultSet.getString(3),
          resultSet.getInt(4),
          resultSet.getInt(5)
      );
      productos.add(aux);
    }
    return productos;
  }

  // Obtener producto por id
  @RequestMapping(value = "api/insumo/{id}", method = RequestMethod.GET)
  public Insumo obtenerInsumo(@RequestHeader(value = "Authorization") String token, @PathVariable int id) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return null; }
    Connection connection = configuracion();
    Statement statement = connection.createStatement();
    String query = String.format("SELECT * FROM INSUMO WHERE IDINSUMO = %d", id);
    ResultSet resultSet = statement.executeQuery(query);
    Insumo insumo = new Insumo();
    while(resultSet.next()){
      insumo.setIdInsumo(resultSet.getInt(1));
      insumo.setIdCategoria(resultSet.getInt(2));
      insumo.setNombreInsumo(resultSet.getString(3));
      insumo.setPrecioUnitario(resultSet.getInt(4));
      insumo.setExistencia(resultSet.getInt(5));
    }
    return insumo;
  }

  // Agregar nuevo producto al inventario
  @RequestMapping(value = "api/insumo/agregar", method = RequestMethod.POST)
  public String agregarInsumo(@RequestHeader(value = "Authorization") String token, @RequestBody Insumo insumo) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return "El usuario no es valido"; }
    Connection connection = configuracion();
    CallableStatement statement = connection.prepareCall("{call SP_INSERTARINSUMO(?,?,?,?)}");
    statement.setInt("p_idCategoria", insumo.getIdCategoria());
    statement.setString("p_nombreInsumo", insumo.getNombreInsumo());
    statement.setInt("p_precioUnitario", insumo.getPrecioUnitario());
    statement.setInt("p_existencia", insumo.getExistencia());
    statement.execute();
    connection.close();
    return "Producto agregado";
  }
}
