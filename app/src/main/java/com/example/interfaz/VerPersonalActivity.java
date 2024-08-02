package com.example.interfaz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerPersonalActivity extends AppCompatActivity {

    private TextView tvPersonalList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personal);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize TextView
        tvPersonalList = findViewById(R.id.tvPersonalList);

        // Fetch and display data
        mDatabase.child("personal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder personalList = new StringBuilder();
                personalList.append("Listado de Personal:\n\n");

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get personal data
                    String cedula = (String) snapshot.child("cedula").getValue();
                    String nombres = (String) snapshot.child("nombres").getValue();
                    String apellidos = (String) snapshot.child("apellidos").getValue();
                    String cargo = (String) snapshot.child("cargo").getValue();
                    String celular = (String) snapshot.child("celular").getValue();
                    String direccion = (String) snapshot.child("direccion").getValue();
                    String correoPersonal = (String) snapshot.child("correo_personal").getValue();
                    String correoInstitucional = (String) snapshot.child("correo_institucional").getValue();
                    String tituloProfesional = (String) snapshot.child("titulo_profesional").getValue();
                    String opcion = (String) snapshot.child("opcion").getValue();

                    // Append personal data in a formatted way
                    personalList.append("Cédula: ").append(cedula).append("\n")
                            .append("Nombres: ").append(nombres).append("\n")
                            .append("Apellidos: ").append(apellidos).append("\n")
                            .append("Cargo: ").append(cargo).append("\n")
                            .append("Celular: ").append(celular).append("\n")
                            .append("Dirección: ").append(direccion).append("\n")
                            .append("Correo Personal: ").append(correoPersonal).append("\n")
                            .append("Correo Institucional: ").append(correoInstitucional).append("\n")
                            .append("Título Profesional: ").append(tituloProfesional).append("\n")
                            .append("Opción: ").append(opcion).append("\n\n");
                }
                // Display data in TextView
                tvPersonalList.setText(personalList.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                tvPersonalList.setText("Error al recuperar datos: " + databaseError.getMessage());
            }
        });
    }
}
