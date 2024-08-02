package com.example.interfaz;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btlogin, btnEntrarDocente, btnRegistrarDocente;
    private TextView recuperar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize EditText, Button, and TextView views
        etUsuario = findViewById(R.id.editTextText2);
        etPassword = findViewById(R.id.editTextTextPassword);
        btlogin = findViewById(R.id.btnLogin);
        recuperar = findViewById(R.id.recuperar);
        btnEntrarDocente = findViewById(R.id.btnEntrarDocente);
        btnRegistrarDocente = findViewById(R.id.btnRegistrarDocente);

        // Set OnClickListener for the Recover TextView
        recuperar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecuperarC.class);
            startActivity(intent);
        });

        // Set OnClickListener for the Login Button
        btlogin.setOnClickListener(v -> {
            // Get input values
            String usuario = etUsuario.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validate input
            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if user already exists
            mDatabase.child("usuarios").orderByChild("usuario").equalTo(usuario).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists, allow login
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String storedPassword = userSnapshot.child("password").getValue(String.class);
                            if (storedPassword != null && storedPassword.equals(password)) {
                                Toast.makeText(MainActivity.this, "Login exitoso.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Menu.class);
                                startActivity(intent);
                                return;
                            }
                        }
                        // If password does not match
                        Toast.makeText(MainActivity.this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    } else {
                        // User does not exist, create new user
                        Map<String, String> userData = new HashMap<>();
                        userData.put("usuario", usuario);
                        userData.put("password", password);

                        // Save data to Firebase
                        mDatabase.child("usuarios").push().setValue(userData)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(MainActivity.this, "Usuario registrado exitosamente.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, Menu.class);
                                    startActivity(intent);
                                })
                                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Error en la consulta: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        // Set OnClickListener for Entrar Docente Button
        btnEntrarDocente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EntrarDocenteActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for Registrar Docente Button
        btnRegistrarDocente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrarDocenteActivity.class);
            startActivity(intent);
        });
// MainActivity.java
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
        // Set window insets listener for the root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CorreoP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
