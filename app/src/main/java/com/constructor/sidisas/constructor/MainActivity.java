package com.constructor.sidisas.constructor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText textUsuario,textPassword;
    Button  buttonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonIngresar=(Button) findViewById(R.id.buttonIngresar);
        textUsuario = (EditText) findViewById(R.id.textUsuario);
        textPassword = (EditText) findViewById(R.id.textPassword);
        buttonIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIngresar:
                Intent tablero = new Intent(getApplicationContext(), TableroActivity.class);
                startActivity(tablero);
                break;
        }
    }
}
