package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Reserva;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@CrossOrigin(origins = {"https://localhost:5001"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ReservaController {
    @Autowired
    private JWTUtil jwtUtil;

    // Conexion base de datos
    private Connection configuracion() throws SQLException {
        Conexion con = new Conexion(
                "jdbc:oracle:thin:@18.116.63.103:49161:XE",
                "RSXII",
                "123"
        );
        return con.obtenerConexion();
    }

    // Validar token
    private String validarToken(String token) { return jwtUtil.getValue(token); }

    // Crear reserva
    @RequestMapping(value = "api/cliente/reserva", method = RequestMethod.POST)
    public String crearReserva(@RequestHeader(value = "Authorization") String token, @RequestBody Reserva reserva) throws SQLException {
        if(validarToken(token) == null || !validarToken(token).equals("CLI")) { return null; }
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{call SP_CREARRESERVA(?,?,?,?,?,?)}");
        statement.setInt("p_idUsuario", reserva.getIdUsuario());
        statement.setString("p_idTipoUsuario", reserva.getTipoUsuario());
        statement.setInt("p_idMesa", reserva.getIdMesa());
        statement.setDate("p_fecha", reserva.getFecha());
        statement.setInt("p_cantidadPersona", reserva.getCantidadPersona());
        statement.setString("p_estado", reserva.getEstado());
        statement.execute();
        connection.close();
        return "Reserva creada";
    }
}
