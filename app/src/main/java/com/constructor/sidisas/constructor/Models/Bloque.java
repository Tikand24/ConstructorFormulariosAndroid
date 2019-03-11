package com.constructor.sidisas.constructor.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PC-1 on 19/2/2018.
 */

public class Bloque implements Serializable{
    private String id;
    private String nombre;
    private String orden;
    private String estado;
    private ArrayList<Pregunta> preguntas;

    public Bloque(String id, String nombre, String orden, String estado,ArrayList<Pregunta> preguntas) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.estado = estado;
        this.preguntas = preguntas;
    }
    public Bloque(){}

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

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
