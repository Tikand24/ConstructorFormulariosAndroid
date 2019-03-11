package com.constructor.sidisas.constructor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ParametrosActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageConexionesConfiguracion,imageViewEstructuraFormularios;
    TextView textViewConexionesTitulo,textViewConexionesDescripcion,textViewEstructuraFormulariosTitulo,textViewEstructuraFormulariosDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);
        imageConexionesConfiguracion = (ImageView) findViewById(R.id.imageViewConexiones);
        imageViewEstructuraFormularios = (ImageView) findViewById(R.id.imageViewEstructuraFormularios);
        textViewConexionesTitulo = (TextView) findViewById(R.id.textViewConexionesTitulo);
        textViewConexionesDescripcion = (TextView) findViewById(R.id.textViewConexionesDescripcion);
        textViewEstructuraFormulariosTitulo = (TextView) findViewById(R.id.textViewEstructuraFormulariosTitulo);
        textViewEstructuraFormulariosDescripcion = (TextView) findViewById(R.id.textViewEstructuraFormulariosDescripcion);
        textViewConexionesTitulo.setOnClickListener(this);
        textViewConexionesDescripcion.setOnClickListener(this);
        textViewEstructuraFormulariosTitulo.setOnClickListener(this);
        textViewEstructuraFormulariosDescripcion.setOnClickListener(this);
        imageConexionesConfiguracion.setOnClickListener(this);
        imageViewEstructuraFormularios.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewConexiones:
                this.configuracionConexionesRedirecciones();
                break;
            case R.id.textViewConexionesTitulo:
                this.configuracionConexionesRedirecciones();
                break;
            case R.id.textViewConexionesDescripcion:
                this.configuracionConexionesRedirecciones();
                break;
            case R.id.imageViewEstructuraFormularios:
                this.configuracionEstructuraFormulariosRedirecciones();
                break;
            case R.id.textViewEstructuraFormulariosTitulo:
                this.configuracionEstructuraFormulariosRedirecciones();
                break;
            case R.id.textViewEstructuraFormulariosDescripcion:
                this.configuracionEstructuraFormulariosRedirecciones();
                break;
        }
    }

    public void configuracionConexionesRedirecciones () {
        Intent configuracionConexionesActivity = new Intent(getApplicationContext(), ConexionesConfiguracionActivity.class);
        startActivity(configuracionConexionesActivity);
    }

    public void configuracionEstructuraFormulariosRedirecciones () {
        Intent estructuraFormularioActivity = new Intent(getApplicationContext(), EstructuraFormulariosActivity.class);
        startActivity(estructuraFormularioActivity);
    }
}
