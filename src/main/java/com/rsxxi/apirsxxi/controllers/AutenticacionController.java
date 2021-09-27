package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class AutenticacionController {

    private Connection configuracion() throws SQLException {
        Conexion con = new Conexion(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "RSXXI",
                "123"
        );
        return con.obtenerConexion();
    }

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public Usuario login(@RequestBody Usuario usuario) throws SQLException {
        System.out.println(usuario.getCorreo());
        Connection con = configuracion();
        Statement st = con.createStatement();
        // Generar query
        String query = String.format("SELECT * FROM USUARIO WHERE CORREO = '%s'", usuario.getCorreo());
        ResultSet res = st.executeQuery(query);
        // comprobar usuario
        Usuario aux = new Usuario();
        while (res.next()){
            aux.setIdUsuario(res.getInt(1));
            aux.setIdTipoUsuario(res.getString(2));
            aux.setCorreo(res.getString(3));
            aux.setContrasena(res.getString(4));
            aux.setNombre(res.getString(5));
            aux.setApellido(res.getString(6));
            aux.setDireccion(res.getString(7));
            aux.setRun(res.getString(8));
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(aux.getContrasena(), usuario.getContrasena())){
            return aux;
        }
        return null;
    }
}
