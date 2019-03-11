package com.constructor.sidisas.constructor;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.constructor.sidisas.constructor.Models.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EstructuraFormulariosActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageViewEstructuraFormularios;
    private ProgressBar progressBarEstructuraFormularios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estructura_formularios);
        imageViewEstructuraFormularios = (ImageView) findViewById(R.id.imageViewEstructuraFormularios);
        progressBarEstructuraFormularios = findViewById(R.id.progressBarEstructuraFormularios);
        imageViewEstructuraFormularios.setOnClickListener(this);
        //ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewEstructuraFormularios:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Create URL
                        URL apiEndPoint = null;
                        String result=null;
                        try {
                            apiEndPoint = new URL("http://192.168.0.21:8000/datos-android");
                            // Create connection
                            HttpURLConnection myConnection =
                                    (HttpURLConnection) apiEndPoint.openConnection();
                            if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                                Log.i("conexion" ,"Ok");
                                InputStream in= new BufferedInputStream(myConnection.getInputStream());
                                BufferedReader reader= new BufferedReader(new InputStreamReader(in));
                                result=reader.readLine();
                                JSONObject json = new JSONObject(result.toString());
                                guardarDatosSincronizar(json);
                                myConnection.disconnect();
                            }else{
                                Log.i("conexion" ,"no conectado");
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Log.e("ErrorMalformed","Malformed");
                        } catch (Exception e){
                            e.printStackTrace();
                            Log.e("Error","Exception");
                        }

                    }
                });
                break;
        }
    }

    public void guardarDatosSincronizar(JSONObject data){
        try{
            JSONArray jsonBloques= data.getJSONArray("bloques");
            JSONArray jsonPreguntas= data.getJSONArray("preguntas");
            JSONArray jsonRespuestas= data.getJSONArray("respuestas");
            JSONArray jsonUsuarios= data.getJSONArray("usuarios");
            JSONArray jsonPermisosUsuario= data.getJSONArray("permisos_usuario");
            JSONArray jsonDepartamentos= data.getJSONArray("departamentos");
            JSONArray jsonProvincias= data.getJSONArray("provincias");
            JSONArray jsonMunicipios= data.getJSONArray("municipios");
            JSONArray jsonBarrios= data.getJSONArray("barrios");
            JSONArray jsonCie10= data.getJSONArray("cie10");
            JSONArray jsonEps= data.getJSONArray("eps");
            ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
            int registrosCompletados= 0;
            int totalRegistros = jsonBloques.length() + jsonPreguntas.length() +
            jsonRespuestas.length() +
            jsonUsuarios.length() +
            jsonPermisosUsuario.length() +
            jsonDepartamentos.length() +
            jsonProvincias.length() +
            jsonMunicipios.length() +
            jsonBarrios.length() +
                    jsonCie10.length() +
            jsonEps.length();
            //con.poblarDataBase();
            SQLiteDatabase db=con.getWritableDatabase();
            for (int i=0; i<jsonUsuarios.length();i++){
                JSONObject convertido=jsonUsuarios.getJSONObject(i);
                //Log.i("Usuario",convertido.toString());
                ContentValues values=new ContentValues();
                values.put(Utilidades.USUARIO_ID,convertido.getString(Utilidades.USUARIO_ID));
                values.put(Utilidades.USUARIO_NOMBRE,convertido.getString(Utilidades.USUARIO_NOMBRE));
                values.put(Utilidades.USUARIO_APELLIDO,convertido.getString(Utilidades.USUARIO_APELLIDO));
                values.put(Utilidades.USUARIO_IDENTIFICACION,convertido.getString(Utilidades.USUARIO_IDENTIFICACION));
                values.put(Utilidades.USUARIO_TELEFONO,convertido.getString(Utilidades.USUARIO_TELEFONO));
                values.put(Utilidades.USUARIO_EMAIL,convertido.getString(Utilidades.USUARIO_EMAIL));
                values.put(Utilidades.USUARIO_ROL,convertido.getString(Utilidades.USUARIO_ROL));
                values.put(Utilidades.USUARIO_FIRMA,convertido.getString(Utilidades.USUARIO_FIRMA));
                values.put(Utilidades.USUARIO_FOTO,convertido.getString(Utilidades.USUARIO_FOTO));
                values.put(Utilidades.USUARIO_PASSWORD,convertido.getString(Utilidades.USUARIO_PASSWORD));
                values.put(Utilidades.USUARIO_ESTADO,convertido.getString(Utilidades.USUARIO_ESTADO));
                values.put(Utilidades.USUARIO_REMEMBER_TOKEN,convertido.getString(Utilidades.USUARIO_REMEMBER_TOKEN));
                values.put(Utilidades.USUARIO_CREATED_AT,convertido.getString(Utilidades.USUARIO_CREATED_AT));
                values.put(Utilidades.USUARIO_UPDATED_AT,convertido.getString(Utilidades.USUARIO_UPDATED_AT));
                db.insert(Utilidades.TABLA_USUARIO,Utilidades.USUARIO_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonPermisosUsuario.length();i++){
                JSONObject convertido=jsonPermisosUsuario.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Permisos",convertido.toString());
                values.put(Utilidades.PERMISOS_USUARIO_ID,convertido.getString(Utilidades.PERMISOS_USUARIO_ID));
                values.put(Utilidades.PERMISOS_USUARIO_PERMISO,convertido.getString(Utilidades.PERMISOS_USUARIO_PERMISO));
                values.put(Utilidades.PERMISOS_USUARIO_USUARIO,convertido.getString(Utilidades.PERMISOS_USUARIO_USUARIO));
                values.put(Utilidades.PERMISOS_USUARIO_CREATED_AT,convertido.getString(Utilidades.PERMISOS_USUARIO_CREATED_AT));
                values.put(Utilidades.PERMISOS_USUARIO_UPDATED_AT,convertido.getString(Utilidades.PERMISOS_USUARIO_UPDATED_AT));
                db.insert(Utilidades.TABLA_PERMISOS_USUARIO,Utilidades.PERMISOS_USUARIO_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonDepartamentos.length();i++){
                JSONObject convertido=jsonDepartamentos.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Departamento",convertido.toString());
                values.put(Utilidades.DEPARTAMENTO_ID,convertido.getString(Utilidades.DEPARTAMENTO_ID));
                values.put(Utilidades.DEPARTAMENTO_CODIGO,convertido.getString(Utilidades.DEPARTAMENTO_CODIGO));
                values.put(Utilidades.DEPARTAMENTO_NOMBRE,convertido.getString(Utilidades.DEPARTAMENTO_NOMBRE));
                db.insert(Utilidades.TABLA_DEPARTAMENTO,Utilidades.DEPARTAMENTO_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonProvincias.length();i++){
                JSONObject convertido=jsonProvincias.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Provincias",convertido.toString());
                values.put(Utilidades.PROVINCIAS_ID,convertido.getString(Utilidades.PROVINCIAS_ID));
                values.put(Utilidades.PROVINCIAS_NOMBRE,convertido.getString(Utilidades.PROVINCIAS_NOMBRE));
                values.put(Utilidades.PROVINCIAS_USUARIO,"");
                values.put(Utilidades.PROVINCIAS_CREATED_AT,convertido.getString(Utilidades.PROVINCIAS_CREATED_AT));
                db.insert(Utilidades.TABLA_PROVINCIAS,Utilidades.PROVINCIAS_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonMunicipios.length();i++){
                JSONObject convertido=jsonMunicipios.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Municipios",convertido.toString());
                values.put(Utilidades.MUNICIPIO_ID,convertido.getString(Utilidades.MUNICIPIO_ID));
                values.put(Utilidades.MUNICIPIO_CODIGO,convertido.getString(Utilidades.MUNICIPIO_CODIGO));
                values.put(Utilidades.MUNICIPIO_NOMBRE,convertido.getString(Utilidades.MUNICIPIO_NOMBRE));
                values.put(Utilidades.MUNICIPIO_DEPARTAMENTO,convertido.getString(Utilidades.MUNICIPIO_DEPARTAMENTO));
                values.put(Utilidades.MUNICIPIO_PROVINCIA,convertido.getString(Utilidades.MUNICIPIO_PROVINCIA));
                values.put(Utilidades.MUNICIPIO_USUARIO,"");
                values.put(Utilidades.MUNICIPIO_CREATED_AT,"");
                values.put(Utilidades.MUNICIPIO_UPDATED_AT,"");
                db.insert(Utilidades.TABLA_MUNICIPIO,Utilidades.MUNICIPIO_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonBarrios.length();i++){
                JSONObject convertido=jsonBarrios.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Barrios",convertido.toString());
                values.put(Utilidades.BARRIOS_ID,convertido.getString(Utilidades.BARRIOS_ID));
                values.put(Utilidades.BARRIOS_NOMBRE,convertido.getString(Utilidades.BARRIOS_NOMBRE));
                values.put(Utilidades.BARRIOS_MUNICIPIO,convertido.getString(Utilidades.BARRIOS_MUNICIPIO));
                values.put(Utilidades.BARRIOS_TIPO,convertido.getString(Utilidades.BARRIOS_TIPO));
                values.put(Utilidades.BARRIOS_COMUNA,convertido.getString(Utilidades.BARRIOS_COMUNA));
                values.put(Utilidades.BARRIOS_COORDENADAS,convertido.getString(Utilidades.BARRIOS_COORDENADAS));
                values.put(Utilidades.BARRIOS_USUARIO,"");
                values.put(Utilidades.BARRIOS_CREATED_AT,"");
                values.put(Utilidades.BARRIOS_UPDATED_AT,"");
                db.insert(Utilidades.TABLA_BARRIOS,Utilidades.BARRIOS_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonCie10.length();i++){
                JSONObject convertido=jsonCie10.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Cie10",convertido.toString());
                values.put(Utilidades.CIE10_ID,convertido.getString(Utilidades.CIE10_ID));
                values.put(Utilidades.CIE10_CODIGO,convertido.getString(Utilidades.CIE10_CODIGO));
                values.put(Utilidades.CIE10_DESCRIPCION,convertido.getString(Utilidades.CIE10_DESCRIPCION));
                db.insert(Utilidades.TABLA_CIE10,Utilidades.CIE10_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0; i<jsonEps.length();i++){
                JSONObject convertido=jsonEps.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Eps",convertido.toString());
                values.put(Utilidades.EPS_ID,convertido.getString(Utilidades.EPS_ID));
                values.put(Utilidades.EPS_NOMBRE,convertido.getString(Utilidades.EPS_NOMBRE));
                values.put(Utilidades.EPS_USUARIO,"");
                values.put(Utilidades.EPS_CREATED_AT,"");
                values.put(Utilidades.EPS_UPDATED_AT,"");
                db.insert(Utilidades.TABLA_EPS,Utilidades.EPS_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0;i<jsonBloques.length();i++){
                JSONObject convertido=jsonBloques.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Bloque",convertido.toString());
                values.put(Utilidades.BLOQUE_ID,convertido.getString(Utilidades.BLOQUE_ID));
                values.put(Utilidades.BLOQUE_NOMBRE,convertido.getString(Utilidades.BLOQUE_NOMBRE));
                values.put(Utilidades.BLOQUE_FORMATO,convertido.getString(Utilidades.BLOQUE_FORMATO));
                values.put(Utilidades.BLOQUE_ESTADO,convertido.getString(Utilidades.BLOQUE_ESTADO));
                values.put(Utilidades.BLOQUE_ORDEN,convertido.getString(Utilidades.BLOQUE_ORDEN));
                values.put(Utilidades.BLOQUE_CREATED_AT,convertido.getString(Utilidades.BLOQUE_CREATED_AT));
                db.insert(Utilidades.TABLA_BLOQUE,Utilidades.BLOQUE_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;

            }
            for (int i=0;i<jsonPreguntas.length();i++){
                JSONObject convertido=jsonPreguntas.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Preguntas",convertido.toString());
                values.put(Utilidades.PREGUNTA_ID,convertido.getString(Utilidades.PREGUNTA_ID));
                values.put(Utilidades.PREGUNTA_NOMBRE,convertido.getString(Utilidades.PREGUNTA_NOMBRE));
                values.put(Utilidades.PREGUNTA_TIPO,convertido.getString(Utilidades.PREGUNTA_TIPO));
                values.put(Utilidades.PREGUNTA_VALID,convertido.getString(Utilidades.PREGUNTA_VALID));
                values.put(Utilidades.PREGUNTA_BLOQUE,convertido.getString(Utilidades.PREGUNTA_BLOQUE));
                values.put(Utilidades.PREGUNTA_LONGITUD,convertido.getString(Utilidades.PREGUNTA_LONGITUD));
                values.put(Utilidades.PREGUNTA_ORDEN,convertido.getString(Utilidades.PREGUNTA_ORDEN));
                values.put(Utilidades.PREGUNTA_USUARIO,convertido.getString(Utilidades.PREGUNTA_USUARIO));
                values.put(Utilidades.PREGUNTA_ESTADO,convertido.getString(Utilidades.PREGUNTA_ESTADO));
                values.put(Utilidades.PREGUNTA_CREATED_AT,convertido.getString(Utilidades.PREGUNTA_CREATED_AT));
                db.insert(Utilidades.TABLA_PREGUNTA,Utilidades.PREGUNTA_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            for (int i=0;i<jsonRespuestas.length();i++){
                JSONObject convertido=jsonRespuestas.getJSONObject(i);
                ContentValues values=new ContentValues();
                //Log.i("Respuestas",convertido.toString());
                values.put(Utilidades.RESPUESTA_ID,convertido.getString(Utilidades.RESPUESTA_ID));
                values.put(Utilidades.RESPUESTA_PREGUNTA,convertido.getString(Utilidades.RESPUESTA_PREGUNTA));
                values.put(Utilidades.RESPUESTA_NOMBRE,convertido.getString(Utilidades.RESPUESTA_NOMBRE));
                values.put(Utilidades.RESPUESTA_FORMATO_REDIRECCION,"");
                values.put(Utilidades.RESPUESTA_ESTADO,convertido.getString(Utilidades.RESPUESTA_ESTADO));
                values.put(Utilidades.RESPUESTA_CREATED_AT,convertido.getString(Utilidades.RESPUESTA_CREATED_AT));
                db.insert(Utilidades.TABLA_RESPUESTAS,Utilidades.RESPUESTA_ID,values);
                progressBarEstructuraFormularios.setProgress((int) ((registrosCompletados*100)/totalRegistros));
                registrosCompletados++;
            }
            Log.i("Sincronizacion ","Sinc Terminada");
        }catch (JSONException e){
            Log.e("ErrorJson",e.getMessage());
            Log.e("ErrorJson",e.getLocalizedMessage());
            Log.e("ErrorJson",e.getStackTrace().toString());
        }
    }
}
