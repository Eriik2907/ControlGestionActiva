package com.example.interfaz;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SedesRegistradasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSedes;
    private SedesAdapter sedesAdapter;
    private List<Map<String, Object>> sedesList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes_registradas);

        recyclerViewSedes = findViewById(R.id.recyclerViewSedes);
        recyclerViewSedes.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("sedes");

        // Initialize list
        sedesList = new ArrayList<>();
        sedesAdapter = new SedesAdapter(sedesList);
        recyclerViewSedes.setAdapter(sedesAdapter);

        // Load data from Firebase
        loadSedesFromFirebase();
    }

    private void loadSedesFromFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sedesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> sede = (Map<String, Object>) snapshot.getValue();
                    sedesList.add(sede);
                }
                sedesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SedesRegistradasActivity.this, "Error al cargar los datos: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
