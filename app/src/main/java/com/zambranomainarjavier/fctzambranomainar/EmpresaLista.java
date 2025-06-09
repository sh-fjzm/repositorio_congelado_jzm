package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOEmpresa;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;
import java.util.List;

public class EmpresaLista extends Fragment {

    private RecyclerView recyclerView;
    private EmpresaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Se convierte el layout del XML que representa el fragmento en una vista con objetos reales en la interfaz
        View view = inflater.inflate(R.layout.fragment_lista_empresas, container, false);

        recyclerView = view.findViewById(R.id.recyclerEmpresas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Crea una instancia del DAO para acceder a los datos de las empresas
        DAOEmpresa daoEmpresa = new DAOEmpresa(getContext());
        // Obtiene la lista de empresas desde la base de datos
        List<Empresa> listaEmpresas = daoEmpresa.obtenerEmpresas();

        // Imprimir los nombres de las empresas en el log para testeo
        Log.d("EmpresaLista", "Número de empresas obtenidas: " + listaEmpresas.size());
        for (Empresa e : listaEmpresas) {
            Log.d("EmpresaLista", "Empresa: " + e.getNombre());
        }
        // Crea un adaptador personalizado con la lista de empresas y lo asigna al RecyclerView
        adapter = new EmpresaAdapter(listaEmpresas, getContext());
        recyclerView.setAdapter(adapter);
        // Se devuelve la vista creada
        return view;
    }
}