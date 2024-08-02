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

public class BienesRegistradosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBienes;
    private BienesAdapter bienesAdapter;
    private List<Map<String, Object>> bienesList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienes_registrados);

        recyclerViewBienes = findViewById(R.id.recyclerViewBienes);
        recyclerViewBienes.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("bienes");

        // Initialize list
        bienesList = new ArrayList<>();
        bienesAdapter = new BienesAdapter(bienesList);
        recyclerViewBienes.setAdapter(bienesAdapter);

        // Load data from Firebase
        loadBienesFromFirebase();
    }

    private void loadBienesFromFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bienesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> bien = (Map<String, Object>) snapshot.getValue();
                    if (bien != null) {
                        bienesList.add(bien);
                    }
                }
                bienesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BienesRegistradosActivity.this, "Error al cargar los datos: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
