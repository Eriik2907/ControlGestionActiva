package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class personal extends AppCompatActivity {

    private EditText etCedula, etApellidos, etNombres, etCargo, etCelular, etDireccion, etPersonal, etInst, etTitulo;
    private CheckBox checkBoxSi, checkBoxNo;
    private Button btnRegistrar, btnVerPersonal;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set window insets listener for the root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CorreoP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText, CheckBox, and Button views
        etCedula = findViewById(R.id.etCedula);
        etApellidos = findViewById(R.id.etApellidos);
        etNombres = findViewById(R.id.etNombres);
        etCargo = findViewById(R.id.etCargo);
        etCelular = findViewById(R.id.etCelular);
        etDireccion = findViewById(R.id.etDireccion);
        etPersonal = findViewById(R.id.etPersonal);
        etInst = findViewById(R.id.etInst);
        etTitulo = findViewById(R.id.etTitulo);
        checkBoxSi = findViewById(R.id.checkBox);
        checkBoxNo = findViewById(R.id.checkBox2);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVerPersonal = findViewById(R.id.btnVerPersonal);

        // Set OnClickListener for the Register button
        btnRegistrar.setOnClickListener(view -> {
            // Get input values
            String cedula = etCedula.getText().toString().trim();
            String apellidos = etApellidos.getText().toString().trim();
            String nombres = etNombres.getText().toString().trim();
            String cargo = etCargo.getText().toString().trim();
            String celular = etCelular.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String personal = etPersonal.getText().toString().trim();
            String inst = etInst.getText().toString().trim();
            String titulo = etTitulo.getText().toString().trim();
            boolean checkBoxSiChecked = checkBoxSi.isChecked();
            boolean checkBoxNoChecked = checkBoxNo.isChecked();

            // Validate input
            if (cedula.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || cargo.isEmpty() ||
                    celular.isEmpty() || direccion.isEmpty() || personal.isEmpty() || inst.isEmpty() || titulo.isEmpty()) {
                Toast.makeText(personal.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ensure only one checkbox is checked
            if (checkBoxSiChecked && checkBoxNoChecked) {
                Toast.makeText(personal.this, "Seleccione solo una opción.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a Map to store the data
            Map<String, Object> data = new HashMap<>();
            data.put("cedula", cedula);
            data.put("apellidos", apellidos);
            data.put("nombres", nombres);
            data.put("cargo", cargo);
            data.put("celular", celular);
            data.put("direccion", direccion);
            data.put("correo_personal", personal);
            data.put("correo_institucional", inst);
            data.put("titulo_profesional", titulo);
            data.put("opcion", checkBoxSiChecked ? "SI" : "NO");

            // Save data to Firebase
            mDatabase.child("personal").push().setValue(data)
                    .addOnSuccessListener(aVoid -> Toast.makeText(personal.this, "Datos guardados exitosamente.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(personal.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show());
        });

        // Set OnClickListener for the View Personal button
        btnVerPersonal.setOnClickListener(view -> {
            // Start the new activity
            Intent intent = new Intent(personal.this, VerPersonalActivity.class);
            startActivity(intent);
        });
    }
}
