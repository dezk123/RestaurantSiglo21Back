package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
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
    Conexion con = new Conexion();
    return con.obtenerConexion();
  }

  private String validarToken(String token) {
    return jwtUtil.getValue(token);
  }

  // Obtener todos los usuarios
  @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
  public List<Usuario> obtenerUsuarios(@RequestHeader(value = "Authorization") String token) throws SQLException {
    if (validarToken(token) == null && !validarToken(token).equals("ADM")) { return null; }
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

  // Registrar empleados
  @RequestMapping(value = "api/registro/empleado", method = RequestMethod.POST)
  public String registrarEmpleados(@RequestBody Usuario usuario,
                                   @RequestHeader(value = "Authorization") String token) throws SQLException {
    if (validarToken(token) == null || !validarToken(token).equals("ADM")) { return "El usuario no es valido"; }

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
    return "Usuario registrado";
  }
}
