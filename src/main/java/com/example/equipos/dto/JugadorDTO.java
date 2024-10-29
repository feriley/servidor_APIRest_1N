package com.example.equipos.dto;


public class JugadorDTO {
    private Long idjugador;
    private String nombre;
    private int edad;
    private String nombreEquipo;


public Long getIdjugador() {
    return idjugador;
}

public void setIdjugador(Long idjugador) {
    this.idjugador = idjugador;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public int getEdad() {
    return edad;
}

public void setEdad(int edad) {
    this.edad = edad;
}

public String getNombreEquipo() {
    return nombreEquipo;
}

public void setNombreEquipo(String nombreEquipo) {
    this.nombreEquipo = nombreEquipo;
}

}