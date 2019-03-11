package com.constructor.sidisas.constructor;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.constructor.sidisas.constructor.Models.Bloque;
import com.constructor.sidisas.constructor.Models.CoreConstructor;
import com.constructor.sidisas.constructor.Models.CoreGuardado;
import com.constructor.sidisas.constructor.Models.HojaTrabajo;
import com.constructor.sidisas.constructor.Models.Respuesta;
import com.constructor.sidisas.constructor.Models.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;

public class TestFindriskActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout contenedorTestFindrisk;
    ArrayList<CoreGuardado> paraPostFormato;
    String persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_findrisk);
        contenedorTestFindrisk = (LinearLayout) findViewById(R.id.contenedorTestFindrisk);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"newaps",null,1);
        String[] formatoId={"6"};
        ArrayList<Bloque> bloquesFormato=null;
        CoreConstructor constructorFormato=new CoreConstructor();
        bloquesFormato=constructorFormato.getMainCore(formatoId,conn.getWritableDatabase());
        conn.close();

        paraPostFormato = new ArrayList<CoreGuardado>();
        for (int i=0; i<bloquesFormato.size();i++){
            /*
            SECCION DEL BLOQUE
            Se crea un textView de nombre bloque.
            Este tendra el nombre de los bloques del formato.
            para agregar propiedades se utiliza bloque.metodoABuscar()
            para finalizar se adiciona el bloque al contenedor Linear
             */


            TextView bloque = new TextView(getApplicationContext());
            bloque.setText(bloquesFormato.get(i).getNombre());
            bloque.setTextSize(18);
            bloque.setTextColor(Color.parseColor("#2196f3"));
            bloque.setTypeface(Typeface.create("BOLD",Typeface.BOLD));
            LinearLayout.LayoutParams layoutParamBloque = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamBloque.setMargins(0,100,0,40);
            bloque.setLayoutParams(layoutParamBloque);
            contenedorTestFindrisk.addView(bloque);


            //Se recorren las preguntas del bloque
            for (int j=0; j<bloquesFormato.get(i).getPreguntas().size(); j++){
                CoreGuardado paraGuardar=new CoreGuardado();
                paraGuardar.setTipoPregunta(bloquesFormato.get(i).getPreguntas().get(j).getTipo());
                paraGuardar.setRespuestas(bloquesFormato.get(i).getPreguntas().get(j).getRespuestas());
                paraGuardar.setPregunta(bloquesFormato.get(i).getPreguntas().get(j).getNombre());
                if (bloquesFormato.get(i).getPreguntas().get(j).getValidacion().equals("obligatorio")){
                    paraGuardar.setRequerido(true);
                }else{
                    paraGuardar.setRequerido(false);
                }

                //CREACION DE PREGUNTAS TIPO SPINNER O SELECT
                if (bloquesFormato.get(i).getPreguntas().get(j).getTipo().equals("desplegable")){

                    //Se crea un textView de el nombre de la pregunta. esto equivale al <label> en html
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesFormato.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setTextSize(12);
                    LinearLayout.LayoutParams layoutParamPregunta = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParamPregunta.setMargins(0,30,0,15);
                    pregunta.setLayoutParams(layoutParamPregunta);
                    /*
                    Se crea un Spinner con nombre select
                    para acceder a los metodos para su personalizacion se llama select.metodoUsar();
                     */
                    Spinner select = new Spinner(this,Spinner.MODE_DIALOG);
                    select.setPadding(0,9,0,9);
                    ArrayList<String> respuestasSelect=new ArrayList<String>();
                    int idSelect=(int) (Math.random()*10000)+1;
                    select.setId(idSelect);
                    ArrayList<Integer> idArraySelect= new ArrayList<Integer>();
                    idArraySelect.add(select.getId());
                    paraGuardar.setIdElemento(idArraySelect);
                    respuestasSelect.add("Seleccione una respuesta");
                    for (int k=0; k<bloquesFormato.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        respuestasSelect.add(bloquesFormato.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                    }
                    ArrayAdapter<CharSequence> adaptadorPer=new ArrayAdapter(this,android.R.layout.simple_spinner_item,respuestasSelect);
                    select.setAdapter(adaptadorPer);

                    /*
                    Se agregan los metodos al contenedor.
                    Primero se agrega el TextView y luego el Spinner
                     */
                    contenedorTestFindrisk.addView(pregunta);
                    contenedorTestFindrisk.addView(select);
                }

                //CREACION DE PREGUNTAS DE TIPO RESPUESTA CORTA O PARRAFO
                if(bloquesFormato.get(i).getPreguntas().get(j).getTipo().equals("respuesta corta") ||
                        bloquesFormato.get(i).getPreguntas().get(j).getTipo().equals("parrafo")){

                    //Se crea un TextInputLayout de el nombre de la pregunta. esto equivale al <label> en html
                    TextInputLayout pregunta = new TextInputLayout(this);
                    pregunta.setHint(bloquesFormato.get(i).getPreguntas().get(j).getNombre());
                    LinearLayout.LayoutParams layoutParamPregunta = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParamPregunta.setMargins(0,30,0,15);
                    pregunta.setLayoutParams(layoutParamPregunta);
                    /*
                    Se crea un TextInputEditText con nombre texto
                    para acceder a los metodos para su personalizacion se llama texto.metodoUsar();
                     */
                    TextInputEditText textoEdit = new TextInputEditText(this);
                    int idEditText=(int) (Math.random()*10000)+1;
                    textoEdit.setId(idEditText);
                    ArrayList<Integer> idArrayText= new ArrayList<Integer>();
                    idArrayText.add(textoEdit.getId());
                    paraGuardar.setIdElemento(idArrayText);
                    // Se agrega el campo de texto a la vista de TextInputLayout
                    pregunta.addView(textoEdit);
                    // se agrega al contenedor general
                    contenedorTestFindrisk.addView(pregunta);
                }
                if (bloquesFormato.get(i).getPreguntas().get(j).getTipo().equals("multiple seleccion")){

                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesFormato.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setBackgroundColor(Color.WHITE);
                    pregunta.setTextColor(Color.BLACK);
                    pregunta.setHeight(50);
                    pregunta.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    contenedorTestFindrisk.addView(pregunta);

                    ArrayList<Integer> listaIdCheck=new ArrayList<Integer>();
                    for (int k=0; k<bloquesFormato.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        CheckBox check= new CheckBox(getApplicationContext());
                        int idCheck=(int) (Math.random()*10000)+1;
                        check.setId(idCheck);
                        listaIdCheck.add(check.getId());
                        check.setText(bloquesFormato.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                        check.setTextColor(Color.BLACK);
                        contenedorTestFindrisk.addView(check);
                    }
                    paraGuardar.setIdElemento(listaIdCheck);
                }
                if (bloquesFormato.get(i).getPreguntas().get(j).getTipo().equals("fecha")){
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesFormato.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setBackgroundColor(Color.WHITE);
                    pregunta.setTextColor(Color.BLACK);
                    pregunta.setHeight(50);
                    pregunta.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    final EditText fecha= new EditText(getApplicationContext());
                    fecha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    fecha.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                    int idEditText=(int) (Math.random()*10000)+1;
                    fecha.setId(idEditText);
                    fecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean b) {
                            Log.i("Evento","Click en fecha");
                            if (v.getId()==fecha.getId()){
                                if (b){
                                    DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker datePicker, int year, int mont, int dayMonth) {
                                            int s = mont + 1;
                                            String a = dayMonth + "-" + s + "-" + year;
                                            fecha.setText(a);
                                        }
                                    };
                                    final Calendar c = Calendar.getInstance();
                                    DatePickerDialog d = new DatePickerDialog(TestFindriskActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                                    d.show();
                                }
                            }
                        }
                    });
                    ArrayList<Integer> idArrayText= new ArrayList<Integer>();
                    idArrayText.add(fecha.getId());
                    paraGuardar.setIdElemento(idArrayText);

                    contenedorTestFindrisk.addView(pregunta);
                    contenedorTestFindrisk.addView(fecha);
                }

                paraPostFormato.add(paraGuardar);
            }
        }
    }

    public void limpiarParaPost(){
        for (int i=0; i<paraPostFormato.size();i++){
            paraPostFormato.get(i).setIdRespuesta(new ArrayList<Respuesta>());
            paraPostFormato.get(i).setDescripcion("");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonEnviarDatosAiepi:

                for (int i=0; i<paraPostFormato.size();i++){
                    if (paraPostFormato.get(i).getTipoPregunta().equals("desplegable")){
                        Spinner spinner= (Spinner) findViewById(paraPostFormato.get(i).getIdElemento().get(0));
                        if (paraPostFormato.get(i).getRequerido()){
                            if (spinner.getSelectedItemPosition()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una respuesta a la pregunta:"+paraPostFormato.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostFormato.get(i).getRespuestas().get((spinner.getSelectedItemPosition()-1)));
                        paraPostFormato.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPostFormato.get(i).getTipoPregunta().equals("respuesta corta") ||
                            paraPostFormato.get(i).getTipoPregunta().equals("parrafo")){
                        TextInputEditText text= (TextInputEditText) findViewById(paraPostFormato.get(i).getIdElemento().get(0));
                        if (paraPostFormato.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe digitar una respuesta a la pregunta:"+paraPostFormato.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostFormato.get(i).getRespuestas().get(0));
                        paraPostFormato.get(i).setIdRespuesta(arrayRespuesta);
                        paraPostFormato.get(i).setDescripcion(text.getText().toString());
                    }
                    if (paraPostFormato.get(i).getTipoPregunta().equals("multiple seleccion")){
                        int cuantosCheckeados=0;
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        for (int j=0; j<paraPostFormato.get(i).getIdElemento().size(); j++){
                            CheckBox check= (CheckBox) findViewById(paraPostFormato.get(i).getIdElemento().get(j));
                            if (check.isChecked()){
                                arrayRespuesta.add(paraPostFormato.get(i).getRespuestas().get(j));
                                cuantosCheckeados++;
                            }
                        }
                        if (paraPostFormato.get(i).getRequerido()){
                            if (cuantosCheckeados==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar al menos 1 respuesta a la pregunta:"+paraPostFormato.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        paraPostFormato.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPostFormato.get(i).getTipoPregunta().equals("fecha")){
                        EditText text= (EditText) findViewById(paraPostFormato.get(i).getIdElemento().get(0));
                        if (paraPostFormato.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una fecha a la pregunta:"+paraPostFormato.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostFormato.get(i).getRespuestas().get(0));
                        paraPostFormato.get(i).setIdRespuesta(arrayRespuesta);
                        paraPostFormato.get(i).setDescripcion(text.getText().toString());
                    }
                    ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
                    SQLiteDatabase db= con.getWritableDatabase();
                    int formatoRepetido3=0;
                    int formatoRepetido4=0;
                    int formatoRepetido5=0;
                    int formatoRepetido6=0;
                    int formatoRepetido8=0;
                    int formatoRepetido9=0;
                    int formatoRepetido10=0;
                    for (int u=0; i<paraPostFormato.size(); u++){
                        for (int j=0; j<paraPostFormato.get(u).getIdRespuesta().size(); j++){
                            ContentValues respuestaPersona=new ContentValues();
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_ID,Utilidades.generarId("RF"));
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_PERSONA,persona);
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_RESPUESTA,paraPostFormato.get(u).getIdRespuesta().get(j).getId());
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_DESCRIPCION,paraPostFormato.get(u).getDescripcion());
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_USUARIO,"USJO7882151612200659377200");
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_CREATED_AT,Utilidades.createdAt());
                            respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_UPDATED_AT,Utilidades.createdAt());
                            db.insert(Utilidades.TABLA_RESPUESTAS_PERSONA,Utilidades.RESPUESTAS_PERSONA_ID,respuestaPersona);
                            //Log.i("idRespuesta",paraPostPersona.get(i).getIdRespuesta().get(j).getId()+" descripcion:"+paraPostPersona.get(i).getDescripcion()+" Redirige a:"+paraPostPersona.get(i).getIdRespuesta().get(j).getFormatoRedireccion());
                            switch (paraPostFormato.get(u).getIdRespuesta().get(j).getFormatoRedireccion()){
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
                    con.close();
                    HojaTrabajo hojaTrabajo=new HojaTrabajo();
                    hojaTrabajo.guardarTarea(getApplicationContext(),persona,"7");
                    if (formatoRepetido3>=1)hojaTrabajo.guardarTarea(this,persona,"3");
                    if (formatoRepetido4>=1)hojaTrabajo.guardarTarea(this,persona,"4");
                    if (formatoRepetido5>=1)hojaTrabajo.guardarTarea(this,persona,"5");
                    if (formatoRepetido6>=1)hojaTrabajo.guardarTarea(this,persona,"6");
                    if (formatoRepetido8>=1)hojaTrabajo.guardarTarea(this,persona,"8");
                    if (formatoRepetido9>=1)hojaTrabajo.guardarTarea(this,persona,"9");
                    if (formatoRepetido10>=1)hojaTrabajo.guardarTarea(this,persona,"10");
                }
                break;
        }
    }
}
