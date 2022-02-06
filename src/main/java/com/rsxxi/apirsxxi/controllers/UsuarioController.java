package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Usuario;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = {"https://localhost:5001", "http://localhost:5000"}, methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioController {

  @Autowired
  private JWTUtil jwtUtil;

  private Connection configuracion() throws SQLException {
    Conexion con = new Conexion();
    return con.obtenerConexion();
  }

  // Comprobar conexion con la base de datos
  @RequestMapping(value = "api/conexion", method = RequestMethod.GET)
  public boolean prueba() throws SQLException {
    Connection con = configuracion();
    return con != null;
  }

  // Registro de usuario
  @RequestMapping(value = "api/registro", method = RequestMethod.POST)
  public String registrarUsuarios(@RequestBody Usuario usuario) throws SQLException {
    if (usuario != null){
      // Cifrar contrasena
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      String hash = argon2.hash(1, 1024, 1, usuario.getContrasena());
      usuario.setContrasena(hash);
      Connection con = configuracion();
      // Llamar al procedimiento que se va a usar
      CallableStatement sp = con.prepareCall("{call SP_CREARUSUARIO(?,?,?,?,?,?,?,?)}");
      // Asignar los parametros
      sp.setString("P_idTipoUsuario", "CLI");
      sp.setString("P_correo", usuario.getCorreo());
      sp.setString("P_contrasena", usuario.getContrasena());
      sp.setString("P_nombre", usuario.getNombre());
      sp.setString("P_apellido", usuario.getApellido());
      sp.setString("P_direccion", usuario.getDireccion());
      sp.setString("P_run", usuario.getRun());
      sp.setString("P_nombreUsuario", usuario.getNombreUsuario());
      // Ejecutar procedimiento
      sp.execute();
      // Cerrar conexion
      con.close();
      return "Usuario registrado";
    }
    return "Usuario no registrado";
  }

  // Comprobar el tipo de usuario a traves del token
  @RequestMapping(value = "api/usuario/tipousuario", method = RequestMethod.GET)
  public String obtenerTipoUsuario(@RequestHeader(value = "Authorization") String token) {
    return jwtUtil.getValue(token);
  }
}
