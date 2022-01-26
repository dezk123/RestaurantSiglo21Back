package com.rsxxi.apirsxxi.controllers;

import com.rsxxi.apirsxxi.connection.Conexion;
import com.rsxxi.apirsxxi.models.Reserva;
import com.rsxxi.apirsxxi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://localhost:44379", "https://localhost:44379"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ReservaController {
    @Autowired
    private JWTUtil jwtUtil;

    // Conexion base de datos
    private Connection configuracion() throws SQLException {
        Conexion con = new Conexion();
        return con.obtenerConexion();
    }

    // Validar token
    private String validarToken(String token) { return jwtUtil.getValue(token); }

    // Crear reserva
    @RequestMapping(value = "api/cliente/reserva", method = RequestMethod.POST)
    public String crearReserva(@RequestHeader(value = "Authorization") String token, @RequestBody Reserva reserva) throws SQLException {
        if(validarToken(token) == null || !validarToken(token).equals("CLI")) { return null; }
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{call SP_INSERTARRESERVA(?,?,?,?,?,?)}");
        statement.setInt("p_idUsuario", reserva.getIdUsuario());
        statement.setString("p_idTipoUsuario", reserva.getTipoUsuario());
        statement.setInt("p_idMesa", reserva.getIdMesa());
        statement.setDate("p_fecha", reserva.getFecha());
        statement.setInt("p_cantidadPersona", reserva.getCantidadPersona());
        statement.setBoolean("p_estado", reserva.isEstado());
        statement.execute();
        connection.close();
        return "Reserva creada";
    }

    // Cancelar reserva
    @RequestMapping(value = "api/cliente/cancelar-reserva", method = RequestMethod.PUT)
    public String cancelarReserva(@RequestHeader(value = "Authorization") String token, @RequestBody Reserva reserva) throws SQLException {
        if(validarToken(token) == null || !validarToken(token).equals("CLI")) { return null; }
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{call SP_ELIMINARRESERVA(?,?)}");
        statement.setInt("p_idReserva", reserva.getIdReserva());
        statement.setBoolean("p_estado", reserva.isEstado());
        statement.execute();
        connection.close();
        return "Reserva cancelada";
    }

    //Actualizar Reserva
    @RequestMapping(value = "api/cliente/actualiza-reserva", method = RequestMethod.PUT)
    public String actualizaReserva(@RequestHeader(value = "Autorization") String token,@RequestBody Reserva reserva) throws  SQLException{
        if(validarToken(token) == null || !validarToken(token).equals("CLI")) { return null; }
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{call SP_ACTUALIZARRESERVA(?,?,?,?,?,?,?)}");
        statement.setInt("p_idReserva", reserva.getIdReserva());
        statement.setInt("p_cantidadPersona",reserva.getCantidadPersona());
        statement.setDate("p_fecha", reserva.getFecha());
        statement.setInt("p_idMesa",reserva.getIdMesa());
        statement.setInt("p_idUsuario", reserva.getIdUsuario());
        statement.setString("p_idTipoUsuario", reserva.getTipoUsuario());
        statement.setBoolean("p_estado", reserva.isEstado());
        statement.execute();
        connection.close();
        return "Reserva Actualizada";
    }

    // Obtener todas las reservas
    @RequestMapping(value = "api/reservas", method = RequestMethod.GET)
    public List<Reserva> obtenerReservas(@RequestHeader(value = "Authorization") String token) throws SQLException {
        if(validarToken(token) == null || validarToken(token).equals("CLI")) { return null; }
        List<Reserva> reservas = new ArrayList<>();
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{? = call FN_LISTARRESERVAS()}");
        statement.registerOutParameter(1, Types.REF_CURSOR);
        statement.execute();
        ResultSet resultSet = (ResultSet) statement.getObject(1);
        while (resultSet.next()) {
            Reserva reserva = new Reserva(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getDate(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getBoolean(7)
            );
            reservas.add(reserva);
        }
        return reservas;
    }

    // Obtener reservas por id de usuario
    @RequestMapping(value = "api/reservas/{id}", method = RequestMethod.GET)
    public List<Reserva> obtenerReservaId(@RequestHeader(value = "Authorization") String token, @PathVariable int id) throws SQLException {
        if(validarToken(token) == null || !validarToken(token).equals("CLI")) { return null; }
        List<Reserva> reservas = new ArrayList<>();
        Connection connection = configuracion();
        CallableStatement statement = connection.prepareCall("{? = call FN_LISTARRESERVAID(?)}");
        statement.registerOutParameter(1, Types.REF_CURSOR);
        statement.setInt(2, id);
        statement.execute();
        ResultSet resultSet = (ResultSet) statement.getObject(1);
        while (resultSet.next()) {
            Reserva reserva = new Reserva(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getDate(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getString(6),
                resultSet.getBoolean(7)
            );
            reservas.add(reserva);
        }
        return reservas;
    }
}
