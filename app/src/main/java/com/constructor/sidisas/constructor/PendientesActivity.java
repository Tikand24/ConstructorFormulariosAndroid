package com.constructor.sidisas.constructor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.constructor.sidisas.constructor.Models.HojaTrabajo;

import java.util.ArrayList;

public class PendientesActivity extends AppCompatActivity implements View.OnClickListener{

    TextView fichaHogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes);
        fichaHogar = findViewById(R.id.tvFichaHogarPendientes);
        fichaHogar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFichaHogarPendientes:
                this.tareasPendientesActivity();
                break;
        }
    }
    public void tareasPendientesActivity () {
        Intent pendientesTareasActivity = new Intent(getApplicationContext(), PendientesTareasActivity.class);
        startActivity(pendientesTareasActivity);
    }
}
