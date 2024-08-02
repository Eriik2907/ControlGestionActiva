package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Custodios extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custodios);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CorreoP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get references to the EditText fields and Buttons
        EditText editTextCedula = findViewById(R.id.editTextText);
        EditText editTextAsignadoA = findViewById(R.id.editTextAsignadoA); // Updated ID
        EditText editTextInventario = findViewById(R.id.editTextText3); // Updated ID
        EditText editTextFecha = findViewById(R.id.editTextDate);
        EditText editTextActa = findViewById(R.id.editTextText4);
        EditText editTextObservacion = findViewById(R.id.editTextText5);
        Button buttonGuardar = findViewById(R.id.button);
        Button buttonVerCustodios = findViewById(R.id.buttonVerCustodios);

        // Set onClickListener for the Save button
        buttonGuardar.setOnClickListener(v -> {
            // Get input values
            String cedula = editTextCedula.getText().toString();
            String inventario = editTextInventario.getText().toString();
            String asignadoA = editTextAsignadoA.getText().toString(); // Updated to use new field
            String fecha = editTextFecha.getText().toString();
            String acta = editTextActa.getText().toString();
            String observacion = editTextObservacion.getText().toString();

            // Validate input
            if (cedula.isEmpty() || inventario.isEmpty() || asignadoA.isEmpty() || fecha.isEmpty() || acta.isEmpty() || observacion.isEmpty()) {
                // Optionally show an error message
                return;
            }

            // Create a Map to store the data
            Map<String, Object> data = new HashMap<>();
            data.put("cedula", cedula);
            data.put("inventario", inventario);
            data.put("asignadoA", asignadoA); // Store new field value
            data.put("fecha", fecha);
            data.put("acta", acta);
            data.put("observacion", observacion);

            // Save data to Firebase
            mDatabase.child("custodios").push().setValue(data)
                    .addOnSuccessListener(aVoid -> {
                        // Optionally show a success message
                    })
                    .addOnFailureListener(e -> {
                        // Optionally handle the error
                    });
        });

        // Set onClickListener for the View Custodios button
        buttonVerCustodios.setOnClickListener(v -> {
            // Start the activity to view custodios
            Intent intent = new Intent(Custodios.this, ViewCustodiosActivity.class);
            startActivity(intent);
        });
    }
}
