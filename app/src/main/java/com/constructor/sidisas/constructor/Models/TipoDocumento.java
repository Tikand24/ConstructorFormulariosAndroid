package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 13/3/2018.
 */

public class TipoDocumento {
    private String id;
    private String codigo;
    private String descripcion;
    private String longitud;

    public TipoDocumento(){

    }

    public TipoDocumento(String id, String codigo, String descripcion, String longitud) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.longitud = longitud;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public ArrayList<TipoDocumento> tipoDocumentoDb(Context context){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ArrayList<TipoDocumento> tipoDocs=new ArrayList<TipoDocumento>();
        Cursor tpDoc= db.rawQuery("select * from "+Utilidades.TABLA_TIPO_DOC,null);
        while (tpDoc.moveToNext()){
            tipoDocs.add(new TipoDocumento(tpDoc.getString(0),tpDoc.getString(1),tpDoc.getString(2),tpDoc.getString(3)));
        }
        con.close();
        return tipoDocs;
    }
}
