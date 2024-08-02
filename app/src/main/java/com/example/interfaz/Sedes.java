package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Sedes extends AppCompatActivity {

    private EditText etNombreSede, etDireccion, etTelefono, etEncargado, etNumeroAulas, etNumeroOficinas, etDescripcion, etCoordenadas;
    private Button btnGuardar, btnMapa, btnSedes;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sedes);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set window insets listener for the root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CorreoP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText and Button views with updated IDs
        etNombreSede = findViewById(R.id.editTextText6);
        etDireccion = findViewById(R.id.editTextText7);
        etTelefono = findViewById(R.id.editTextText8);
        etEncargado = findViewById(R.id.editTextText9);
        etNumeroAulas = findViewById(R.id.editTextNumber);
        etNumeroOficinas = findViewById(R.id.editTextNumber2);
        etDescripcion = findViewById(R.id.editTextText10);
        etCoordenadas = findViewById(R.id.editTextText12);
        btnGuardar = findViewById(R.id.button2);
        btnMapa = findViewById(R.id.buttonMapa);
        btnSedes = findViewById(R.id.buttonSedes);

        // Set OnClickListener for the Save button
        btnGuardar.setOnClickListener(view -> {
            // Get input values
            String nombreSede = etNombreSede.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String encargado = etEncargado.getText().toString().trim();
            String numeroAulas = etNumeroAulas.getText().toString().trim();
            String numeroOficinas = etNumeroOficinas.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String coordenadas = etCoordenadas.getText().toString().trim();

            // Validate input
            if (nombreSede.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || encargado.isEmpty() ||
                    numeroAulas.isEmpty() || numeroOficinas.isEmpty() || descripcion.isEmpty() || coordenadas.isEmpty()) {
                Toast.makeText(Sedes.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a Map to store the data
            Map<String, Object> data = new HashMap<>();
            data.put("nombre_sede", nombreSede);
            data.put("direccion", direccion);
            data.put("telefono", telefono);
            data.put("encargado", encargado);
            data.put("numero_aulas", numeroAulas);
            data.put("numero_oficinas", numeroOficinas);
            data.put("descripcion", descripcion);
            data.put("coordenadas", coordenadas);

            // Save data to Firebase
            mDatabase.child("sedes").push().setValue(data)
                    .addOnSuccessListener(aVoid -> Toast.makeText(Sedes.this, "Datos guardados exitosamente.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(Sedes.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show());
        });

        // Set OnClickListener for the Map button
        btnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(Sedes.this, MapaActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for the "VER SEDES REGISTRADAS" button
        btnSedes.setOnClickListener(v -> {
            Intent intent = new Intent(Sedes.this, SedesRegistradasActivity.class);
            startActivity(intent);
        });
    }
}


