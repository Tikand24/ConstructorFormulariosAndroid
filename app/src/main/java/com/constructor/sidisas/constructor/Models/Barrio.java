package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 8/3/2018.
 */

public class Barrio {
    private String id;
    private String nombre;
    private String municipio;
    private String tipo;
    private String comuna;
    private String coordenadas;
    private String usuario;

    public Barrio(){

    }

    public Barrio(String id, String nombre, String municipio, String tipo, String comuna, String coordenadas, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.municipio = municipio;
        this.tipo = tipo;
        this.comuna = comuna;
        this.coordenadas = coordenadas;
        this.usuario = usuario;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Barrio> getBarrioDb(Context context,String zona,String municipio){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ArrayList<Barrio> barrios=new ArrayList<Barrio>();
        Cursor barr= db.rawQuery("select * from "+Utilidades.TABLA_BARRIOS+" WHERE "+Utilidades.BARRIOS_TIPO+"='"+zona+"' AND "+Utilidades.BARRIOS_MUNICIPIO+"="+municipio,null);
        while (barr.moveToNext()){
            barrios.add(new Barrio(barr.getString(0),barr.getString(1),barr.getString(2),barr.getString(3),barr.getString(4),barr.getString(5),barr.getString(6)));
        }
        con.close();
        return barrios;
    }
}
