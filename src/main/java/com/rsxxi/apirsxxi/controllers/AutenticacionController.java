package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Usuario;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@CrossOrigin(origins = {"https://localhost:5001/"}, methods = { RequestMethod.POST })
public class AutenticacionController {

    private Connection configuracion() throws SQLException {
        Conexion con = new Conexion(
                "jdbc:oracle:thin:@3.15.193.194:49161:XE",
                "RSXXI",
                "123"
        );
        return con.obtenerConexion();
    }

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) throws SQLException {
        // System.out.println(usuario.getCorreo());
        Connection con = configuracion();
        Usuario aux = new Usuario();
        aux = aux.buscarUsuario(con, usuario.getCorreo());
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(aux.getContrasena(), usuario.getContrasena())){
            // retornar token
            return jwtUtil.create(aux.getCorreo(), aux.getIdTipoUsuario());
        }
        return "ERROR";
    }
}
