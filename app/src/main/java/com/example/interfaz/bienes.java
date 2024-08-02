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

public class bienes extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText codigoEditText, tipoEditText, marcaEditText, descripcionEditText, asignadoAEditText;
    private Button saveButton, viewGoodsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienes);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize UI elements
        codigoEditText = findViewById(R.id.editTextText16);
        tipoEditText = findViewById(R.id.editTextText17);
        marcaEditText = findViewById(R.id.editTextText18);
        descripcionEditText = findViewById(R.id.editTextText19);
        asignadoAEditText = findViewById(R.id.editTextAsignadoA); // Initialize new EditText
        saveButton = findViewById(R.id.button4);
        viewGoodsButton = findViewById(R.id.buttonViewGoods);

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set onClickListener for save button
        saveButton.setOnClickListener(v -> storeBienesDataInFirebase());

        // Set onClickListener for view goods button
        viewGoodsButton.setOnClickListener(v -> {
            // Start the activity to view registered goods
            Intent intent = new Intent(bienes.this, BienesRegistradosActivity.class);
            startActivity(intent);
        });
    }

    private void storeBienesDataInFirebase() {
        String bienId = mDatabase.child("bienes").push().getKey(); // Generate a unique ID for each good
        String codigo = codigoEditText.getText().toString();
        String tipo = tipoEditText.getText().toString();
        String marca = marcaEditText.getText().toString();
        String descripcion = descripcionEditText.getText().toString();
        String asignadoA = asignadoAEditText.getText().toString(); // Get the value for the new field

        Map<String, Object> bienesData = new HashMap<>();
        bienesData.put("codigo", codigo);
        bienesData.put("tipo", tipo);
        bienesData.put("marca", marca);
        bienesData.put("descripcion", descripcion);
        bienesData.put("asignadoA", asignadoA); // Add the new field to the data map

        // Store bienes data in Firebase Database
        if (bienId != null) {
            mDatabase.child("bienes").child(bienId).setValue(bienesData);
        }
    }
}
