package com.constructor.sidisas.constructor.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;
import com.constructor.sidisas.constructor.Ficha;

import java.io.Serializable;

/**
 * Created by PC-1 on 6/3/2018.
 */

public class FichaHogar implements Serializable{
    private String id;
    private String codigo;
    private String direccion;
    private String zona;
    private String municipio;
    private String barrio;
    private String firma;
    private String personaQueFirma;
    private String clasificacion;
    private String coordenadas;

    public FichaHogar(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getPersonaQueFirma() {
        return personaQueFirma;
    }

    public void setPersonaQueFirma(String personaQueFirma) {
        this.personaQueFirma = personaQueFirma;
    }


    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void guardarFicha(Context context,String firma){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utilidades.FICHA_HOGAR_ID,"DAWS");
        values.put(Utilidades.FICHA_HOGAR_FIRMA,firma);
        db.insert(Utilidades.TABLA_FICHA_HOGAR,Utilidades.FICHA_HOGAR_ID,values);
    }
    public FichaHogar getFichaPorId(Context context,String idFicha){
        FichaHogar fichaHogar=new FichaHogar();
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        String sqlQuery="SELECT "+
                Utilidades.FICHA_HOGAR_ID+","+
                Utilidades.FICHA_HOGAR_CODIGO+","+
                Utilidades.FICHA_HOGAR_NOMECLATURA+","+
                Utilidades.FICHA_HOGAR_ZONA+","+
                Utilidades.FICHA_HOGAR_MUNICIPIO+","+
                Utilidades.FICHA_HOGAR_BARRIO+","+
                Utilidades.FICHA_HOGAR_FIRMA+","+
                Utilidades.FICHA_HOGAR_DOCFIRMA+","+
                Utilidades.FICHA_HOGAR_COORDENADAS+","+
                Utilidades.FICHA_HOGAR_CLASIFICADO+","+
                Utilidades.FICHA_HOGAR_USUARIO+
                " FROM "+Utilidades.TABLA_FICHA_HOGAR+" WHERE "+Utilidades.FICHA_HOGAR_ID+" = '"+idFicha+"'";
        Cursor fichaDb= db.rawQuery(sqlQuery,null);
        while (fichaDb.moveToNext()){
            fichaHogar.setId(fichaDb.getString(0));
            fichaHogar.setCodigo(fichaDb.getString(1));
            fichaHogar.setDireccion(fichaDb.getString(2));
            fichaHogar.setZona(fichaDb.getString(3));
            fichaHogar.setMunicipio(fichaDb.getString(4));
            fichaHogar.setBarrio(fichaDb.getString(5));
            fichaHogar.setFirma(fichaDb.getString(6));
            fichaHogar.setPersonaQueFirma(fichaDb.getString(7));
            fichaHogar.setClasificacion(fichaDb.getString(8));
        }
        return fichaHogar;
    }
}
