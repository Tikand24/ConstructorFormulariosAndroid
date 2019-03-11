package com.constructor.sidisas.constructor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.constructor.sidisas.constructor.Models.FichaHogar;
import com.constructor.sidisas.constructor.Models.HojaTrabajo;

import java.util.ArrayList;

public class PendientesTareasActivity extends AppCompatActivity implements View.OnClickListener{

    String fichaHogar;
    ListView lvAiepi,lvKardex,lvCancer,lvTest,lvDemanda,lvPromocion;
    TextView tvAiepiPendientesTarea,tvCancerMamaPendientesTarea,tvKardexPendientesTarea,tvTestFindriskPendientesTarea,tvDemandaPendientesTarea,tvPromocionPendientesTarea;
    ArrayList<ArrayList<HojaTrabajo>> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes_tareas);
        capturarDatos();
        HojaTrabajo hojaTrabajo=new HojaTrabajo();
        datos=hojaTrabajo.allTareasPendientes(getApplicationContext(),fichaHogar);
        Log.i("aiepi",""+datos.get(0).size());
        Log.i("kardex",""+datos.get(1).size());
        Log.i("cancer",""+datos.get(2).size());
        Log.i("test",""+datos.get(3).size());
        Log.i("demanda",""+datos.get(4).size());
        Log.i("promocion",""+datos.get(5).size());

        ArrayList<String>datosAiepi= new ArrayList<>();
        ArrayList<String>datosKardex= new ArrayList<>();
        ArrayList<String>datosCancerMama= new ArrayList<>();
        ArrayList<String>datosTest= new ArrayList<>();
        ArrayList<String>datosDemanda= new ArrayList<>();
        ArrayList<String>datosPromocion= new ArrayList<>();

        for (int i=0; i<datos.get(0).size();i++){
            datosAiepi.add(datos.get(0).get(i).getPersonaObj().getNombreCompleto());
        }
        for (int i=0; i<datos.get(1).size();i++){
            datosKardex.add(datos.get(1).get(i).getPersonaObj().getNombreCompleto());
        }
        for (int i=0; i<datos.get(2).size();i++){
            datosCancerMama.add(datos.get(2).get(i).getPersonaObj().getNombreCompleto());
        }
        for (int i=0; i<datos.get(3).size();i++){
            datosTest.add(datos.get(3).get(i).getPersonaObj().getNombreCompleto());
        }
        for (int i=0; i<datos.get(4).size();i++){
            datosDemanda.add(datos.get(4).get(i).getPersonaObj().getNombreCompleto());
        }
        for (int i=0; i<datos.get(5).size();i++){
            datosPromocion.add(datos.get(5).get(i).getPersonaObj().getNombreCompleto());
        }

        ArrayAdapter<CharSequence> adaptadorAiepiPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosAiepi);
        ArrayAdapter<CharSequence> adaptadorKardexPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosKardex);
        ArrayAdapter<CharSequence> adaptadorCancerMamaPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosCancerMama);
        ArrayAdapter<CharSequence> adaptadorTestPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosTest);
        ArrayAdapter<CharSequence> adaptadorDemandaPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosDemanda);
        ArrayAdapter<CharSequence> adaptadorPromocionPendientes=new ArrayAdapter(this,android.R.layout.simple_list_item_1,datosPromocion);
        lvAiepi = (ListView) findViewById(R.id.listViewAiepi);
        lvKardex = (ListView) findViewById(R.id.listViewKardex);
        lvCancer = (ListView) findViewById(R.id.listViewCancer);
        lvTest = (ListView) findViewById(R.id.listViewTest);
        lvDemanda = (ListView) findViewById(R.id.listViewDemanda);
        lvPromocion = (ListView) findViewById(R.id.listViewPromocion);

        tvAiepiPendientesTarea = findViewById(R.id.tvAiepiPendientesTarea);
        tvCancerMamaPendientesTarea= findViewById(R.id.tvCancerMamaPendientesTarea);
        tvKardexPendientesTarea= findViewById(R.id.tvKardexPendientesTarea);
        tvTestFindriskPendientesTarea= findViewById(R.id.tvTestFindriskPendientesTarea);
        tvDemandaPendientesTarea= findViewById(R.id.tvDemandaPendientesTarea);
        tvPromocionPendientesTarea= findViewById(R.id.tvPromocionPendientesTarea);

        tvAiepiPendientesTarea.setOnClickListener(this);
        tvCancerMamaPendientesTarea.setOnClickListener(this);
        tvKardexPendientesTarea.setOnClickListener(this);
        tvTestFindriskPendientesTarea.setOnClickListener(this);
        tvDemandaPendientesTarea.setOnClickListener(this);
        tvPromocionPendientesTarea.setOnClickListener(this);

        lvAiepi.setAdapter(adaptadorAiepiPendientes);
        lvKardex.setAdapter(adaptadorKardexPendientes);
        lvCancer.setAdapter(adaptadorCancerMamaPendientes);
        lvTest.setAdapter(adaptadorTestPendientes);
        lvDemanda.setAdapter(adaptadorDemandaPendientes);
        lvPromocion.setAdapter(adaptadorPromocionPendientes);

        lvAiepi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(0).get(posicion).getId());
            }
        });
        lvKardex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(1).get(posicion).getId());
            }
        });
        lvCancer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(2).get(posicion).getId());
            }
        });
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(3).get(posicion).getId());
            }
        });
        lvDemanda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(4).get(posicion).getId());
            }
        });
        lvPromocion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Log.i("htRedirigir",datos.get(5).get(posicion).getId());
            }
        });

    }

    public void capturarDatos(){
        fichaHogar = getIntent().getStringExtra("fichaHogar");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAiepiPendientesTarea:
                this.redirectAiepiActivity();
                break;
            case R.id.tvCancerMamaPendientesTarea:
                this.redirectCancerMamaActivity();
                break;
            case R.id.tvKardexPendientesTarea:
                this.redirectKardexActivity();
                break;
            case R.id.tvTestFindriskPendientesTarea:
                this.redirectTestFindriskActivity();
                break;
            case R.id.tvDemandaPendientesTarea:
                this.redirectDemandaInducidaActivity();
                break;
            case R.id.tvPromocionPendientesTarea:
                this.redirectPromocionActivity();
                break;
        }
    }

    public void redirectAiepiActivity() {
        Intent aiepiActivity = new Intent(getApplicationContext(), AiepiActivity.class);
        startActivity(aiepiActivity);
    }
    public void redirectCancerMamaActivity() {
        Intent cancerMamaActivity = new Intent(getApplicationContext(), CancerMamaActivity.class);
        startActivity(cancerMamaActivity);
    }
    public void redirectKardexActivity() {
        Intent kardexActivity = new Intent(getApplicationContext(), KardexActivity.class);
        startActivity(kardexActivity);
    }
    public void redirectTestFindriskActivity() {
        Intent testFindriskActivity = new Intent(getApplicationContext(), TestFindriskActivity.class);
        startActivity(testFindriskActivity);
    }
    public void redirectDemandaInducidaActivity() {
        Intent demandaInducidaActivity = new Intent(getApplicationContext(), DemandaInducidaActivity.class);
        startActivity(demandaInducidaActivity);
    }
    public void redirectPromocionActivity() {
        Intent promocionActivity = new Intent(getApplicationContext(), PromocionActivity.class);
        startActivity(promocionActivity);
    }
}
