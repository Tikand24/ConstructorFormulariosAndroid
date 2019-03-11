package com.constructor.sidisas.constructor.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.constructor.sidisas.constructor.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by PC-1 on 21/2/2018.
 */

public class CoreConstructor {

    public CoreConstructor(){

    }
    public ArrayList<Bloque> getMainCore(String[] formato,SQLiteDatabase db){
        ArrayList<Bloque> bloques=new ArrayList<Bloque>();
        try {
            Cursor bloquesDb=db.query(Utilidades.TABLA_BLOQUE,null,Utilidades.BLOQUE_FORMATO+"=?",formato,null,null,Utilidades.BLOQUE_ORDEN+" ASC");
            while (bloquesDb.moveToNext()){
                Bloque bloque = new Bloque();
                bloque.setId(bloquesDb.getString(0));
                bloque.setNombre(bloquesDb.getString(1));
                bloque.setOrden(bloquesDb.getString(3));
                bloque.setEstado(bloquesDb.getString(4));
                bloques.add(bloque);
            }
            for (int i=0; i < bloques.size();i++){
                ArrayList<Pregunta> preguntas=new ArrayList<Pregunta>();
                String[] bloqueId={bloques.get(i).getId()};
                Cursor preguntasDb=db.query(Utilidades.TABLA_PREGUNTA,null,Utilidades.PREGUNTA_BLOQUE+"=?",bloqueId,null,null,Utilidades.PREGUNTA_ORDEN+" ASC");
                while (preguntasDb.moveToNext()){
                    Pregunta pregunta= new Pregunta();
                    pregunta.setId(preguntasDb.getString(0));
                    pregunta.setNombre(preguntasDb.getString(1));
                    pregunta.setTipo(preguntasDb.getString(2));
                    pregunta.setValidacion(preguntasDb.getString(3));
                    pregunta.setLongitud(preguntasDb.getString(5));
                    pregunta.setOrden(preguntasDb.getString(6));
                    pregunta.setEstado(preguntasDb.getString(7));
                    preguntas.add(pregunta);
                }
                bloques.get(i).setPreguntas(preguntas);
            }

            for (int i = 0; i < bloques.size(); i++){
                for (int j = 0; j < bloques.get(i).getPreguntas().size(); j++){
                    ArrayList<Respuesta> respuestas=new ArrayList<Respuesta>();
                    String[] preguntaId={bloques.get(i).getPreguntas().get(j).getId()};
                    Cursor respuestadDb=db.query(Utilidades.TABLA_RESPUESTAS,null,Utilidades.RESPUESTA_PREGUNTA+"=?",preguntaId,null,null,null);
                    while (respuestadDb.moveToNext()){
                        Respuesta respuesta=new Respuesta();
                        respuesta.setId(respuestadDb.getString(0));
                        respuesta.setNombre(respuestadDb.getString(1));
                        respuesta.setFormatoRedireccion(respuestadDb.getString(3));
                        respuesta.setEstado(respuestadDb.getString(4));
                        respuestas.add(respuesta);
                    }
                    bloques.get(i).getPreguntas().get(j).setRespuestas(respuestas);
                }
            }
        }catch (Exception e){
            Log.e("Error Bloque",e.getMessage());
        }
        return bloques;
    }

}
