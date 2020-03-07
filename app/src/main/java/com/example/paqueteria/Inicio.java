package com.example.paqueteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);


    }

    public void Calcular(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Gestionar(View view){
        Intent i = new Intent(this, Gestion.class);
        startActivity(i);
    }

}
