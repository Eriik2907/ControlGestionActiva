package com.example.interfaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.AreaViewHolder> {

    private final List<Map<String, Object>> areasList;

    public AreasAdapter(List<Map<String, Object>> areasList) {
        this.areasList = areasList;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_area, parent, false);
        return new AreaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        Map<String, Object> area = areasList.get(position);

        String description = area.get("description") != null ? area.get("description").toString() : "N/A";
        String reference = area.get("reference") != null ? area.get("reference").toString() : "N/A";
        String numberOfAulas = area.get("number_of_aulas") != null ? area.get("number_of_aulas").toString() : "N/A";
        String capacity = area.get("capacity") != null ? area.get("capacity").toString() : "N/A";

        holder.descriptionTextView.setText("Descripción: " + description);
        holder.referenceTextView.setText("Referencia: " + reference);
        holder.numberOfAulasTextView.setText("Número de Aulas: " + numberOfAulas);
        holder.capacityTextView.setText("Capacidad: " + capacity);
    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }

    static class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView referenceTextView;
        TextView numberOfAulasTextView;
        TextView capacityTextView;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            referenceTextView = itemView.findViewById(R.id.textViewReference);
            numberOfAulasTextView = itemView.findViewById(R.id.textViewNumberOfAulas);
            capacityTextView = itemView.findViewById(R.id.textViewCapacity);
        }
    }
}
