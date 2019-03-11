package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 7/3/2018.
 */

public class Departamento {
    private String id;
    private String codigo;
    private String nombre;

    public Departamento(){

    }

    public Departamento(String id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
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

    public ArrayList<Departamento> getDepartamentosDb(Context context){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ArrayList<Departamento> departamentos=new ArrayList<Departamento>();
        Cursor dep= db.query(Utilidades.TABLA_DEPARTAMENTO,null,null,null,null,null,Utilidades.DEPARTAMENTO_NOMBRE+" ASC");
        while (dep.moveToNext()){
            departamentos.add(new Departamento(dep.getString(0),dep.getString(1),dep.getString(2)));
        }
        con.close();
        return departamentos;
    }
}
