package com.constructor.sidisas.constructor.Models;

import java.io.Serializable;

/**
 * Created by PC-1 on 19/2/2018.
 */

public class Respuesta implements Serializable{
    private String id;
    private String nombre;
    private String formatoRedireccion;
    private String estado;

    public Respuesta(String id, String nombre, String formatoRedireccion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.formatoRedireccion = formatoRedireccion;
        this.estado = estado;
    }
    public Respuesta(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormatoRedireccion() {
        return formatoRedireccion;
    }

    public void setFormatoRedireccion(String formatoRedireccion) {
        this.formatoRedireccion = formatoRedireccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
