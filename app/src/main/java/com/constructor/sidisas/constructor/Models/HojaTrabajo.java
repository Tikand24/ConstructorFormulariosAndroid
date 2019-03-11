package com.constructor.sidisas.constructor.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by PC-1 on 15/3/2018.
 */

public class HojaTrabajo {
    private String id;
    private String persona;
    private String usuario;
    private String formato;
    private String estado;
    private Persona personaObj;

    public HojaTrabajo(){

    }

    public HojaTrabajo(String id, String persona, String usuario, String formato, String estado, Persona personaObj) {
        this.id = id;
        this.persona = persona;
        this.usuario = usuario;
        this.formato = formato;
        this.estado = estado;
        this.personaObj = personaObj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPersonaObj() {
        return personaObj;
    }

    public void setPersonaObj(Persona personaObj) {
        this.personaObj = personaObj;
    }

    public boolean guardarTarea(Context context, String idPersona, String formato){
        try {
            if (idPersona.equals("") || formato.equals(""))return false;
            if (formato.equals("1") || formato.equals("2"))return false;
            ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
            SQLiteDatabase db= con.getWritableDatabase();
            String sqlQuery="SELECT "+Utilidades.PERSONAS_GENERO+","+Utilidades.PERSONAS_FECHA_NACIMIENTO+
                    " FROM "+Utilidades.TABLA_PERSONAS+
                    " WHERE "+Utilidades.PERSONAS_ID+"='"+idPersona+"'";
            Cursor persona= db.rawQuery(sqlQuery,null);
            String fechaNacimiento="";
            String genero="";
            while (persona.moveToNext()){
                fechaNacimiento=persona.getString(1);
                genero=persona.getString(0);
            }
            if (fechaNacimiento.equals("") || genero.equals(""))return false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date hoy=new Date();
            Date fechaInicial=dateFormat.parse(fechaNacimiento);
            Date fechaFinal = dateFormat.parse(dateFormat.format(hoy));
            int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
            if (formato.equals("3")){
                if (dias>=61 && dias<=1825){
                    return save(context,new HojaTrabajo(Utilidades.generarId("HT"),idPersona,"USJO7882151612200659377200",formato,"Activo",new Persona()));
                }
            }
            if (formato.equals("4")){
                if (genero.equals("Femenino") && dias>=3650){
                    return save(context,new HojaTrabajo(Utilidades.generarId("HT"),idPersona,"USJO7882151612200659377200",formato,"Activo",new Persona()));
                }
            }
            if (formato.equals("5")){
                if (genero.equals("Femenino") && dias>=5110){
                    return save(context,new HojaTrabajo(Utilidades.generarId("HT"),idPersona,"USJO7882151612200659377200",formato,"Activo",new Persona()));
                }
            }
            if (formato.equals("6")){
                if (dias>=6570){
                    return save(context,new HojaTrabajo(Utilidades.generarId("HT"),idPersona,"USJO7882151612200659377200",formato,"Activo",new Persona()));
                }
            }
            return save(context,new HojaTrabajo(Utilidades.generarId("HT"),idPersona,"USJO7882151612200659377200",formato,"Activo",new Persona()));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean save(Context context,HojaTrabajo hojaTrabajo){
        try  {
            ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues valuesHojaTrabajo = new ContentValues();
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_ID, hojaTrabajo.getId());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_PERSONA, hojaTrabajo.getPersona());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_USUARIO, hojaTrabajo.getUsuario());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_FORMATO, hojaTrabajo.getFormato());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_ESTADO, hojaTrabajo.getEstado());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_CREATED_AT, Utilidades.createdAt());
            valuesHojaTrabajo.put(Utilidades.HOJA_TRABAJO_UPDATED_AT, Utilidades.createdAt());
            db.insert(Utilidades.TABLA_HOJA_TRABAJO, Utilidades.HOJA_TRABAJO_ID, valuesHojaTrabajo);
            return true;
        }catch (SQLiteException e){
            return false;
        }
    }

    public ArrayList<ArrayList<HojaTrabajo>> allTareasPendientes(Context context, String idFichaHogar){
        ArrayList<Persona> personas=new ArrayList<Persona>();
        ArrayList<HojaTrabajo> tareas=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> aiepi=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> kardex=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> cancer=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> test=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> demanda=new ArrayList<HojaTrabajo>();
        ArrayList<HojaTrabajo> promocion=new ArrayList<HojaTrabajo>();
        ArrayList<ArrayList<HojaTrabajo>> datos=new ArrayList<ArrayList<HojaTrabajo>>();

        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        String sqlConsulta="SELECT " +
                Utilidades.PERSONAS_ID+","+
                Utilidades.PERSONAS_CODIGO+","+
                Utilidades.PERSONAS_FICHA+","+
                Utilidades.PERSONAS_TIPO_DOCUMENTO+","+
                Utilidades.PERSONAS_IDENTIFICACION+","+
                Utilidades.PERSONAS_NOMBRE+","+
                Utilidades.PERSONAS_APELLIDO+","+
                Utilidades.PERSONAS_FECHA_NACIMIENTO+","+
                Utilidades.PERSONAS_EPS+","+
                Utilidades.PERSONAS_EDAD+","+
                Utilidades.PERSONAS_GENERO+
                " FROM "+Utilidades.TABLA_PERSONAS+" WHERE "+Utilidades.PERSONAS_FICHA+" = '"+idFichaHogar+"' ";
        Log.i("SQL",sqlConsulta);
        Cursor personaDb=db.rawQuery(sqlConsulta,null);
        while (personaDb.moveToNext()){
            personas.add(new Persona(
                    personaDb.getString(0),
                    personaDb.getString(1),
                    personaDb.getString(2),
                    personaDb.getString(3),
                    personaDb.getString(4),
                    personaDb.getString(5),
                    personaDb.getString(6),
                    personaDb.getString(7),
                    personaDb.getString(8),
                    personaDb.getString(9),
                    personaDb.getString(10)));
        }
        Log.i("per",""+personas.size());
        for (int i=0;i<personas.size();i++){
            String sqlConsultaHojaTrabajo="SELECT "+
                    Utilidades.HOJA_TRABAJO_ID+","+
                    Utilidades.HOJA_TRABAJO_PERSONA+","+
                    Utilidades.HOJA_TRABAJO_USUARIO+","+
                    Utilidades.HOJA_TRABAJO_FORMATO+","+
                    Utilidades.HOJA_TRABAJO_ESTADO+
                    " FROM "+Utilidades.TABLA_HOJA_TRABAJO+" WHERE "+Utilidades.HOJA_TRABAJO_ESTADO+" = 'Activo' AND "+Utilidades.HOJA_TRABAJO_PERSONA+" = '"+personas.get(i).getId()+"'";
            Cursor hojaTrabajoDb=db.rawQuery(sqlConsultaHojaTrabajo,null);
            while (hojaTrabajoDb.moveToNext()){
                tareas.add(new HojaTrabajo(hojaTrabajoDb.getString(0),hojaTrabajoDb.getString(1),hojaTrabajoDb.getString(2),hojaTrabajoDb.getString(3),hojaTrabajoDb.getString(4),personas.get(i)));
            }

            Log.i("Tareas",""+tareas.size());
            for (int j=0; j<tareas.size();j++){
                Log.i("idTarea",tareas.get(j).getId());
                switch (tareas.get(j).getFormato()){
                    case "3":
                        aiepi.add(tareas.get(j));
                        break;
                    case "4":
                        kardex.add(tareas.get(j));
                        break;
                    case "5":
                        cancer.add(tareas.get(j));
                        break;
                    case "6":
                        test.add(tareas.get(j));
                        break;
                    case "7":
                        demanda.add(tareas.get(j));
                        break;
                    case "11":
                        promocion.add(tareas.get(j));
                        break;
                    default:
                        break;
                }
            }
        }
        con.close();
        datos.add(aiepi);
        datos.add(kardex);
        datos.add(cancer);
        datos.add(test);
        datos.add(demanda);
        datos.add(promocion);
        return datos;
    }
    public void getAllPendientes(Context context){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(context,"newaps",null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        String consultaSql="SELECT "+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_ID+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_PERSONA+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_USUARIO+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_FORMATO+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_ESTADO+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_CREATED_AT+","+
                Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_UPDATED_AT+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_CODIGO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_FICHA+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_TIPO_DOCUMENTO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_IDENTIFICACION+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_NOMBRE+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_APELLIDO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_FECHA_NACIMIENTO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_EPS+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_EDAD+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_GENERO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_USUARIO+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_CREATED_AT+","+
                Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_UPDATED_AT+
                " FROM "+Utilidades.TABLA_HOJA_TRABAJO+","+Utilidades.TABLA_PERSONAS+
                " where "+Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_ESTADO+"='Activo' "+
                " AND "+Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_FORMATO+" BETWEEN 3 and 7 "+
                " GROUP BY "+Utilidades.TABLA_PERSONAS+"." +Utilidades.PERSONAS_FICHA+
                " limit 0,10";
        Cursor datos1=db.rawQuery(consultaSql,null);
        while (datos1.moveToNext()){
            if (datos1.getString(0).equals("Activo")){
                String consultaSql1="SELECT "+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_CODIGO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_FICHA+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_TIPO_DOCUMENTO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_IDENTIFICACION+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_NOMBRE+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_APELLIDO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_FECHA_NACIMIENTO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_EPS+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_EDAD+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_GENERO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_USUARIO+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_CREATED_AT+","+
                        Utilidades.TABLA_PERSONAS+"."+Utilidades.PERSONAS_UPDATED_AT+
                        " FROM "+Utilidades.TABLA_PERSONAS+
                        " WHERE "+Utilidades.PERSONAS_FICHA+" = '"+datos1.getString(8)+"'";
                Cursor personasDb=db.rawQuery(consultaSql1,null);
                while (personasDb.moveToNext()){
                    String consultaSql2="SELECT "+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_ID+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_PERSONA+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_USUARIO+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_FORMATO+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_ESTADO+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_CREATED_AT+","+
                            Utilidades.TABLA_HOJA_TRABAJO+"."+Utilidades.HOJA_TRABAJO_UPDATED_AT+
                            " WHERE "+Utilidades.HOJA_TRABAJO_ESTADO+"='Activo'";
                }
            }
        }
    }
}
