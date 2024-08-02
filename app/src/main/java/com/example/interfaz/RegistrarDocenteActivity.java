package com.example.interfaz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarDocenteActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEmail, etPassword;
    private Button btnRegistrarDocente;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_docente);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize EditText and Button views
        etNombre = findViewById(R.id.editTextNombre);
        etApellido = findViewById(R.id.editTextApellido);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnRegistrarDocente = findViewById(R.id.btnRegistrarDocente);

        // Set OnClickListener for the Register Button
        btnRegistrarDocente.setOnClickListener(v -> {
            // Get input values
            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validate input
            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegistrarDocenteActivity.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create new docente data
            Map<String, String> docenteData = new HashMap<>();
            docenteData.put("nombre", nombre);
            docenteData.put("apellido", apellido);
            docenteData.put("email", email);
            docenteData.put("password", password);

            // Save data to Firebase
            mDatabase.child("docentes").push().setValue(docenteData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(RegistrarDocenteActivity.this, "Docente registrado exitosamente.", Toast.LENGTH_SHORT).show();
                        finish();  // Close the activity after successful registration
                    })
                    .addOnFailureListener(e -> Toast.makeText(RegistrarDocenteActivity.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show());
        });
    }
}
