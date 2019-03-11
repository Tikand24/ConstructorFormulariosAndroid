package com.constructor.sidisas.constructor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.constructor.sidisas.constructor.Models.Barrio;
import com.constructor.sidisas.constructor.Models.Bloque;
import com.constructor.sidisas.constructor.Models.CoreConstructor;
import com.constructor.sidisas.constructor.Models.CoreGuardado;
import com.constructor.sidisas.constructor.Models.Departamento;
import com.constructor.sidisas.constructor.Models.Eps;
import com.constructor.sidisas.constructor.Models.FichaHogar;
import com.constructor.sidisas.constructor.Models.Municipio;
import com.constructor.sidisas.constructor.Models.Persona;
import com.constructor.sidisas.constructor.Models.Respuesta;
import com.constructor.sidisas.constructor.Models.TipoDocumento;
import com.constructor.sidisas.constructor.Models.Utilidades;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FichaHogarActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout contenedor,contenedorPersonas;
    ArrayList<CoreGuardado> paraPost,paraPostPersona;
    Button buttonEnviarDatos;
    ImageButton btnFechaNacimiento;
    Spinner spinnerDepartamento,spinnerMunicipio,spinnerBarrio,spinnerTipoDoc,spinnerGenero;
    ArrayList<Municipio> municipios;
    ArrayList<Barrio> barrios;
    RadioButton radioRural,radioUrbano,clasfAlto,clasfMedio,clasfBajo;
    public FichaHogar fichaHogar= new FichaHogar();
    Persona persona = new Persona();
    TextInputEditText direccion,identificacion,nombres,apellidos;
    TextView fechaNacimiento,tvEdad;
    AutoCompleteTextView buscadorEps;
    String codHogarMun,codHogarBarrio;
    ArrayList<TipoDocumento> tiposDocumento;
    Localizacion local;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_hogar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        Contenedor Linear donde van a estar todos los elementos creados por codigo
         */

        contenedor = (LinearLayout) findViewById(R.id.contenedor);
        contenedorPersonas = (LinearLayout) findViewById(R.id.contenedorPersonas);
        local=new Localizacion(this,FichaHogarActivity.this);
        fichaHogar.setCoordenadas(local.getLongitude()+","+local.getLatitude());
        Log.i("Latitud",local.getLatitude());
        Log.i("Longitud",local.getLongitude());
        /*
        Declaracion del boton enviar y de la asignacion de su evento
         */
        radioRural = (RadioButton) findViewById(R.id.radioZonaRural);
        radioUrbano = (RadioButton) findViewById(R.id.radioZonaUrbana);
        clasfAlto = (RadioButton) findViewById(R.id.riesgoAlto);
        clasfMedio = (RadioButton) findViewById(R.id.riesgoMedio);
        clasfBajo = (RadioButton) findViewById(R.id.riesgoBajo);
        buttonEnviarDatos= (Button) findViewById(R.id.buttonEnviarDatos);
        btnFechaNacimiento= (ImageButton) findViewById(R.id.btnFechaNacimiento);
        spinnerDepartamento = (Spinner) findViewById(R.id.departamentoSpinner);
        spinnerBarrio = (Spinner) findViewById(R.id.spinnerBarrio);
        spinnerTipoDoc = (Spinner) findViewById(R.id.spinnerTipoDoc);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
        direccion = (TextInputEditText) findViewById(R.id.direccion);
        identificacion = (TextInputEditText) findViewById(R.id.txtDocIdentidad);
        nombres = (TextInputEditText) findViewById(R.id.txtNombre);
        apellidos = (TextInputEditText) findViewById(R.id.txtApellidos);
        fechaNacimiento = (TextView) findViewById(R.id.fechaNacimiento);
        buscadorEps = (AutoCompleteTextView) findViewById(R.id.buscadorEps);
        tvEdad= (TextView) findViewById(R.id.txtEdad);
        buttonEnviarDatos.setOnClickListener(this);
        btnFechaNacimiento.setOnClickListener(this);


        /*
        Apertura de la conexion a la base de datos, se puede llamar el metodo de poblarDataBase solo en fase de pruebas
         */
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
        //con.poblarDataBase();

        /*
        se declara un objeto de coreConstructor y se llamma al metodo getMainCore,
        este nos retorna la estructura para la creacion del formato
         */
        String[] formato={"1"};
        ArrayList<Bloque> bloques=null;
        CoreConstructor construct=new CoreConstructor();
        bloques=construct.getMainCore(formato,con.getWritableDatabase());
        con.close();

        paraPost = new ArrayList<CoreGuardado>();

        //Se recorren los bloques

        for (int i=0; i<bloques.size();i++){
            /*
            SECCION DEL BLOQUE
            Se crea un textView de nombre bloque.
            Este tendra el nombre de los bloques del formato.
            para agregar propiedades se utiliza bloque.metodoABuscar()
            para finalizar se adiciona el bloque al contenedor Linear
             */
            TextView bloque = new TextView(getApplicationContext());
            bloque.setText(bloques.get(i).getNombre());
            bloque.setTextSize(18);
            bloque.setTextColor(Color.parseColor("#2196f3"));
            bloque.setTypeface(Typeface.create("BOLD",Typeface.BOLD));
            LinearLayout.LayoutParams layoutParamBloque = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamBloque.setMargins(0,100,0,40);
            bloque.setLayoutParams(layoutParamBloque);
            contenedor.addView(bloque);



            //Se recorren las preguntas del bloque
            for (int j=0; j<bloques.get(i).getPreguntas().size(); j++){
                CoreGuardado paraGuardar=new CoreGuardado();
                paraGuardar.setTipoPregunta(bloques.get(i).getPreguntas().get(j).getTipo());
                paraGuardar.setRespuestas(bloques.get(i).getPreguntas().get(j).getRespuestas());
                paraGuardar.setPregunta(bloques.get(i).getPreguntas().get(j).getNombre());
                if (bloques.get(i).getPreguntas().get(j).getValidacion().equals("obligatorio")){
                    paraGuardar.setRequerido(true);
                }else{
                    paraGuardar.setRequerido(false);
                }

                //CREACION DE PREGUNTAS TIPO SPINNER O SELECT
                if (bloques.get(i).getPreguntas().get(j).getTipo().equals("desplegable")){

                    //Se crea un textView de el nombre de la pregunta. esto equivale al <label> en html
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloques.get(i).getPreguntas().get(j).getNombre());
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
                    for (int k=0; k<bloques.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        respuestasSelect.add(bloques.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                    }
                    ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,respuestasSelect);
                    select.setAdapter(adaptador);

                    /*
                    Se agregan los metodos al contenedor.
                    Primero se agrega el TextView y luego el Spinner
                     */
                    contenedor.addView(pregunta);
                    contenedor.addView(select);
                }

                //CREACION DE PREGUNTAS DE TIPO RESPUESTA CORTA O PARRAFO
                if(bloques.get(i).getPreguntas().get(j).getTipo().equals("respuesta corta") ||
                        bloques.get(i).getPreguntas().get(j).getTipo().equals("parrafo")){
                    //Se crea un TextInputLayout de el nombre de la pregunta. esto equivale al <label> en html
                    TextInputLayout pregunta = new TextInputLayout(this);
                    pregunta.setHint(bloques.get(i).getPreguntas().get(j).getNombre());
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
                    contenedor.addView(pregunta);
                }
                if (bloques.get(i).getPreguntas().get(j).getTipo().equals("multiple seleccion")){

                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloques.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setBackgroundColor(Color.WHITE);
                    pregunta.setTextColor(Color.BLACK);
                    pregunta.setHeight(50);
                    LinearLayout.LayoutParams layoutParamPregunta = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParamPregunta.setMargins(0,30,0,15);
                    pregunta.setLayoutParams(layoutParamPregunta);
                    contenedor.addView(pregunta);

                    ArrayList<Integer> listaIdCheck=new ArrayList<Integer>();
                    for (int k=0; k<bloques.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        CheckBox check= new CheckBox(getApplicationContext());
                        int idCheck=(int) (Math.random()*10000)+1;
                        check.setId(idCheck);
                        listaIdCheck.add(check.getId());
                        check.setText(bloques.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                        check.setTextColor(Color.BLACK);
                        contenedor.addView(check);
                    }
                    paraGuardar.setIdElemento(listaIdCheck);
                }
                if (bloques.get(i).getPreguntas().get(j).getTipo().equals("fecha")){
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloques.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setTextSize(12);
                    LinearLayout.LayoutParams layoutParamPregunta = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParamPregunta.setMargins(0,30,0,15);
                    pregunta.setLayoutParams(layoutParamPregunta);

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
                                    DatePickerDialog d = new DatePickerDialog(FichaHogarActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                                    d.show();
                                }
                            }
                        }
                    });
                    ArrayList<Integer> idArrayText= new ArrayList<Integer>();
                    idArrayText.add(fecha.getId());
                    paraGuardar.setIdElemento(idArrayText);

                    contenedor.addView(pregunta);
                    contenedor.addView(fecha);
                }
                paraPost.add(paraGuardar);
            }
        }

        final Departamento departamento=new Departamento();

        ArrayList<String>datosBuscadorDepartamentos= new ArrayList<>();
        datosBuscadorDepartamentos.add("Seleccione un departamento");
        final ArrayList<Departamento> departamentos = departamento.getDepartamentosDb(this);

        for (int p=0; p<departamentos.size(); p++){
            datosBuscadorDepartamentos.add(departamentos.get(p).getCodigo()+" - "+departamentos.get(p).getNombre());
        }

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosBuscadorDepartamentos);
        spinnerDepartamento.setAdapter(adaptador);
        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion!=0){
                    Log.i("eventoDep","Selecciono"+departamentos.get((posicion-1)).getId());
                    Toast.makeText(FichaHogarActivity.this,"Cargando municipios",Toast.LENGTH_LONG).show();
                    cargarMunicipios(departamentos.get((posicion-1)).getId(),departamentos);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("eventoDep","No Selecciono");
            }
        });





        /*
        PERSONAs
        Creacion de personas
        Constructor y datos estaticos
        */


        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"newaps",null,1);
        String[] formatoPersona={"2"};
        ArrayList<Bloque> bloquesPersona=null;
        CoreConstructor constructPersona=new CoreConstructor();
        bloquesPersona=constructPersona.getMainCore(formatoPersona,conn.getWritableDatabase());
        conn.close();

        paraPostPersona = new ArrayList<CoreGuardado>();

        for (int i=0; i<bloquesPersona.size();i++){
            /*
            SECCION DEL BLOQUE
            Se crea un textView de nombre bloque.
            Este tendra el nombre de los bloques del formato.
            para agregar propiedades se utiliza bloque.metodoABuscar()
            para finalizar se adiciona el bloque al contenedor Linear
             */
            TextView bloque = new TextView(getApplicationContext());
            bloque.setText(bloquesPersona.get(i).getNombre());
            bloque.setTextSize(18);
            bloque.setTextColor(Color.parseColor("#2196f3"));
            bloque.setTypeface(Typeface.create("BOLD",Typeface.BOLD));
            LinearLayout.LayoutParams layoutParamBloque = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamBloque.setMargins(0,100,0,40);
            bloque.setLayoutParams(layoutParamBloque);
            contenedorPersonas.addView(bloque);

            //Se recorren las preguntas del bloque
            for (int j=0; j<bloquesPersona.get(i).getPreguntas().size(); j++){
                CoreGuardado paraGuardar=new CoreGuardado();
                paraGuardar.setTipoPregunta(bloquesPersona.get(i).getPreguntas().get(j).getTipo());
                paraGuardar.setRespuestas(bloquesPersona.get(i).getPreguntas().get(j).getRespuestas());
                paraGuardar.setPregunta(bloquesPersona.get(i).getPreguntas().get(j).getNombre());
                if (bloquesPersona.get(i).getPreguntas().get(j).getValidacion().equals("obligatorio")){
                    paraGuardar.setRequerido(true);
                }else{
                    paraGuardar.setRequerido(false);
                }

                //CREACION DE PREGUNTAS TIPO SPINNER O SELECT
                if (bloquesPersona.get(i).getPreguntas().get(j).getTipo().equals("desplegable")){

                    //Se crea un textView de el nombre de la pregunta. esto equivale al <label> en html
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesPersona.get(i).getPreguntas().get(j).getNombre());
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
                    for (int k=0; k<bloquesPersona.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        respuestasSelect.add(bloquesPersona.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                    }
                    ArrayAdapter<CharSequence> adaptadorPer=new ArrayAdapter(this,android.R.layout.simple_spinner_item,respuestasSelect);
                    select.setAdapter(adaptadorPer);

                    /*
                    Se agregan los metodos al contenedor.
                    Primero se agrega el TextView y luego el Spinner
                     */
                    contenedorPersonas.addView(pregunta);
                    contenedorPersonas.addView(select);
                }

                //CREACION DE PREGUNTAS DE TIPO RESPUESTA CORTA O PARRAFO
                if(bloquesPersona.get(i).getPreguntas().get(j).getTipo().equals("respuesta corta") ||
                        bloquesPersona.get(i).getPreguntas().get(j).getTipo().equals("parrafo")){

                    //Se crea un TextInputLayout de el nombre de la pregunta. esto equivale al <label> en html
                    TextInputLayout pregunta = new TextInputLayout(this);
                    pregunta.setHint(bloquesPersona.get(i).getPreguntas().get(j).getNombre());
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
                    contenedorPersonas.addView(pregunta);
                }
                if (bloquesPersona.get(i).getPreguntas().get(j).getTipo().equals("multiple seleccion")){

                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesPersona.get(i).getPreguntas().get(j).getNombre());
                    pregunta.setBackgroundColor(Color.WHITE);
                    pregunta.setTextColor(Color.BLACK);
                    pregunta.setHeight(50);
                    pregunta.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    contenedorPersonas.addView(pregunta);

                    ArrayList<Integer> listaIdCheck=new ArrayList<Integer>();
                    for (int k=0; k<bloquesPersona.get(i).getPreguntas().get(j).getRespuestas().size();k++){
                        CheckBox check= new CheckBox(getApplicationContext());
                        int idCheck=(int) (Math.random()*10000)+1;
                        check.setId(idCheck);
                        listaIdCheck.add(check.getId());
                        check.setText(bloquesPersona.get(i).getPreguntas().get(j).getRespuestas().get(k).getNombre());
                        check.setTextColor(Color.BLACK);
                        contenedorPersonas.addView(check);
                    }
                    paraGuardar.setIdElemento(listaIdCheck);
                }
                if (bloquesPersona.get(i).getPreguntas().get(j).getTipo().equals("fecha")){
                    TextView pregunta = new TextView(getApplicationContext());
                    pregunta.setText(bloquesPersona.get(i).getPreguntas().get(j).getNombre());
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
                                    DatePickerDialog d = new DatePickerDialog(FichaHogarActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                                    d.show();
                                }
                            }
                        }
                    });
                    ArrayList<Integer> idArrayText= new ArrayList<Integer>();
                    idArrayText.add(fecha.getId());
                    paraGuardar.setIdElemento(idArrayText);

                    contenedorPersonas.addView(pregunta);
                    contenedorPersonas.addView(fecha);
                }

                paraPostPersona.add(paraGuardar);
            }
        }




        ArrayList<String>datosSpinnerTipoDoc= new ArrayList<>();
        datosSpinnerTipoDoc.add("Seleccione un tipo de documento");
        TipoDocumento tipoDoc=new TipoDocumento();
        tiposDocumento=tipoDoc.tipoDocumentoDb(this);
        for (int m=0; m<tiposDocumento.size();m++){
            datosSpinnerTipoDoc.add(tiposDocumento.get(m).getCodigo()+" - "+tiposDocumento.get(m).getDescripcion());
        }
        ArrayAdapter<CharSequence> adaptadorTipoDoc=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosSpinnerTipoDoc);
        spinnerTipoDoc.setAdapter(adaptadorTipoDoc);
        ArrayList<String> datosGeneroSpinner=new ArrayList<>();
        datosGeneroSpinner.add("Seleccione un genero");
        datosGeneroSpinner.add("Masculino");
        datosGeneroSpinner.add("Femenino");
        ArrayAdapter<CharSequence> adaptadorGenero=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosGeneroSpinner);
        spinnerGenero.setAdapter(adaptadorGenero);

        final Eps eps=new Eps();
        ArrayList<Eps> epsDb=eps.epsDb(this);
        ArrayList<String> datosAutoCompleteEps=new ArrayList<>();
        for(int q=0;q<epsDb.size();q++){
            datosAutoCompleteEps.add(epsDb.get(q).getDescripcion());
        }
        ArrayAdapter<CharSequence> adaptadorEps=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosAutoCompleteEps);
        buscadorEps.setAdapter(adaptadorEps);
        buscadorEps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int posicion, long l) {
                String[] nombreEps={adapterView.getItemAtPosition(posicion).toString()};
                persona.setEps(eps.buscarEpsPorNombre(FichaHogarActivity.this,nombreEps));
                Log.i("Eps","posicion:"+posicion+" "+adapterView.getItemAtPosition(posicion));
            }
        });
    }
    public void cargarMunicipios(String idDepartamento, ArrayList<Departamento> departamentos){
        Municipio municipio=new Municipio();
        municipios=municipio.getMunicipiosDb(this,idDepartamento);
        ArrayList<String>datosSpinnerMunicipio= new ArrayList<>();
        datosSpinnerMunicipio.add("Seleccione un municipio");
        for (int i=0;i<municipios.size();i++){
            datosSpinnerMunicipio.add(municipios.get(i).getCodigo()+" - "+municipios.get(i).getNombre());
        }
        spinnerMunicipio = (Spinner) findViewById(R.id.spinnerMunicipio);
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosSpinnerMunicipio);
        spinnerMunicipio.setAdapter(adaptador);
        Toast.makeText(FichaHogarActivity.this,"Municipios Cargados",Toast.LENGTH_LONG).show();
        spinnerMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion!=0){
                    Log.i("eventoMun","Selecciono"+municipios.get((posicion-1)).getId());
                    codHogarMun=municipios.get((posicion-1)).getCodigo();
                    Toast.makeText(FichaHogarActivity.this,"Cargando barrios",Toast.LENGTH_LONG).show();
                    cargarBarrios(municipios.get((posicion-1)).getId(),municipios);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(FichaHogarActivity.this,"Nada Seleccionado",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void cargarBarrios(String idMunicipio,ArrayList<Municipio> municipios){
        if (radioRural.isChecked()==false && radioUrbano.isChecked()==false){
            Toast.makeText(this,"Seleccion la zona de ubicacion",Toast.LENGTH_LONG).show();
            ArrayList<String>datoSpinnerBarrio= new ArrayList<>();
            datoSpinnerBarrio.add("Seleccione un barrio");
            ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datoSpinnerBarrio);
            spinnerBarrio.setAdapter(adaptador);
            ArrayList<String>datosSpinnerMunicipios= new ArrayList<>();
            datosSpinnerMunicipios.add("Seleccione un barrio");
            for(int i=0;i<municipios.size();i++){
                datosSpinnerMunicipios.add(municipios.get(i).getCodigo()+" - "+municipios.get(i).getNombre());
            }
            ArrayAdapter<CharSequence> adaptadorMunicipio=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datosSpinnerMunicipios);
            spinnerMunicipio.setAdapter(adaptadorMunicipio);
            return;
        }
        Barrio barrio = new Barrio();
        String zona="";
        if (radioRural.isChecked()){
            zona="Vereda";
        }
        if (radioUrbano.isChecked()){
            zona="Barrio";
        }
        barrios=barrio.getBarrioDb(this,zona,idMunicipio);
        fichaHogar.setMunicipio(idMunicipio);
        ArrayList<String>datoSpinnerBarrio= new ArrayList<>();
        datoSpinnerBarrio.add("Seleccione un barrio");
        for(int i=0; i<barrios.size();i++){
            datoSpinnerBarrio.add(barrios.get(i).getNombre());
        }
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datoSpinnerBarrio);
        spinnerBarrio.setAdapter(adaptador);
        Toast.makeText(FichaHogarActivity.this,"Barrios Cargados",Toast.LENGTH_LONG).show();
        spinnerBarrio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if (posicion!=0){
                    codHogarBarrio=barrios.get((posicion-1)).getId();
                    fichaHogar.setBarrio(barrios.get((posicion-1)).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void limpiarParaPost(){
        for (int i=0; i<paraPost.size(); i++){
            paraPost.get(i).setIdRespuesta(new ArrayList<Respuesta>());
            paraPost.get(i).setDescripcion("");
        }
        for (int i=0; i<paraPostPersona.size();i++){
            paraPostPersona.get(i).setIdRespuesta(new ArrayList<Respuesta>());
            paraPostPersona.get(i).setDescripcion("");
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonEnviarDatos:
                Log.i("Latitud",local.getLatitude());
                Log.i("Longitud",local.getLongitude());
                fichaHogar.setCoordenadas(local.getLongitude()+","+local.getLatitude());
                if (!(radioUrbano.isChecked() || radioRural.isChecked())){
                    Toast.makeText(this,"Seleccione la zona de residencia",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (fichaHogar.getMunicipio()==null){
                    Toast.makeText(this,"Seleccione un municipio",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (fichaHogar.getBarrio()==null){
                    Toast.makeText(this,"Seleccione un barrio",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (direccion.getText().toString().length()==0){
                    Toast.makeText(this,"Digite la direccion de residencia",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (!(clasfBajo.isChecked() || clasfMedio.isChecked() || clasfAlto.isChecked())){
                    Toast.makeText(this,"Seleccione la clasificacion de riesgo de la familia",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (spinnerTipoDoc.getSelectedItemPosition()==0){
                    Toast.makeText(this,"Seleccione un tipo de documento",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (identificacion.getText().equals("") || identificacion.getText().length()==0){
                    Toast.makeText(this,"Digite el numero de identificacion",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (nombres.getText().equals("") || nombres.getText().length()==0){
                    Toast.makeText(this,"Digite el nombre de la persona",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (apellidos.getText().equals("") || apellidos.getText().length()==0){
                    Toast.makeText(this,"Digite los apellidos de la persona",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (fechaNacimiento.getText().equals("") || fechaNacimiento.getText().length()==0){
                    Toast.makeText(this,"Seleccione la fecha de nacimiento de la persona",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (spinnerGenero.getSelectedItemPosition()==0){
                    Toast.makeText(this,"Seleccione el genero de la persona",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
                if (persona.getEps()==null){
                    Toast.makeText(this,"Seleccione una eps",Toast.LENGTH_LONG).show();
                    limpiarParaPost();
                    return;
                }
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
                        TextInputEditText text= (TextInputEditText) findViewById(paraPost.get(i).getIdElemento().get(0));
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


                for (int i=0; i<paraPostPersona.size();i++){
                    if (paraPostPersona.get(i).getTipoPregunta().equals("desplegable")){
                        Spinner spinner= (Spinner) findViewById(paraPostPersona.get(i).getIdElemento().get(0));
                        if (paraPostPersona.get(i).getRequerido()){
                            if (spinner.getSelectedItemPosition()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una respuesta a la pregunta:"+paraPostPersona.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostPersona.get(i).getRespuestas().get((spinner.getSelectedItemPosition()-1)));
                        paraPostPersona.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPostPersona.get(i).getTipoPregunta().equals("respuesta corta") ||
                            paraPostPersona.get(i).getTipoPregunta().equals("parrafo")){
                        TextInputEditText text= (TextInputEditText) findViewById(paraPostPersona.get(i).getIdElemento().get(0));
                        if (paraPostPersona.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe digitar una respuesta a la pregunta:"+paraPostPersona.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostPersona.get(i).getRespuestas().get(0));
                        paraPostPersona.get(i).setIdRespuesta(arrayRespuesta);
                        paraPostPersona.get(i).setDescripcion(text.getText().toString());
                    }
                    if (paraPostPersona.get(i).getTipoPregunta().equals("multiple seleccion")){
                        int cuantosCheckeados=0;
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        for (int j=0; j<paraPostPersona.get(i).getIdElemento().size(); j++){
                            CheckBox check= (CheckBox) findViewById(paraPostPersona.get(i).getIdElemento().get(j));
                            if (check.isChecked()){
                                arrayRespuesta.add(paraPostPersona.get(i).getRespuestas().get(j));
                                cuantosCheckeados++;
                            }
                        }
                        if (paraPostPersona.get(i).getRequerido()){
                            if (cuantosCheckeados==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar al menos 1 respuesta a la pregunta:"+paraPostPersona.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        paraPostPersona.get(i).setIdRespuesta(arrayRespuesta);
                    }
                    if (paraPostPersona.get(i).getTipoPregunta().equals("fecha")){
                        EditText text= (EditText) findViewById(paraPostPersona.get(i).getIdElemento().get(0));
                        if (paraPostPersona.get(i).getRequerido()){
                            if (text.getText().length()==0){
                                Toast.makeText(getApplicationContext(), "Debe seleccionar una fecha a la pregunta:"+paraPostPersona.get(i).getPregunta(), Toast.LENGTH_LONG).show();
                                limpiarParaPost();
                                return;
                            }
                        }
                        ArrayList<Respuesta> arrayRespuesta=new ArrayList<Respuesta>();
                        arrayRespuesta.add(paraPostPersona.get(i).getRespuestas().get(0));
                        paraPostPersona.get(i).setIdRespuesta(arrayRespuesta);
                        paraPostPersona.get(i).setDescripcion(text.getText().toString());
                    }
                }

                if (radioUrbano.isChecked()){
                    fichaHogar.setZona("Urbana");
                }
                if (radioRural.isChecked()){
                    fichaHogar.setZona("Rural");
                }
                fichaHogar.setDireccion(direccion.getText().toString());
                fichaHogar.setCodigo(codHogarMun+codHogarBarrio+"-"+identificacion.getText().toString());
                fichaHogar.setPersonaQueFirma(identificacion.getText().toString());
                if (clasfAlto.isChecked())fichaHogar.setClasificacion("1");
                if (clasfMedio.isChecked())fichaHogar.setClasificacion("2");
                if (clasfBajo.isChecked())fichaHogar.setClasificacion("3");

                fichaHogar.setId(Utilidades.generarId("FH"));
                /*
                Para el momento de guardar
                 */

                persona.setId(Utilidades.generarId("PER"));
                persona.setCodigo(fichaHogar.getCodigo()+(Integer.parseInt(persona.generarCodigoPersona(this,fichaHogar.getId()))+1));
                persona.setFichaHogar(fichaHogar.getId());
                persona.setTipoDocumento(tiposDocumento.get(spinnerTipoDoc.getSelectedItemPosition()).getId());
                persona.setIdentificacion(identificacion.getText().toString());
                persona.setNombre(nombres.getText().toString());
                persona.setApellido(apellidos.getText().toString());
                persona.setFechaNacimiento(fechaNacimiento.getText().toString());
                persona.setEdad(tvEdad.getText().toString());
                persona.setGenero(spinnerGenero.getSelectedItem().toString());
                Intent firma = new Intent(getApplicationContext(), FirmaActivity.class);
                firma.putExtra("fichaHogar", fichaHogar);
                firma.putExtra("persona",  persona);
                firma.putExtra("paraPost",  paraPost);
                firma.putExtra("paraPostPersona", paraPostPersona);
                startActivity(firma);
                break;
            case R.id.btnFechaNacimiento:
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mont, int dayMonth) {
                        int s = mont + 1;
                        String montString="";
                        String dayString="";
                        if (s<10){
                            montString="0"+s;
                        }else{
                            montString=""+s;
                        }
                        if (dayMonth<10){
                            dayString="0"+dayMonth;
                        }else{
                            dayString=""+dayMonth;
                        }
                        String a = dayString + "-" + montString + "-" + year;
                        fechaNacimiento.setText(a);
                        tvEdad.setText(Utilidades.calcularEdad(year+"-"+montString+"-"+dayString));
                    }
                };
                final Calendar c = Calendar.getInstance();
                DatePickerDialog d = new DatePickerDialog(FichaHogarActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                d.getDatePicker().setMaxDate(new Date().getTime());
                d.setCanceledOnTouchOutside(false);
                d.show();
                break;
        }
    }

    class Localizacion implements LocationListener {

        private String longitude="";
        private String latitude="";
        public final int MY_PERMISSION_REQUEST = 1;
        LocationManager locationManager;

        public Localizacion(Context context, Activity activity){
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("GPS","Faltan los permisos de el gps");
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_REQUEST);
                return;
            }
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                alertMessageNoGps(context);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            }
        }

        public String getLongitude(){
            return longitude;
        }

        public String getLatitude(){
            return latitude;
        }

        private void alertMessageNoGps(Context context) {
            final AlertDialog.Builder builder= new AlertDialog.Builder(context);
            builder.setMessage("El gps no esta habilitado conceda permisos").setCancelable(false)
                    .setPositiveButton("Yes conceder", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            final AlertDialog alert= builder.create();
            alert.show();
        }

        @Override
        public void onLocationChanged(Location location) {
            latitude=""+location.getLatitude();
            longitude=""+location.getLongitude();
            Log.i("latitud",latitude);
            Log.i("longitud",longitude);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
