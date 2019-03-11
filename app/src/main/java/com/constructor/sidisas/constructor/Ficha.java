package com.constructor.sidisas.constructor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Ficha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void funciones(){
    /*
        LinearLayout contenedor;
        ArrayList<CoreGuardado> paraPost;
        Button buttonEnviarDatos,buttonSincronizar,btnGps,btnNuevaPersona,btnPendientes;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tablero);
            buttonEnviarDatos= (Button) findViewById(R.id.buttonEnviarDatos);
            buttonSincronizar=(Button) findViewById(R.id.sincronizar);
            btnGps=(Button) findViewById(R.id.btnGps);
            btnNuevaPersona = (Button) findViewById(R.id.btnPersona);
            btnPendientes = (Button) findViewById(R.id.btnPendientes);
            buttonEnviarDatos.setOnClickListener(this);
            buttonSincronizar.setOnClickListener(this);
            btnPendientes.setOnClickListener(this);
            btnNuevaPersona.setOnClickListener(this);
            btnGps.setOnClickListener(this);
            ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
        }

        public void limpiarParaPost(){
            for (int i=0; i<paraPost.size(); i++){
                paraPost.get(i).setIdRespuesta(new ArrayList<Respuesta>());
                paraPost.get(i).setDescripcion("");
            }
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(this, "Click para enviar", Toast.LENGTH_LONG).show();
            Log.i("Click","evento");
            switch (v.getId()){
                case R.id.sincronizar:
                    Thread tr = new Thread(){
                        @Override
                        public void run() {
                            Log.i("Click","evento");
                            URL url=null;
                            String linea="";
                            int respuesta=0;
                            String result=null;
                            try {
                                url= new URL("http://192.168.0.3:8000/datos-android");
                                HttpURLConnection coneccion=(HttpURLConnection)url.openConnection();
                                respuesta=coneccion.getResponseCode();
                                if (respuesta==HttpURLConnection.HTTP_OK){
                                    InputStream in= new BufferedInputStream(coneccion.getInputStream());
                                    BufferedReader reader= new BufferedReader(new InputStreamReader(in));
                                    result=reader.readLine();
                                }
                                JSONObject json = new JSONObject(result.toString());
                                guardarDatosSincronizar(json);
                            }catch (Exception e){
                                Log.e("Error","Obtener json");
                                Log.e("error",e.getMessage());
                            }
                        }
                    };
                    tr.start();
                    break;
                case R.id.buttonEnviarDatos:
                    Log.i("evento","Redirigir");
                    Intent fichaHogar = new Intent(getApplicationContext(), FichaHogarActivity.class);
                    startActivity(fichaHogar);
                for (int i=0; i<paraPost.size();i++){
                    if (paraPost.get(i).getTipoPregunta().equals("desplegable")){
                        Spinner spinner= (Spinner) findViewById(paraPost.get(i).getIdElemento().get(0));
                        if (paraPost.get(i).getRequerido()){
                            if (spinner.getSelectedItemPosition()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una respuesta a la pregunta:"+paraPost.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPost.get(i).getRespuestas().get((spinner.getSelectedItemPosition()-1)));
                        paraPost.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPost.get(i).getTipoPregunta().equals("respuesta corta") ||
                            paraPost.get(i).getTipoPregunta().equals("parrafo")){
                        EditText text= (EditText) findViewById(paraPost.get(i).getIdElemento().get(0));
                        if (paraPost.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe digitar una respuesta a la pregunta:"+paraPost.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPost.get(i).getRespuestas().get(0));
                        paraPost.get(i).setIdRespuesta(arrayRespuesta);
                        paraPost.get(i).setDescripcion(text.getText().toString());
                    }
                    if (paraPost.get(i).getTipoPregunta().equals("multiple seleccion")){
                        int cuantosCheckeados=0;
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        for (int j=0; j<paraPost.get(i).getIdElemento().size(); j++){
                            CheckBox check= (CheckBox) findViewById(paraPost.get(i).getIdElemento().get(j));
                            if (check.isChecked()){
                                arrayRespuesta.add(paraPost.get(i).getRespuestas().get(j));
                                cuantosCheckeados++;
                            }
                        }
                        if (paraPost.get(i).getRequerido()){
                            if (cuantosCheckeados==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar al menos 1 respuesta a la pregunta:"+paraPost.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        paraPost.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPost.get(i).getTipoPregunta().equals("fecha")){
                        EditText text= (EditText) findViewById(paraPost.get(i).getIdElemento().get(0));
                        if (paraPost.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una fecha a la pregunta:"+paraPost.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPost.get(i).getRespuestas().get(0));
                        paraPost.get(i).setIdRespuesta(arrayRespuesta);
                        paraPost.get(i).setDescripcion(text.getText().toString());
                    }
                }

                Para el momento de guardar
                int formatoRepetido3=0;
                int formatoRepetido4=0;
                int formatoRepetido5=0;
                int formatoRepetido6=0;
                int formatoRepetido7=0;
                int formatoRepetido8=0;
                int formatoRepetido9=0;
                int formatoRepetido10=0;
                for (int i=0; i<paraPost.size(); i++){
                    for (int j=0; j<paraPost.get(i).getIdRespuesta().size(); j++){
                        Log.i("idRespuesta",paraPost.get(i).getIdRespuesta().get(j).getId()+" descripcion:"+paraPost.get(i).getDescripcion()+" Redirige a:"+paraPost.get(i).getIdRespuesta().get(j).getFormatoRedireccion());
                        switch (paraPost.get(i).getIdRespuesta().get(j).getFormatoRedireccion()){
                            case "3":
                                formatoRepetido3++;
                                break;
                            case "4":
                                formatoRepetido4++;
                                break;
                            case "5":
                                formatoRepetido5++;
                                break;
                            case "6":
                                formatoRepetido6++;
                                break;
                            case "7":
                                formatoRepetido7++;
                                break;
                            case "8":
                                formatoRepetido8++;
                                break;
                            case "9":
                                formatoRepetido9++;
                                break;
                            case "10":
                                formatoRepetido10++;
                                break;
                        }
                    }
                }
                    break;
                case R.id.btnGps:
                    Intent localizacion = new Intent(getApplicationContext(), LocalizacionActivity.class);
                    startActivity(localizacion);
                    break;
                case R.id.btnPersona:
                    Intent per = new Intent(getApplicationContext(), PersonaActivity.class);
                    per.putExtra("fichaHogar","FHJO1069757882790261521146450044");
                    startActivity(per);
                    break;
                case R.id.btnPendientes:
                    Intent pendientes = new Intent(getApplicationContext(), PendientesTareasActivity.class);
                    pendientes.putExtra("fichaHogar","FHJO1069757882562031521210729153");
                    startActivity(pendientes);
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
                con.poblarDataBase();
                SQLiteDatabase db=con.getWritableDatabase();
                for (int i=0; i<jsonUsuarios.length();i++){
                    JSONObject convertido=jsonUsuarios.getJSONObject(i);
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
                }
                for (int i=0; i<jsonPermisosUsuario.length();i++){
                    JSONObject convertido=jsonPermisosUsuario.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.PERMISOS_USUARIO_ID,convertido.getString(Utilidades.PERMISOS_USUARIO_ID));
                    values.put(Utilidades.PERMISOS_USUARIO_PERMISO,convertido.getString(Utilidades.PERMISOS_USUARIO_PERMISO));
                    values.put(Utilidades.PERMISOS_USUARIO_USUARIO,convertido.getString(Utilidades.PERMISOS_USUARIO_USUARIO));
                    values.put(Utilidades.PERMISOS_USUARIO_CREATED_AT,convertido.getString(Utilidades.PERMISOS_USUARIO_CREATED_AT));
                    values.put(Utilidades.PERMISOS_USUARIO_UPDATED_AT,convertido.getString(Utilidades.PERMISOS_USUARIO_UPDATED_AT));
                    db.insert(Utilidades.TABLA_PERMISOS_USUARIO,Utilidades.PERMISOS_USUARIO_ID,values);
                }
                for (int i=0; i<jsonDepartamentos.length();i++){
                    JSONObject convertido=jsonDepartamentos.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.DEPARTAMENTO_ID,convertido.getString(Utilidades.DEPARTAMENTO_ID));
                    values.put(Utilidades.DEPARTAMENTO_CODIGO,convertido.getString(Utilidades.DEPARTAMENTO_CODIGO));
                    values.put(Utilidades.DEPARTAMENTO_NOMBRE,convertido.getString(Utilidades.DEPARTAMENTO_NOMBRE));
                    db.insert(Utilidades.TABLA_DEPARTAMENTO,Utilidades.DEPARTAMENTO_ID,values);
                }
                for (int i=0; i<jsonProvincias.length();i++){
                    JSONObject convertido=jsonProvincias.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.PROVINCIAS_ID,convertido.getString(Utilidades.PROVINCIAS_ID));
                    values.put(Utilidades.PROVINCIAS_NOMBRE,convertido.getString(Utilidades.PROVINCIAS_NOMBRE));
                    values.put(Utilidades.PROVINCIAS_USUARIO,convertido.getString(Utilidades.PROVINCIAS_USUARIO));
                    values.put(Utilidades.PROVINCIAS_CREATED_AT,convertido.getString(Utilidades.PROVINCIAS_CREATED_AT));
                    db.insert(Utilidades.TABLA_PROVINCIAS,Utilidades.PROVINCIAS_ID,values);
                }
                for (int i=0; i<jsonMunicipios.length();i++){
                    JSONObject convertido=jsonMunicipios.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.MUNICIPIO_ID,convertido.getString(Utilidades.MUNICIPIO_ID));
                    values.put(Utilidades.MUNICIPIO_CODIGO,convertido.getString(Utilidades.MUNICIPIO_CODIGO));
                    values.put(Utilidades.MUNICIPIO_NOMBRE,convertido.getString(Utilidades.MUNICIPIO_NOMBRE));
                    values.put(Utilidades.MUNICIPIO_DEPARTAMENTO,convertido.getString(Utilidades.MUNICIPIO_DEPARTAMENTO));
                    values.put(Utilidades.MUNICIPIO_PROVINCIA,convertido.getString(Utilidades.MUNICIPIO_PROVINCIA));
                    values.put(Utilidades.MUNICIPIO_USUARIO,convertido.getString(Utilidades.MUNICIPIO_USUARIO));
                    values.put(Utilidades.MUNICIPIO_CREATED_AT,convertido.getString(Utilidades.MUNICIPIO_CREATED_AT));
                    values.put(Utilidades.MUNICIPIO_UPDATED_AT,convertido.getString(Utilidades.MUNICIPIO_UPDATED_AT));
                    db.insert(Utilidades.TABLA_MUNICIPIO,Utilidades.MUNICIPIO_ID,values);
                }
                for (int i=0; i<jsonBarrios.length();i++){
                    JSONObject convertido=jsonBarrios.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.BARRIOS_ID,convertido.getString(Utilidades.BARRIOS_ID));
                    values.put(Utilidades.BARRIOS_NOMBRE,convertido.getString(Utilidades.BARRIOS_NOMBRE));
                    values.put(Utilidades.BARRIOS_MUNICIPIO,convertido.getString(Utilidades.BARRIOS_MUNICIPIO));
                    values.put(Utilidades.BARRIOS_TIPO,convertido.getString(Utilidades.BARRIOS_TIPO));
                    values.put(Utilidades.BARRIOS_COMUNA,convertido.getString(Utilidades.BARRIOS_COMUNA));
                    values.put(Utilidades.BARRIOS_COORDENADAS,convertido.getString(Utilidades.BARRIOS_COORDENADAS));
                    values.put(Utilidades.BARRIOS_USUARIO,convertido.getString(Utilidades.BARRIOS_USUARIO));
                    values.put(Utilidades.BARRIOS_CREATED_AT,convertido.getString(Utilidades.BARRIOS_CREATED_AT));
                    values.put(Utilidades.BARRIOS_UPDATED_AT,convertido.getString(Utilidades.BARRIOS_UPDATED_AT));
                    db.insert(Utilidades.TABLA_BARRIOS,Utilidades.BARRIOS_ID,values);
                }
                for (int i=0; i<jsonCie10.length();i++){
                    JSONObject convertido=jsonCie10.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.CIE10_ID,convertido.getString(Utilidades.CIE10_ID));
                    values.put(Utilidades.CIE10_CODIGO,convertido.getString(Utilidades.CIE10_CODIGO));
                    values.put(Utilidades.CIE10_DESCRIPCION,convertido.getString(Utilidades.CIE10_DESCRIPCION));
                    db.insert(Utilidades.TABLA_CIE10,Utilidades.CIE10_ID,values);
                }
                for (int i=0; i<jsonEps.length();i++){
                    JSONObject convertido=jsonEps.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.EPS_ID,convertido.getString(Utilidades.EPS_ID));
                    values.put(Utilidades.EPS_NOMBRE,convertido.getString(Utilidades.EPS_NOMBRE));
                    values.put(Utilidades.EPS_USUARIO,convertido.getString(Utilidades.EPS_USUARIO));
                    values.put(Utilidades.EPS_CREATED_AT,convertido.getString(Utilidades.EPS_CREATED_AT));
                    values.put(Utilidades.EPS_UPDATED_AT,convertido.getString(Utilidades.EPS_UPDATED_AT));
                    db.insert(Utilidades.TABLA_EPS,Utilidades.EPS_ID,values);
                }
                for (int i=0;i<jsonBloques.length();i++){
                    JSONObject convertido=jsonBloques.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.BLOQUE_ID,convertido.getString(Utilidades.BLOQUE_ID));
                    values.put(Utilidades.BLOQUE_NOMBRE,convertido.getString(Utilidades.BLOQUE_NOMBRE));
                    values.put(Utilidades.BLOQUE_FORMATO,convertido.getString(Utilidades.BLOQUE_FORMATO));
                    values.put(Utilidades.BLOQUE_ESTADO,convertido.getString(Utilidades.BLOQUE_ESTADO));
                    values.put(Utilidades.BLOQUE_ORDEN,convertido.getString(Utilidades.BLOQUE_ORDEN));
                    values.put(Utilidades.BLOQUE_CREATED_AT,convertido.getString(Utilidades.BLOQUE_CREATED_AT));
                    try {
                        db.insert(Utilidades.TABLA_BLOQUE,Utilidades.BLOQUE_ID,values);
                    }catch (SQLiteException e){
                        Log.e("Error",e.getMessage());
                    }
                }
                for (int i=0;i<jsonPreguntas.length();i++){
                    JSONObject convertido=jsonPreguntas.getJSONObject(i);
                    ContentValues values=new ContentValues();
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
                }
                for (int i=0;i<jsonRespuestas.length();i++){
                    JSONObject convertido=jsonRespuestas.getJSONObject(i);
                    ContentValues values=new ContentValues();
                    values.put(Utilidades.RESPUESTA_ID,convertido.getString(Utilidades.RESPUESTA_ID));
                    values.put(Utilidades.RESPUESTA_PREGUNTA,convertido.getString(Utilidades.RESPUESTA_PREGUNTA));
                    values.put(Utilidades.RESPUESTA_NOMBRE,convertido.getString(Utilidades.RESPUESTA_NOMBRE));
                    values.put(Utilidades.RESPUESTA_FORMATO_REDIRECCION,convertido.getString(Utilidades.RESPUESTA_FORMATO_REDIRECCION));
                    values.put(Utilidades.RESPUESTA_ESTADO,convertido.getString(Utilidades.RESPUESTA_ESTADO));
                    values.put(Utilidades.RESPUESTA_CREATED_AT,convertido.getString(Utilidades.RESPUESTA_CREATED_AT));
                    db.insert(Utilidades.TABLA_RESPUESTAS,Utilidades.RESPUESTA_ID,values);
                }
                Log.i("Sincronizacion ","Sinc Terminada");
            }catch (JSONException e){
                Log.e("ErrorJson",e.getMessage());
            }
        }*/
    }
}
