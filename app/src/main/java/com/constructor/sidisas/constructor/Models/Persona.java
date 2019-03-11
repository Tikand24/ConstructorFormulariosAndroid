package com.constructor.sidisas.constructor.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.io.Serializable;

/**
 * Created by PC-1 on 6/3/2018.
 */

public class Persona implements Serializable {
    private String id;
    private String codigo;
    private String fichaHogar;
    private String tipoDocumento;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String eps;
    private String edad;
    private String genero;

    public Persona(){

    }

    public Persona(String id, String codigo, String fichaHogar, String tipoDocumento, String identificacion, String nombre, String apellido, String fechaNacimiento, String eps, String edad, String genero) {
        this.id = id;
        this.codigo = codigo;
        this.fichaHogar = fichaHogar;
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.eps = eps;
        this.edad = edad;
        this.genero = genero;
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

    public String getFichaHogar() {
        return fichaHogar;
    }

    public void setFichaHogar(String fichaHogar) {
        this.fichaHogar = fichaHogar;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreCompleto(){
        return this.nombre+" "+this.apellido;
    }
    public String generarCodigoPersona(Context context,String idHogar){
        String codigoPersona="1";
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        Cursor personas= db.rawQuery("select COUNT("+Utilidades.PERSONAS_ID+") from "+Utilidades.TABLA_PERSONAS+" WHERE "+Utilidades.PERSONAS_FICHA+"='"+idHogar+"'",null);
        while (personas.moveToNext()){
            codigoPersona=codigoPersona=personas.getString(0);
        }
        con.close();
        return codigoPersona;
    }
    public boolean save(Context context,Persona persona){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db= con.getWritableDatabase();
        ContentValues personaValues=new ContentValues();
        personaValues.put(Utilidades.PERSONAS_ID,persona.getId());
        personaValues.put(Utilidades.PERSONAS_CODIGO,persona.getCodigo());
        personaValues.put(Utilidades.PERSONAS_FICHA,persona.getFichaHogar());
        personaValues.put(Utilidades.PERSONAS_TIPO_DOCUMENTO,persona.getTipoDocumento());
        personaValues.put(Utilidades.PERSONAS_IDENTIFICACION,persona.getIdentificacion());
        personaValues.put(Utilidades.PERSONAS_NOMBRE,persona.getNombre());
        personaValues.put(Utilidades.PERSONAS_APELLIDO,persona.getApellido());
        personaValues.put(Utilidades.PERSONAS_FECHA_NACIMIENTO,persona.getFechaNacimiento());
        personaValues.put(Utilidades.PERSONAS_EPS,persona.getEps());
        personaValues.put(Utilidades.PERSONAS_EDAD,persona.getEdad());
        personaValues.put(Utilidades.PERSONAS_GENERO,persona.getGenero());
        personaValues.put(Utilidades.PERSONAS_USUARIO,"USJO7882151612200659377200");
        personaValues.put(Utilidades.PERSONAS_CREATED_AT,Utilidades.createdAt());
        personaValues.put(Utilidades.PERSONAS_UPDATED_AT,Utilidades.createdAt());
        db.insert(Utilidades.TABLA_PERSONAS,Utilidades.PERSONAS_ID,personaValues);
        con.close();
        return true;
    }
}
