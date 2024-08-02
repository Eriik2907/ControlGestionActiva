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
import java.util.List;
import java.util.Map;

public class AreasRegistradasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAreas;
    private AreasAdapter areasAdapter;
    private List<Map<String, Object>> areasList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_registradas);

        recyclerViewAreas = findViewById(R.id.recyclerViewAreas);
        recyclerViewAreas.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("areas");

        // Initialize list and adapter
        areasList = new ArrayList<>();
        areasAdapter = new AreasAdapter(areasList);
        recyclerViewAreas.setAdapter(areasAdapter);

        // Load data from Firebase
        loadAreasFromFirebase();
    }

    private void loadAreasFromFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                areasList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> area = (Map<String, Object>) snapshot.getValue();
                    areasList.add(area);
                }
                areasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AreasRegistradasActivity.this, "Error al cargar los datos: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
