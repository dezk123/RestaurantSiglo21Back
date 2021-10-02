package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Usuario;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<Usuario> usuarios(@RequestHeader(value = "Authorization") String token) throws SQLException {
        String idTipoUsuario = jwtUtil.getValue(token);

        if(idTipoUsuario == null){ return new ArrayList<>(); }

        // Comprobar tipo de usuario
        if(!idTipoUsuario.equals("ADM")){ return new ArrayList<>(); }

        Connection con = configuracion();
        Statement statement = con.createStatement();
        List<Usuario> usuarios = new ArrayList<Usuario>();

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
}
