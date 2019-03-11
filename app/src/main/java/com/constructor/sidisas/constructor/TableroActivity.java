package com.constructor.sidisas.constructor;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.constructor.sidisas.constructor.Models.CoreGuardado;
import com.constructor.sidisas.constructor.Models.Respuesta;
import com.constructor.sidisas.constructor.Models.Utilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TableroActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageTableroFichaHogar,imageTableroParametros,imageTableroPendientes;
    TextView textTableroFichaHogar,textTableroParametros,textTableroPendientes;
    Button buttonGoTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        imageTableroFichaHogar = (ImageView) findViewById(R.id.imageTableroFichaHogar);
        imageTableroParametros = (ImageView) findViewById(R.id.imageTableroParametros);
        imageTableroPendientes = (ImageView) findViewById(R.id.imageTableroPendientes);
        textTableroFichaHogar = (TextView) findViewById(R.id.textTableroFichaHogar);
        textTableroParametros = (TextView) findViewById(R.id.textTableroParametros);
        textTableroPendientes = (TextView) findViewById(R.id.textTableroPendientes);
        imageTableroFichaHogar.setOnClickListener(this);
        imageTableroParametros.setOnClickListener(this);
        imageTableroPendientes.setOnClickListener(this);
        textTableroFichaHogar.setOnClickListener(this);
        textTableroParametros.setOnClickListener(this);
        textTableroPendientes.setOnClickListener(this);
        buttonGoTest = findViewById(R.id.buttonGoTest);
        buttonGoTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageTableroFichaHogar:
                this.fichaHogarRedireccion();
                break;
            case R.id.textTableroFichaHogar:
                this.fichaHogarRedireccion();
                break;
            case R.id.imageTableroParametros:
                this.parametrosRedireccion();
                break;
            case R.id.textTableroParametros:
                this.parametrosRedireccion();
                break;
            case R.id.imageTableroPendientes:
                this.pendientesRedireccion();
                break;
            case R.id.textTableroPendientes:
                this.pendientesRedireccion();
                break;
            case R.id.buttonGoTest:
                Intent testActivity = new Intent(getApplicationContext(), TextActivity.class);
                startActivity(testActivity);
                break;
        }
    }
    public void fichaHogarRedireccion () {
        Intent fichaHogarActivity = new Intent(getApplicationContext(), FichaHogarActivity.class);
        startActivity(fichaHogarActivity);
    }
    public void parametrosRedireccion () {
        Intent parametrosActivity = new Intent(getApplicationContext(), ParametrosActivity.class);
        startActivity(parametrosActivity);
    }
    public void pendientesRedireccion () {
        Intent parametrosActivity = new Intent(getApplicationContext(), PendientesActivity.class);
        startActivity(parametrosActivity);
    }
}
