package com.constructor.sidisas.constructor.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PC-1 on 19/2/2018.
 */

public class Pregunta implements Serializable{
    private String id;
    private String nombre;
    private String tipo;
    private String validacion;
    private String longitud;
    private String orden;
    private String estado;
    private ArrayList<Respuesta> respuestas;

    public Pregunta(){

    }

    public Pregunta(String id, String nombre, String tipo, String validacion, String longitud, String orden, String estado, ArrayList<Respuesta> respuestas) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.validacion = validacion;
        this.longitud = longitud;
        this.orden = orden;
        this.estado = estado;
        this.respuestas = respuestas;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
