package com.example.interfaz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class CustodiosAdapter extends RecyclerView.Adapter<CustodiosAdapter.CustodioViewHolder> {

    private final List<Map<String, Object>> custodiosList;

    public CustodiosAdapter(List<Map<String, Object>> custodiosList) {
        this.custodiosList = custodiosList;
    }

    @NonNull
    @Override
    public CustodioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custodio, parent, false);
        return new CustodioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustodioViewHolder holder, int position) {
        Map<String, Object> custodio = custodiosList.get(position);

        holder.cedulaTextView.setText("Cedula: " + custodio.get("cedula"));
        holder.inventarioTextView.setText("Inventario: " + custodio.get("inventario"));
        holder.areaTextView.setText("Area: " + custodio.get("area"));
        holder.fechaTextView.setText("Fecha: " + custodio.get("fecha"));
        holder.actaTextView.setText("Acta: " + custodio.get("acta"));
        holder.observacionTextView.setText("Observacion: " + custodio.get("observacion"));
    }

    @Override
    public int getItemCount() {
        return custodiosList.size();
    }

    static class CustodioViewHolder extends RecyclerView.ViewHolder {

        TextView cedulaTextView, inventarioTextView, areaTextView, fechaTextView, actaTextView, observacionTextView;

        public CustodioViewHolder(@NonNull View itemView) {
            super(itemView);
            cedulaTextView = itemView.findViewById(R.id.textViewCedula);
            inventarioTextView = itemView.findViewById(R.id.textViewInventario);
            areaTextView = itemView.findViewById(R.id.textViewArea);
            fechaTextView = itemView.findViewById(R.id.textViewFecha);
            actaTextView = itemView.findViewById(R.id.textViewActa);
            observacionTextView = itemView.findViewById(R.id.textViewObservacion);
        }
    }
}
