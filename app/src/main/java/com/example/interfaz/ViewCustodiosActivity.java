package com.example.interfaz;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewCustodiosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustodiosAdapter adapter;
    private List<Map<String, Object>> custodiosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_custodios);

        recyclerView = findViewById(R.id.recyclerViewCustodios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        custodiosList = new ArrayList<>();
        adapter = new CustodiosAdapter(custodiosList);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("custodios")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        custodiosList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Map<String, Object> custodian = (Map<String, Object>) dataSnapshot.getValue();
                            custodiosList.add(custodian);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle possible errors
                    }
                });
    }
}
