package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 7/3/2018.
 */

public class Municipio {
    private String id;
    private String codigo;
    private String nombre;
    private String departamento;
    private String provincia;
    private String usuario;

    public Municipio(String id, String codigo, String nombre, String departamento, String provincia, String usuario) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
        this.provincia = provincia;
        this.usuario = usuario;
    }

    public Municipio(){

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Municipio> getMunicipiosDb(Context context,String idDepartamento){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ArrayList<Municipio> municipios=new ArrayList<Municipio>();
        Cursor mun= db.rawQuery("select * from "+Utilidades.TABLA_MUNICIPIO+" WHERE "+Utilidades.MUNICIPIO_DEPARTAMENTO+"="+idDepartamento,null);
        while (mun.moveToNext()){
            municipios.add(new Municipio(mun.getString(0),mun.getString(1),mun.getString(2),mun.getString(3),mun.getString(4),mun.getString(5)));
        }
        con.close();
        return municipios;
    }
}
