package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class registros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        GridLayout mainLayout = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración del OnClickListener para los ImageButtons
        ImageButton imgBtnPersonal = findViewById(R.id.imgBtn_personal);
        imgBtnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPersonal = new Intent(registros.this, personal.class);
                startActivity(intentPersonal);
            }
        });

        ImageButton imgBtnBienes = findViewById(R.id.imgBtn_bienes);
        imgBtnBienes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBienes = new Intent(registros.this, bienes.class);
                startActivity(intentBienes);
            }
        });

        ImageButton imageBtnCustodios = findViewById(R.id.imgBtn_custodios);
        imageBtnCustodios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCustodios = new Intent(registros.this, Custodios.class);
                startActivity(intentCustodios);
            }
        });

        ImageButton imageBtnSedes = findViewById(R.id.imgBtn_sedes);
        imageBtnSedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSedes = new Intent(registros.this, Sedes.class);
                startActivity(intentSedes);
            }
        });

        ImageButton imageBtnAreas = findViewById(R.id.imgBtn_areas);
        imageBtnAreas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAreas = new Intent(registros.this, areas.class);
                startActivity(intentAreas);
            }
        });


    }
}

