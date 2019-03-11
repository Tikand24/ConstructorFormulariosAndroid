package com.constructor.sidisas.constructor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.constructor.sidisas.constructor.Models.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PC-1 on 19/2/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_ACTUALIZACION_DB);
        db.execSQL(Utilidades.CREAR_TABLA_AGENDAMIENTOS);
        db.execSQL(Utilidades.CREAR_TABLA_AIEPI);
        db.execSQL(Utilidades.CREAR_TABLA_ARCHIVOS_ENFERMERIA);
        db.execSQL(Utilidades.CREAR_TABLA_ARCHIVOS_HOGAR);
        db.execSQL(Utilidades.CREAR_TABLA_ARCHIVOS_MEDICO);
        db.execSQL(Utilidades.CREAR_TABLA_ARCHIVOS_PSICOLOGIA);
        db.execSQL(Utilidades.CREAR_TABLA_ATENCION_CANALIZACION);
        db.execSQL(Utilidades.CREAR_TABLA_BARRIOS);
        db.execSQL(Utilidades.CREAR_TABLA_BLOQUE);
        db.execSQL(Utilidades.CREAR_TABLA_CANALIZACION);
        db.execSQL(Utilidades.CREAR_TABLA_CANCER_MAMA);
        db.execSQL(Utilidades.CREAR_TABLA_CIE10);
        db.execSQL(Utilidades.CREAR_TABLA_CONFIGURACIONES);
        db.execSQL(Utilidades.CREAR_TABLA_DEMANDA_INDUCIDA);
        db.execSQL(Utilidades.CREAR_TABLA_DEPARTAMENTO);
        db.execSQL(Utilidades.CREAR_TABLA_DIAGNOSTICO_MEDICINA);
        db.execSQL(Utilidades.CREAR_TABLA_ENFERMERIA);
        db.execSQL(Utilidades.CREAR_TABLA_ENTIDAD);
        db.execSQL(Utilidades.CREAR_TABLA_EPS);
        db.execSQL(Utilidades.CREAR_TABLA_FICHA_HOGAR);
        db.execSQL(Utilidades.CREAR_TABLA_FORMATO);
        db.execSQL(Utilidades.CREAR_TABLA_HOJA_TRABAJO);
        db.execSQL(Utilidades.CREAR_TABLA_KARDEX);
        db.execSQL(Utilidades.CREAR_TABLA_MEDICINA);
        db.execSQL(Utilidades.CREAR_TABLA_MUNICIPIO);
        db.execSQL(Utilidades.CREAR_TABLA_OBSERVACIONES);
        db.execSQL(Utilidades.CREAR_TABLA_PASSWROD_RESET);
        db.execSQL(Utilidades.CREAR_TABLA_PERMISOS);
        db.execSQL(Utilidades.CREAR_TABLA_PERMISOS_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_PERSONAS);
        db.execSQL(Utilidades.CREAR_TABLA_PREGUNTAS);
        db.execSQL(Utilidades.CREAR_TABLA_PROMOCION);
        db.execSQL(Utilidades.CREAR_TABLA_PROVINCIAS);
        db.execSQL(Utilidades.CREAR_TABLA_PSICOLOGIA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_AIEPI);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_CANALIZACION);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_CANCER_MAMA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_DEMANDA_INDUCIDA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_ENFERMERIA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_HOGAR);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_KARDEX);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_MEDICINA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_PERSONA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_PROMOCION);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_PSICOLOGIA);
        db.execSQL(Utilidades.CREAR_TABLA_RESPUESTAS_TEST_FINDRISK);
        db.execSQL(Utilidades.CREAR_TABLA_SERVICIOS);
        db.execSQL(Utilidades.CREAR_TABLA_SERVICIOS_ENTIDAD);
        db.execSQL(Utilidades.CREAR_TABLA_TEST_FINDRISK);
        db.execSQL(Utilidades.CREAR_TABLA_TIPO_DOC);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        poblarDataBase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ACTUALIZACION_DB);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_AGENDAMIENTOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_AIEPI);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ARCHIVOS_ENFERMERIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ARCHIVOS_HOGAR);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ARCHIVOS_MEDICO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ARCHIVOS_PSICOLOGIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ATENCION_CANALIZACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_BARRIOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_BLOQUE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CANALIZACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CANCER_MAMA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CIE10);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CONFIGURACIONES);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_DEMANDA_INDUCIDA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_DEPARTAMENTO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_DIAGNOSTICO_MEDICINA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ENFERMERIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ENTIDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_EPS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_FICHA_HOGAR);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_FORMATO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_HOJA_TRABAJO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_KARDEX);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MUNICIPIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MEDICINA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_OBSERVACIONES);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PROVINCIAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PREGUNTA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PASSWORD_RESET);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PERMISOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PERMISOS_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PERSONAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PROMOCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PSICOLOGIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_AIEPI);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_CANALIZACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_CANCER_MAMA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_DEMANDA_INDUCIDA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_ENFERMERIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_HOGAR);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_KARDEX);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_MEDICINA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_PERSONA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_PROMOCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_PSICOLOGIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESPUESTAS_TEST_FINDRISK);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SERVICIOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SERVICIOS_ENTIDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_TEST_FINDRIKS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_TIPO_DOC);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        onCreate(db);
    }
    public void poblarDataBase(SQLiteDatabase db){
        ContentValues values;
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Crear ficha");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Pendientes");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Consultas");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Archivos ficha");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Medicina");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Enfermeria");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Psicologia");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Canalizacion");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Agendamientos");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Indicadores");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Usuarios");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Descargar documentos");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.PERMISOS_DESCRIPCION,"Parametros");
        db.insert(Utilidades.TABLA_PERMISOS,Utilidades.PERMISOS_ID,values);
        values=new ContentValues();
        values.put(Utilidades.TIPO_DOC_CODIGO,"CC");
        values.put(Utilidades.TIPO_DOC_DESCRIPCION,"Cédula de ciudadanía");
        values.put(Utilidades.TIPO_DOC_LONGITUD,"13");
        db.insert(Utilidades.TABLA_TIPO_DOC,Utilidades.TIPO_DOC_ID,values);
        values=new ContentValues();
        values.put(Utilidades.TIPO_DOC_CODIGO,"TI");
        values.put(Utilidades.TIPO_DOC_DESCRIPCION,"Tarjeta de identidad");
        values.put(Utilidades.TIPO_DOC_LONGITUD,"13");
        db.insert(Utilidades.TABLA_TIPO_DOC,Utilidades.TIPO_DOC_ID,values);
        values=new ContentValues();
        values.put(Utilidades.TIPO_DOC_CODIGO,"RC");
        values.put(Utilidades.TIPO_DOC_DESCRIPCION,"Registro civil");
        values.put(Utilidades.TIPO_DOC_LONGITUD,"15");
        db.insert(Utilidades.TABLA_TIPO_DOC,Utilidades.TIPO_DOC_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Ficha Hogar");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Ficha Personas");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Aiepi");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Kardex");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Cancer de mama");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Test de findrisk");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Demanda Inducida");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Medicina");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Enfermeria");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Psicologia");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
        values=new ContentValues();
        values.put(Utilidades.FORMATO_NOMBRE,"Promoción");
        db.insert(Utilidades.TABLA_FORMATO,Utilidades.FORMATO_ID,values);
    }
}
