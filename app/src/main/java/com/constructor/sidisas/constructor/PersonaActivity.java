package com.constructor.sidisas.constructor;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.constructor.sidisas.constructor.Models.Bloque;
import com.constructor.sidisas.constructor.Models.CoreConstructor;
import com.constructor.sidisas.constructor.Models.CoreGuardado;
import com.constructor.sidisas.constructor.Models.Eps;
import com.constructor.sidisas.constructor.Models.FichaHogar;
import com.constructor.sidisas.constructor.Models.HojaTrabajo;
import com.constructor.sidisas.constructor.Models.Persona;
import com.constructor.sidisas.constructor.Models.Respuesta;
import com.constructor.sidisas.constructor.Models.TipoDocumento;
import com.constructor.sidisas.constructor.Models.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PersonaActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout contenedorPersonas;
    ArrayList<CoreGuardado> paraPostPersona;
    Button buttonEnviarDatos;
    ImageButton btnFechaNacimiento;
    Spinner spinnerTipoDoc,spinnerGenero;
    Persona persona = new Persona();
    TextInputEditText identificacion,nombres,apellidos;
    TextView fechaNacimiento,tvEdad;
    AutoCompleteTextView buscadorEps;
    ArrayList<TipoDocumento> tiposDocumento;
    String idFichaHogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        capturarDatosIntent();
        contenedorPersonas = (LinearLayout) findViewById(R.id.contenedorPersonas);
        buttonEnviarDatos= (Button) findViewById(R.id.buttonEnviarDatos);
        btnFechaNacimiento= (ImageButton) findViewById(R.id.btnFechaNacimiento);
        spinnerTipoDoc = (Spinner) findViewById(R.id.spinnerTipoDoc);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
        identificacion = (TextInputEditText) findViewById(R.id.txtDocIdentidad);
        nombres = (TextInputEditText) findViewById(R.id.txtNombre);
        apellidos = (TextInputEditText) findViewById(R.id.txtApellidos);
        fechaNacimiento = (TextView) findViewById(R.id.fechaNacimiento);
        buscadorEps = (AutoCompleteTextView) findViewById(R.id.buscadorEps);
        tvEdad= (TextView) findViewById(R.id.txtEdad);
        buttonEnviarDatos.setOnClickListener(this);
        btnFechaNacimiento.setOnClickListener(this);

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
            bloque.setBackgroundColor(Color.parseColor("#0277bd"));
            bloque.setTextColor(Color.BLACK);
            bloque.setHeight(150);
            bloque.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
                                    DatePickerDialog d = new DatePickerDialog(PersonaActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
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
                persona.setEps(eps.buscarEpsPorNombre(PersonaActivity.this,nombreEps));
                Log.i("Eps","posicion:"+posicion+" "+adapterView.getItemAtPosition(posicion));
            }
        });
    }

    public void limpiarParaPost(){
        for (int i=0; i<paraPostPersona.size();i++){
            paraPostPersona.get(i).setIdRespuesta(new ArrayList<Respuesta>());
            paraPostPersona.get(i).setDescripcion("");
        }
    }


    public void capturarDatosIntent(){
        idFichaHogar =  getIntent().getStringExtra("fichaHogar");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonEnviarDatos:
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
                ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
                SQLiteDatabase db= con.getWritableDatabase();
                FichaHogar fichaHogar=new FichaHogar().getFichaPorId(this,idFichaHogar);
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
                persona.save(this,persona);
                int formatoRepetido3=0;
                int formatoRepetido4=0;
                int formatoRepetido5=0;
                int formatoRepetido6=0;
                int formatoRepetido8=0;
                int formatoRepetido9=0;
                int formatoRepetido10=0;
                for (int i=0; i<paraPostPersona.size(); i++){
                    for (int j=0; j<paraPostPersona.get(i).getIdRespuesta().size(); j++){
                        ContentValues respuestaPersona=new ContentValues();
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_ID,Utilidades.generarId("RF"));
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_PERSONA,persona.getId());
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_RESPUESTA,paraPostPersona.get(i).getIdRespuesta().get(j).getId());
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_DESCRIPCION,paraPostPersona.get(i).getDescripcion());
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_USUARIO,"USJO7882151612200659377200");
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_CREATED_AT,Utilidades.createdAt());
                        respuestaPersona.put(Utilidades.RESPUESTAS_PERSONA_UPDATED_AT,Utilidades.createdAt());
                        db.insert(Utilidades.TABLA_RESPUESTAS_PERSONA,Utilidades.RESPUESTAS_PERSONA_ID,respuestaPersona);
                        Log.i("idRespuesta",paraPostPersona.get(i).getIdRespuesta().get(j).getId()+" descripcion:"+paraPostPersona.get(i).getDescripcion()+" Redirige a:"+paraPostPersona.get(i).getIdRespuesta().get(j).getFormatoRedireccion());
                        switch (paraPostPersona.get(i).getIdRespuesta().get(j).getFormatoRedireccion()){
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
                hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"7");
                if (formatoRepetido3>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"3");
                if (formatoRepetido4>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"4");
                if (formatoRepetido5>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"5");
                if (formatoRepetido6>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"6");
                if (formatoRepetido8>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"8");
                if (formatoRepetido9>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"9");
                if (formatoRepetido10>=1)hojaTrabajo.guardarTarea(this,persona.getId(),"10");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea agregar otro miembro?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent pendientesEditar = new Intent(getApplicationContext(), PendientesTareasActivity.class);
                                        pendientesEditar.putExtra("fichaHogar",idFichaHogar);
                                        dialog.cancel();
                                        startActivity(pendientesEditar);
                                    }
                                })
                        .setPositiveButton("Si",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent personaActivity = new Intent(getApplicationContext(), PersonaActivity.class);
                                        personaActivity.putExtra("fichaHogar",idFichaHogar);
                                        dialog.cancel();
                                        startActivity(personaActivity);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
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
                DatePickerDialog d = new DatePickerDialog(PersonaActivity.this,dpd,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                d.getDatePicker().setMaxDate(new Date().getTime());
                d.setCanceledOnTouchOutside(false);
                d.show();
                break;
        }
    }
}
