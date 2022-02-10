package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.utils.User;
import com.rsxxi.apirsxxi.utils.Login;
import com.rsxxi.apirsxxi.models.Usuario;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://localhost:5001/", "http://localhost:5000/"}, methods = { RequestMethod.POST })
public class AutenticacionController {

  private Connection configuracion() throws SQLException {
    Conexion con = new Conexion();
    return con.obtenerConexion();
  }

  @Autowired
  private JWTUtil jwtUtil;

  @RequestMapping(value = "api/login", method = RequestMethod.POST)
  public Map<String,String> login(@RequestBody Login login) throws SQLException {
    Connection con = configuracion();
    Usuario aux = new Usuario();
    aux = aux.buscarUsuario(con, login.getCorreo());
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    if(argon2.verify(aux.getContrasena(), login.getContrasena())){
//      retornar token

//      return  new User(aux.getNombre(), jwtUtil.create(aux.getCorreo(), aux.getIdTipoUsuario()),aux.getIdUsuario() );
      Map<String,String> usuario = new HashMap<>();
      usuario.put("idUsuario", Integer.toString(aux.getIdUsuario()));
      usuario.put("nombre", aux.getNombre());
      usuario.put("token",jwtUtil.create(aux.getCorreo(), aux.getIdTipoUsuario()));

      return usuario;
    }
    return null;
  }
}
