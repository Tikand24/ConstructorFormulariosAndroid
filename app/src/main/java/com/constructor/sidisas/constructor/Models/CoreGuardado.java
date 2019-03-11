package com.constructor.sidisas.constructor.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PC-1 on 5/3/2018.
 */

public class CoreGuardado implements Serializable{
    private String pregunta;
    private ArrayList<Integer> idElemento;
    private ArrayList<Respuesta> idRespuesta;
    private String tipoPregunta;
    private String descripcion;
    private String formatoRedirecion;
    private Boolean requerido;
    private ArrayList<Respuesta> respuestas;

    public CoreGuardado(){
    }

    public ArrayList<Integer> getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(ArrayList<Integer> idElemento) {
        this.idElemento = idElemento;
    }

    public ArrayList<Respuesta> getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(ArrayList<Respuesta> idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormatoRedirecion() {
        return formatoRedirecion;
    }

    public void setFormatoRedirecion(String formatoRedirecion) {
        this.formatoRedirecion = formatoRedirecion;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public boolean setTarea(String idPersona,String formato){
        if (idPersona.isEmpty() || formato.isEmpty()){
            return false;
        }
        if (formato.equals("1") || formato.equals("2") || formato.equals("7") || formato.equals("11") ){
            return false;
        }

        return true;
    }
}
