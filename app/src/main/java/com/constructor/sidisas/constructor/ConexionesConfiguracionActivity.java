package com.constructor.sidisas.constructor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.design.widget.TextInputEditText;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class ConexionesConfiguracionActivity extends AppCompatActivity implements View.OnFocusChangeListener,View.OnTouchListener{
    TextInputLayout textInputLayoutPuerto;
    TextInputEditText textInputEditTextPuerto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexiones_configuracion);
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_window_focused},
                new int[] { android.R.attr.state_enabled},
                new int[] { android.R.attr.state_pressed},
                new int[] { android.R.attr.state_active}
        };

        int[] colors = new int[] {
                Color.YELLOW,
                Color.BLACK,
                Color.CYAN,
                Color.GREEN,
                Color.MAGENTA
        };

        ColorStateList myList = new ColorStateList(states, colors);
        textInputLayoutPuerto = (TextInputLayout) findViewById(R.id.texInputLayoutPuerto);
        textInputEditTextPuerto = (TextInputEditText) findViewById(R.id.textInputLayoutEditTextPuerto);
        textInputEditTextPuerto.setBackgroundTintList(myList);
        textInputLayoutPuerto.setBackgroundTintList(myList);
        textInputEditTextPuerto.setOnFocusChangeListener(this);
        textInputLayoutPuerto.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {
            case R.id.texInputLayoutPuerto:
                Log.i("change",""+hasFocus);
                Log.i("change","Cambio layout input ");
                break;
            case R.id.textInputLayoutEditTextPuerto:
                Log.i("change",""+hasFocus);
                for (int dato:textInputEditTextPuerto.getDrawableState()) {
                    Log.i("change","Dato "+dato);
                }
                Log.i("change","Cambio edit text input ");

                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
