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

/*
    Adaptador para un RecyclerView que muestra una lista de objetos Empresa.
    Se encarga de crear, enlazar y mostrar cada item de la lista en el RecyclerView.
*/
public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> {
    // Lista de empresas que se mostraran en el RecyclerView
    private List<Empresa> listaEmpresas;
    // Context necesario para mostrar vistas y lanzar intents
    private Context context;
    // Constructor del adaptador. Recibe la lista de empresas y el contexto
    public EmpresaAdapter(List<Empresa> listaEmpresas, Context context) {
        this.listaEmpresas = listaEmpresas;
        this.context = context;
    }
    // Crea y retorna un nuevo ViewHolder con la vista de cada item
    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crea el layout del XML que representa cada item de empresa
        View view = LayoutInflater.from(context).inflate(R.layout.item_empresa, parent, false);
        return new EmpresaViewHolder(view);
    }
    /*
        Vincula los datos del objeto Empresa con los elementos visuales del ViewHolder.
        Es el encargado de que al seleccionar una empresa se muestre una ventana con sus
        detalles.
     */
    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        // Obtiene el objeto empresa de la posicion actual
        Empresa empresa = listaEmpresas.get(position);
        // Establece el nombre y el sector en los TextViews
        holder.txtNombre.setText(empresa.getNombre());
        holder.txtSector.setText(empresa.getSector());
        // Si la empresa tiene logo, se carga con Glide; si no, se deja la imagen vacia
        if (empresa.getLogo() != null && !empresa.getLogo().isEmpty()) {
            Glide.with(context)
                    .load(empresa.getLogo())
                    .into(holder.imgLogo);
        } else {
            // Deja el ImageView en blanco
            holder.imgLogo.setImageDrawable(null);
        }
        // Al hacer click sobre el item, se abre la actividad de detalles de la empresa
        holder.itemView.setOnClickListener(v -> {
            // Crea un intent para lanzar la actividad que muestra los detalles de empresa
            Intent intent = new Intent(context, EmpresaDetalleActivity.class);
            /*
                Manda los datos de la empresa a la actividad.
                Para que esto funcione, previamente se debe implementar la clase
                Serializable en la clase Empresa para que Android sepa como empaquetar
                el objeto y mandarlo entre actividades.
             */
            intent.putExtra("empresa", empresa);
            // Inicia la actividad de empresaDetalle
            context.startActivity(intent);
        });
    }
    // Devuelve el numero total de elementos en la lista
    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }
    /*
        Clase que representa el ViewHolder. Mantiene las referencias a los
        elementos visuales (ImageView y TextViews) de cada item de la lista.
    */
    public static class EmpresaViewHolder extends RecyclerView.ViewHolder {
        // Logo de la empresa
        ImageView imgLogo;
        // Nombre y sector de la empresa
        TextView txtNombre, txtSector;

        public EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asocia los elementos visuales con sus IDs en el layout XML
            imgLogo = itemView.findViewById(R.id.imgLogoEmpresa);
            txtNombre = itemView.findViewById(R.id.txtNombreEmpresa);
            txtSector = itemView.findViewById(R.id.txtSectorEmpresa);
        }
    }
}