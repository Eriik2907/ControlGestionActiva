package com.example.interfaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class SedesAdapter extends RecyclerView.Adapter<SedesAdapter.SedeViewHolder> {

    private final List<Map<String, Object>> sedesList;

    public SedesAdapter(List<Map<String, Object>> sedesList) {
        this.sedesList = sedesList;
    }

    @NonNull
    @Override
    public SedeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sede, parent, false);
        return new SedeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SedeViewHolder holder, int position) {
        Map<String, Object> sede = sedesList.get(position);

        String nombreSede = sede.get("nombre_sede") != null ? sede.get("nombre_sede").toString() : "N/A";
        String direccion = sede.get("direccion") != null ? sede.get("direccion").toString() : "N/A";
        String telefono = sede.get("telefono") != null ? sede.get("telefono").toString() : "N/A";
        String encargado = sede.get("encargado") != null ? sede.get("encargado").toString() : "N/A";
        String numeroAulas = sede.get("numero_aulas") != null ? sede.get("numero_aulas").toString() : "N/A";
        String numeroOficinas = sede.get("numero_oficinas") != null ? sede.get("numero_oficinas").toString() : "N/A";
        String descripcion = sede.get("descripcion") != null ? sede.get("descripcion").toString() : "N/A";
        String coordenadas = sede.get("coordenadas") != null ? sede.get("coordenadas").toString() : "N/A";

        holder.tvNombreSede.setText("Nombre: " + nombreSede);
        holder.tvDireccion.setText("Dirección: " + direccion);
        holder.tvTelefono.setText("Teléfono: " + telefono);
        holder.tvEncargado.setText("Encargado: " + encargado);
        holder.tvNumeroAulas.setText("Número de Aulas: " + numeroAulas);
        holder.tvNumeroOficinas.setText("Número de Oficinas: " + numeroOficinas);
        holder.tvDescripcion.setText("Descripción: " + descripcion);
        holder.tvCoordenadas.setText("Coordenadas: " + coordenadas);
    }

    @Override
    public int getItemCount() {
        return sedesList.size();
    }

    static class SedeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreSede, tvDireccion, tvTelefono, tvEncargado, tvNumeroAulas, tvNumeroOficinas, tvDescripcion, tvCoordenadas;

        public SedeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreSede = itemView.findViewById(R.id.tvNombreSede);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvEncargado = itemView.findViewById(R.id.tvEncargado);
            tvNumeroAulas = itemView.findViewById(R.id.tvNumeroAulas);
            tvNumeroOficinas = itemView.findViewById(R.id.tvNumeroOficinas);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvCoordenadas = itemView.findViewById(R.id.tvCoordenadas);
        }
    }
}
