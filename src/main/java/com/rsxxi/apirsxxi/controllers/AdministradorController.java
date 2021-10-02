package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Producto;
import com.rsxxi.apirsxxi.models.Usuario;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://localhost:5001/"}, methods = { RequestMethod.GET, RequestMethod.POST })
public class AdministradorController {
  @Autowired
  private JWTUtil jwtUtil;

  private Connection configuracion() throws SQLException {
    Conexion con = new Conexion(
        "jdbc:oracle:thin:@3.15.193.194:49161:XE",
        "RSXXI",
        "123"
    );
    return con.obtenerConexion();
  }

  @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
  public List<Usuario> obtenerUsuarios(@RequestHeader(value = "Authorization") String token) throws SQLException {
    String idTipoUsuario = jwtUtil.getValue(token);
    if(idTipoUsuario == null){ return null; }
    // Comprobar tipo de usuario
    if(!idTipoUsuario.equals("ADM")){ return null; }
    Connection con = configuracion();
    Statement statement = con.createStatement();
    List<Usuario> usuarios = new ArrayList<>();
    String query = "SELECT * FROM USUARIO WHERE IDTIPOUSUARIO = 'CLI'";
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()){
      Usuario aux = new Usuario(
          resultSet.getInt(1),
          resultSet.getString(2),
          resultSet.getString(3),
          null,
          resultSet.getString(5),
          resultSet.getString(6),
          resultSet.getString(7),
          resultSet.getString(8)
      );
      usuarios.add(aux);
    }
    return usuarios;
  }

  @RequestMapping(value = "api/registro/empleado", method = RequestMethod.POST)
  public boolean registrarEmpleado(@RequestBody Usuario usuario,
                                   @RequestHeader(value = "Authorization") String token) throws SQLException {
    String idTipoUsuario = jwtUtil.getValue(token);
    if (idTipoUsuario == null) { return false; }
    if (!idTipoUsuario.equals("ADM")) { return false; }

    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String hash = argon2.hash(1, 1024, 1, usuario.getContrasena());
    usuario.setContrasena(hash);

    Connection connection = configuracion();
    CallableStatement sp = connection.prepareCall("{call SP_REGISTRO(?,?,?,?,?,?,?)}");
    sp.setString("P_idTipoUsuario", usuario.getIdTipoUsuario());
    sp.setString("P_correo", usuario.getCorreo());
    sp.setString("P_contrasena", usuario.getContrasena());
    sp.setString("P_nombre", usuario.getNombre());
    sp.setString("P_apellido", usuario.getApellido());
    sp.setString("P_direccion", usuario.getDireccion());
    sp.setString("P_run", usuario.getRun());
    // Ejecutar procedimiento
    sp.execute();
    // Cerrar conexion
    connection.close();
    return true;
  }

  @RequestMapping(value = "api/productos", method = RequestMethod.GET)
  public List<Producto> obtenerProductos(@RequestHeader(value = "Authorization") String token) throws SQLException {
    String idTipoUsuario = jwtUtil.getValue(token);
    if (idTipoUsuario == null) { return null; }
    if (!idTipoUsuario.equals("ADM")) { return null; }
    List<Producto> productos = new ArrayList<>();
    Connection connection = configuracion();
    Statement statement = connection.createStatement();
    String query = "SELECT * FROM PRODUCTO;";
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


}
