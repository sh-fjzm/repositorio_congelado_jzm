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
        View view = inflater.inflate(R.layout.fragment_lista_empresas, container, false);

        recyclerView = view.findViewById(R.id.recyclerEmpresas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DAOEmpresa daoEmpresa = new DAOEmpresa(getContext());
        List<Empresa> listaEmpresas = daoEmpresa.obtenerEmpresas();

        // Imprimir los nombres de las empresas en el log para testeo
        Log.d("EmpresaLista", "Número de empresas obtenidas: " + listaEmpresas.size());
        for (Empresa e : listaEmpresas) {
            Log.d("EmpresaLista", "Empresa: " + e.getNombre());
        }

        adapter = new EmpresaAdapter(listaEmpresas, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}