package com.constructor.sidisas.constructor;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TextActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout contenedorTest;
    int idEditTextPrueba;
    Button buttonEnviarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        contenedorTest = findViewById(R.id.contenedorTest);
        buttonEnviarDatos = findViewById(R.id.buttonEnviarDatosTest);
        buttonEnviarDatos.setOnClickListener(this);
        /*
        EditText texto= new EditText(getApplicationContext());
        texto.setText("DE text");
        texto.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        */

        TextInputLayout pregunta = new TextInputLayout(this);
        pregunta.setHint("Hitn de texto");
        LinearLayout.LayoutParams layoutParamPregunta = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamPregunta.setMargins(0,30,0,15);
        pregunta.setLayoutParams(layoutParamPregunta);

        TextInputEditText textoEdit = new TextInputEditText(this);
        pregunta.addView(textoEdit);
        int idEditText=(int) (Math.random()*10000)+1;
        textoEdit.setId(idEditText);
        idEditTextPrueba = idEditText;
        contenedorTest.addView(pregunta);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEnviarDatosTest:
                TextInputEditText text= (TextInputEditText) findViewById(idEditTextPrueba);
                Log.i("textoMostrado",text.getText().toString());
                break;
        }
    }
}
