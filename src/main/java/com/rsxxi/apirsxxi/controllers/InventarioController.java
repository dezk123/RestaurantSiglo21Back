package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Producto;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://localhost:5001"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
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
  @RequestMapping(value = "api/productos", method = RequestMethod.GET)
  public List<Producto> obtenerProductos(@RequestHeader(value = "Authorization") String token) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return null; }
    Connection connection = configuracion();
    Statement statement = connection.createStatement();
    List<Producto> productos = new ArrayList<>();
    String query = "SELECT * FROM PRODUCTO";
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()){
      Producto aux = new Producto(
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
  @RequestMapping(value = "api/productos/{id}", method = RequestMethod.GET)
  public Producto obtenerProducto(@RequestHeader(value = "Authorization") String token, @PathVariable int id) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return null; }
    Connection connection = configuracion();
    Statement statement = connection.createStatement();
    String query = String.format("SELECT * FROM PRODUCTO WHERE IDPRODUCTO = %d", id);
    ResultSet resultSet = statement.executeQuery(query);
    return new Producto(
        resultSet.getInt(1),
        resultSet.getInt(2),
        resultSet.getString(3),
        resultSet.getInt(4),
        resultSet.getInt(5)
    );
  }

  // Agregar nuevo producto al inventario
  @RequestMapping(value = "api/productos/agregar", method = RequestMethod.POST)
  public String agregarProducto(@RequestHeader(value = "Authorization") String token, @RequestBody Producto producto) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return "El usuario no es valido"; }
    Connection connection = configuracion();
    CallableStatement statement = connection.prepareCall("{call SP_INSERPROD(?,?,?,?)}");
    statement.setInt("p_idCategoria", producto.getIdCategoriaProducto());
    statement.setString("p_nomProd", producto.getNombreProducto());
    statement.setInt("p_precioUni", producto.getPrecioUnitario());
    statement.setInt("p_existencia", producto.getExistencia());
    statement.execute();
    connection.close();
    return "Producto agregado";
  }

  // Actualizar producto del inventario
  // Aumentar o disminuir inventario
  @RequestMapping(value = "api/productos/actualizar", method = RequestMethod.PUT)
  public String actualizarProducto(@RequestHeader(value = "Authorization") String token, @RequestBody Producto producto) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return "El usuario no es valido"; }
    Connection connection = configuracion();
    CallableStatement statement = connection.prepareCall("{call }");

    statement.execute();
    connection.close();
    return "Producto actualizado";
  }
}
