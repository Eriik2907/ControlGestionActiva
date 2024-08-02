package com.example.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntrarDocenteActivity extends AppCompatActivity {

    private EditText etEmailDocente, etPasswordDocente;
    private Button btnEntrarDocente;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_docente);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize EditText and Button views
        etEmailDocente = findViewById(R.id.editTextEmailDocente);
        etPasswordDocente = findViewById(R.id.editTextPasswordDocente);
        btnEntrarDocente = findViewById(R.id.btnEntrarDocente);

        // Set OnClickListener for the Login Button
        btnEntrarDocente.setOnClickListener(v -> {
            // Get input values
            String email = etEmailDocente.getText().toString().trim();
            String password = etPasswordDocente.getText().toString().trim();

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(EntrarDocenteActivity.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the docente exists and password matches
            mDatabase.child("docentes").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        boolean passwordMatch = false;
                        // Docente exists, validate password
                        for (DataSnapshot docenteSnapshot : dataSnapshot.getChildren()) {
                            String storedPassword = docenteSnapshot.child("password").getValue(String.class);
                            if (storedPassword != null && storedPassword.equals(password)) {
                                passwordMatch = true;
                                break;
                            }
                        }
                        if (passwordMatch) {
                            Toast.makeText(EntrarDocenteActivity.this, "Login exitoso.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EntrarDocenteActivity.this, BienesDocenteActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else {
                            // If password does not match
                            Toast.makeText(EntrarDocenteActivity.this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Docente does not exist
                        Toast.makeText(EntrarDocenteActivity.this, "No existe una cuenta con este correo.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(EntrarDocenteActivity.this, "Error en la consulta: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}

