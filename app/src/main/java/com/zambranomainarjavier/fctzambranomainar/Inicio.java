package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
    Esta clase se encarga de mostrar la pantalla de bienvenida cuando se
    inicia la aplicación (fragment_inicio.xml). Solo muestra el diseño.
 */
public class Inicio extends Fragment {

    public Inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }
}