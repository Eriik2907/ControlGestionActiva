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

public class areas extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText descriptionEditText, referenceEditText, numberOfAulasEditText, capacityEditText;
    private Button saveButton, viewAreasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_areas);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize UI elements
        descriptionEditText = findViewById(R.id.editTextText13);
        referenceEditText = findViewById(R.id.editTextText14);
        numberOfAulasEditText = findViewById(R.id.editTextNumber3);
        capacityEditText = findViewById(R.id.editTextText15);
        saveButton = findViewById(R.id.button3);
        viewAreasButton = findViewById(R.id.buttonVerAreas);

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set onClickListener for save button
        saveButton.setOnClickListener(v -> storeAreaDataInFirebase());

        // Set onClickListener for view areas button
        viewAreasButton.setOnClickListener(v -> {
            Intent intent = new Intent(areas.this, AreasRegistradasActivity.class);
            startActivity(intent);
        });
    }

    private void storeAreaDataInFirebase() {
        String areaId = mDatabase.child("areas").push().getKey(); // Generate a unique ID for each area
        String description = descriptionEditText.getText().toString();
        String reference = referenceEditText.getText().toString();
        int numberOfAulas = Integer.parseInt(numberOfAulasEditText.getText().toString());
        int capacity = Integer.parseInt(capacityEditText.getText().toString());

        Map<String, Object> areaData = new HashMap<>();
        areaData.put("description", description);
        areaData.put("reference", reference);
        areaData.put("number_of_aulas", numberOfAulas);
        areaData.put("capacity", capacity);

        // Store area data in Firebase Database
        if (areaId != null) {
            mDatabase.child("areas").child(areaId).setValue(areaData);
        }
    }
}
