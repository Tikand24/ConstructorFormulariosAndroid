package com.constructor.sidisas.constructor;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.constructor.sidisas.constructor.Models.CoreGuardado;
import com.constructor.sidisas.constructor.Models.FichaHogar;
import com.constructor.sidisas.constructor.Models.HojaTrabajo;
import com.constructor.sidisas.constructor.Models.Persona;
import com.constructor.sidisas.constructor.Models.Utilidades;

import java.util.ArrayList;

public class FirmaActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linear;
    Button btnGuardar,btnLimpiarFirma;
    Vista view;
    FichaHogar fichaHogar;
    Persona persona;
    ArrayList<CoreGuardado> paraPost,paraPostPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);

        fichaHogar = new FichaHogar();
        persona = new Persona();
        linear = (LinearLayout) findViewById(R.id.linearFirma);
        btnGuardar = (Button) findViewById(R.id.btnSaveFirma);
        btnLimpiarFirma = (Button) findViewById(R.id.btnLimpiar);
        btnGuardar.setOnClickListener(this);
        btnLimpiarFirma.setOnClickListener(this);
        view=new Vista(this);
        linear.addView(view);
        view.buildDrawingCache();
        capturarDatosIntent();
        /*view.buildDrawingCache();
        Bitmap bm=view.getDrawingCache();
        Save savefile = new Save();
        savefile.SaveImage(getApplicationContext(), bm);*/
    }

    public void capturarDatosIntent(){
        fichaHogar = (FichaHogar) getIntent().getSerializableExtra("fichaHogar");
        persona = (Persona) getIntent().getSerializableExtra("persona");
        paraPost = (ArrayList<CoreGuardado>) getIntent().getExtras().get("paraPost");
        paraPostPersona = (ArrayList<CoreGuardado>) getIntent().getExtras().get("paraPostPersona");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveFirma:
                view.buildDrawingCache();
                Bitmap bm=view.getDrawingCache();
                fichaHogar.setFirma(ImageUtil.convert(bm));
                ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"newaps",null,1);
                SQLiteDatabase db= con.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(Utilidades.FICHA_HOGAR_ID,fichaHogar.getId());
                values.put(Utilidades.FICHA_HOGAR_CODIGO,fichaHogar.getCodigo());
                values.put(Utilidades.FICHA_HOGAR_NOMECLATURA,fichaHogar.getDireccion());
                values.put(Utilidades.FICHA_HOGAR_ZONA,fichaHogar.getZona());
                values.put(Utilidades.FICHA_HOGAR_MUNICIPIO,fichaHogar.getMunicipio());
                values.put(Utilidades.FICHA_HOGAR_BARRIO,fichaHogar.getBarrio());
                values.put(Utilidades.FICHA_HOGAR_FIRMA,fichaHogar.getFirma());
                values.put(Utilidades.FICHA_HOGAR_DOCFIRMA,fichaHogar.getPersonaQueFirma());
                values.put(Utilidades.FICHA_HOGAR_COORDENADAS,fichaHogar.getCoordenadas());
                values.put(Utilidades.FICHA_HOGAR_CLASIFICADO,fichaHogar.getClasificacion());
                values.put(Utilidades.FICHA_HOGAR_USUARIO,"USJO7882151612200659377200");
                values.put(Utilidades.FICHA_HOGAR_CREATED_AT,Utilidades.createdAt());
                values.put(Utilidades.FICHA_HOGAR_UPDATED_AT,Utilidades.createdAt());
                db.insert(Utilidades.TABLA_FICHA_HOGAR,Utilidades.FORMATO_ID,values);

                for (int i=0; i<paraPost.size(); i++){
                    for (int j=0; j<paraPost.get(i).getIdRespuesta().size(); j++){
                        ContentValues respuestaHogar=new ContentValues();
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_ID,Utilidades.generarId("RF"));
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_HOGAR,fichaHogar.getId());
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_RESPUESTA,paraPost.get(i).getIdRespuesta().get(j).getId());
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_DESCRIPCION,paraPost.get(i).getDescripcion());
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_USUARIO,"USJO7882151612200659377200");
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_CREATED_AT,Utilidades.createdAt());
                        respuestaHogar.put(Utilidades.RESPUESTAS_HOGAR_UPDATED_AT,Utilidades.createdAt());
                        db.insert(Utilidades.TABLA_RESPUESTAS_HOGAR,Utilidades.RESPUESTAS_HOGAR_ID,respuestaHogar);
                        Log.i("idRespuesta",paraPost.get(i).getIdRespuesta().get(j).getId()+" descripcion:"+paraPost.get(i).getDescripcion()+" Redirige a:"+paraPost.get(i).getIdRespuesta().get(j).getFormatoRedireccion());

                    }
                }
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
                hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"11");
                if (formatoRepetido3>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"3");
                if (formatoRepetido4>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"4");
                if (formatoRepetido5>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"5");
                if (formatoRepetido6>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"6");
                if (formatoRepetido8>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"8");
                if (formatoRepetido9>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"9");
                if (formatoRepetido10>=1)hojaTrabajo.guardarTarea(getApplicationContext(),persona.getId(),"10");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea agregar otro miembro?")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent pendientesEditar = new Intent(getApplicationContext(), PendientesTareasActivity.class);
                                        pendientesEditar.putExtra("fichaHogar",fichaHogar.getId());
                                        dialog.cancel();
                                        startActivity(pendientesEditar);
                                    }
                                })
                        .setPositiveButton("Si",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent personaActivity = new Intent(getApplicationContext(), PersonaActivity.class);
                                        personaActivity.putExtra("fichaHogar",fichaHogar.getId());
                                        dialog.cancel();
                                        startActivity(personaActivity);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.btnLimpiar:
                view.limpiarCanvas();
                break;
        }
    }

    class Vista extends View {
        float x=0;
        float y=0;
        String accion="accion";
        Path path = new Path();
        public Vista(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint paint= new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            paint.setColor(Color.BLUE);
            int ancho=canvas.getWidth();
            canvas.drawColor(Color.WHITE);
            //canvas.drawRect(10,500,ancho-20,20,paint);

            if (accion=="down")path.moveTo(x,y);
            if (accion=="move")path.lineTo(x,y);
            if (accion=="limpiar") {
                path.reset();
                canvas.drawColor(Color.WHITE);
                this.destroyDrawingCache();
                return;
            }
            canvas.drawPath(path,paint);
        }

        public boolean onTouchEvent(MotionEvent event){
            x=event.getX();
            y=event.getY();
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                accion="down";
            if (event.getAction() == MotionEvent.ACTION_MOVE)
                accion="move";
            invalidate();
            return true;
        }
        public void limpiarCanvas(){
            accion="limpiar";
            invalidate();
        }
    }
}
