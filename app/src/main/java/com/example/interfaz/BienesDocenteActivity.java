package com.example.interfaz;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BienesDocenteActivity extends AppCompatActivity {

    private ListView listViewBienes;
    private DatabaseReference mDatabase;
    private List<String> bienesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienes_docente);

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize ListView and List
        listViewBienes = findViewById(R.id.listViewBienes);
        bienesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bienesList);
        listViewBienes.setAdapter(adapter);

        // Get docente email from intent
        String email = getIntent().getStringExtra("email");

        if (email == null || email.isEmpty()) {
            Toast.makeText(this, "No se ha proporcionado el correo del docente.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query assigned bienes
        mDatabase.child("bienes").orderByChild("asignadoA").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bienesList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String codigo = snapshot.child("codigo").getValue(String.class);
                        String descripcion = snapshot.child("descripcion").getValue(String.class);
                        String tipo = snapshot.child("tipo").getValue(String.class);
                        String marca = snapshot.child("marca").getValue(String.class);

                        if (codigo == null) {
                            codigo = "Código no disponible";
                        }
                        if (descripcion == null) {
                            descripcion = "Descripción no disponible";
                        }
                        if (tipo == null) {
                            tipo = "Tipo no disponible";
                        }
                        if (marca == null) {
                            marca = "Marca no disponible";
                        }

                        bienesList.add("Código: " + codigo + "\nDescripción: " + descripcion + "\nTipo: " + tipo + "\nMarca: " + marca);
                    }
                } else {
                    bienesList.add("No hay bienes asignados para este docente.");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BienesDocenteActivity.this, "Error al obtener los bienes: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}



