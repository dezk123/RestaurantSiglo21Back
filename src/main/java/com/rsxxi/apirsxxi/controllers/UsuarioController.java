package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class UsuarioController {

    private Connection configuracion() throws SQLException {
        Conexion con = new Conexion(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "RSXXI",
                "123"
        );
        return con.obtenerConexion();
    }

    @RequestMapping(value = "api/conexion", method = RequestMethod.GET)
    public boolean prueba() throws SQLException {
        Connection con = configuracion();
        return con != null;
    }

    @RequestMapping(value = "api/registro", method = RequestMethod.POST)
    public boolean registrarUsuarios(@RequestBody Usuario usuario) throws SQLException {
        if (usuario != null){
            // Cifrar contrasena
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, usuario.getContrasena());
            usuario.setContrasena(hash);
            Connection con = configuracion();
            // Llamar al procedimiento que se va a usar
            CallableStatement sp = con.prepareCall("{call SP_REGISTRO(?,?,?,?,?,?,?,?)}");
            // Asignar los parametros
            sp.setInt("P_idUsuario", usuario.getIdUsuario());
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
            con.close();
            return true;
        }
        return false;
    }

}
