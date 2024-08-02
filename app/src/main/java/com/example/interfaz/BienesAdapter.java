package com.example.interfaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class BienesAdapter extends RecyclerView.Adapter<BienesAdapter.BienesViewHolder> {

    private final List<Map<String, Object>> bienesList;

    public BienesAdapter(List<Map<String, Object>> bienesList) {
        this.bienesList = bienesList;
    }

    @NonNull
    @Override
    public BienesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bien, parent, false);
        return new BienesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BienesViewHolder holder, int position) {
        Map<String, Object> bien = bienesList.get(position);

        String codigo = bien.get("codigo") != null ? bien.get("codigo").toString() : "N/A";
        String tipo = bien.get("tipo") != null ? bien.get("tipo").toString() : "N/A";
        String marca = bien.get("marca") != null ? bien.get("marca").toString() : "N/A";
        String descripcion = bien.get("descripcion") != null ? bien.get("descripcion").toString() : "N/A";

        holder.codigoTextView.setText("Código: " + codigo);
        holder.tipoTextView.setText("Tipo: " + tipo);
        holder.marcaTextView.setText("Marca: " + marca);
        holder.descripcionTextView.setText("Descripción: " + descripcion);
    }

    @Override
    public int getItemCount() {
        return bienesList.size();
    }

    public static class BienesViewHolder extends RecyclerView.ViewHolder {
        TextView codigoTextView, tipoTextView, marcaTextView, descripcionTextView;

        public BienesViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.textViewCodigo);
            tipoTextView = itemView.findViewById(R.id.textViewTipo);
            marcaTextView = itemView.findViewById(R.id.textViewMarca);
            descripcionTextView = itemView.findViewById(R.id.textViewDescripcion);
        }
    }
}


