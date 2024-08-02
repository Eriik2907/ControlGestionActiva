package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    private Spinner registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CorreoP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        registro =(Spinner) findViewById(R.id.registro);
        String [] opciones={"Inicio", "Registers"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        registro.setAdapter(adapter);

        registro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = (String) parentView.getItemAtPosition(position);
                switch (selectedItem) {
                    case "Custodios":

                        Intent intentCustodios = new Intent(Menu.this, Custodios.class);
                        startActivity(intentCustodios);
                        break;

                    case "Sedes":

                        Intent intentSedes = new Intent(Menu.this, Sedes.class);
                        startActivity(intentSedes);
                        break;

                    case "Personal":

                        Intent intentPersonal = new Intent(Menu.this, personal.class);
                        startActivity(intentPersonal);
                        break;

                    case "Registers":

                        Intent intentRegisters = new Intent(Menu.this, registros.class);
                        startActivity(intentRegisters);
                        break;



                    case "Perfil":

                        Intent intentPerfil = new Intent(Menu.this, perfil.class);
                        startActivity(intentPerfil);
                        break;

                    case "Areas":

                        Intent intentAreas = new Intent(Menu.this, areas.class);
                        startActivity(intentAreas);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
