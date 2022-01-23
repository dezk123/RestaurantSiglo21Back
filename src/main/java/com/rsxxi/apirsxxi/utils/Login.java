package com.rsxxi.apirsxxi.utils;

import lombok.Getter;
import lombok.Setter;

public class Login {
    @Getter @Setter
    private String correo;

    @Getter @Setter
    private String contrasena;

    public Login(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
