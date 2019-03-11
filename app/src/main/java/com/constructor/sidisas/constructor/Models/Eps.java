package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 14/3/2018.
 */

public class Eps {
    private String id;
    private String descripcion;
    private String usuario;

    public Eps(){

    }

    public Eps(String id, String descripcion, String usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Eps> epsDb(Context context){
        ArrayList<Eps> eps=new ArrayList<Eps>();
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        Cursor epsQuery= db.rawQuery("select * from "+Utilidades.TABLA_EPS,null);
        while (epsQuery.moveToNext()){
            eps.add(new Eps(epsQuery.getString(0),epsQuery.getString(1),epsQuery.getString(2)));
        }
        con.close();
        return eps;
    }
    public String buscarEpsPorNombre(Context context, String[] nombre){
        String idEps="";
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        Cursor epsQuery= db.query(Utilidades.TABLA_EPS,null,Utilidades.EPS_NOMBRE+"=?",nombre,null,null,null);
        while (epsQuery.moveToNext()){
            idEps=epsQuery.getString(0);
        }
        con.close();
        return idEps;
    }
}
