package com.zambranomainarjavier.fctzambranomainar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;
import java.util.List;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> {

    private List<Empresa> listaEmpresas;
    private Context context;

    public EmpresaAdapter(List<Empresa> listaEmpresas, Context context) {
        this.listaEmpresas = listaEmpresas;
        this.context = context;
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_empresa, parent, false);
        return new EmpresaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        Empresa empresa = listaEmpresas.get(position);

        holder.txtNombre.setText(empresa.getNombre());
        holder.txtSector.setText(empresa.getSector());

        if (empresa.getLogo() != null && !empresa.getLogo().isEmpty()) {
            Glide.with(context)
                    .load(empresa.getLogo())
                    .into(holder.imgLogo);
        } else {
            holder.imgLogo.setImageDrawable(null); // deja el ImageView en blanco
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EmpresaDetalleActivity.class);
            intent.putExtra("empresa", empresa);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public static class EmpresaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView txtNombre, txtSector;

        public EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgLogoEmpresa);
            txtNombre = itemView.findViewById(R.id.txtNombreEmpresa);
            txtSector = itemView.findViewById(R.id.txtSectorEmpresa);
        }
    }
}
