package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.zambranomainarjavier.fctzambranomainar.api.ApiServicio;
import com.zambranomainarjavier.fctzambranomainar.controlador.ProcesarDatosApi;
import org.json.JSONArray;
import org.json.JSONException;

/*
    Fragment utilizado en el menu Buscar Empresas.

    Contiene un boton que al accionar, se llama al metodo obtenerDatos() de la clase ApiServicio
    y se obtienen los datos de la API. Esos datos se utilizan para crear objetos Empresa y Oferta y
    almacenarlos en la base de datos.
 */
public class Buscar extends Fragment {

    private Button btnBuscar;
    private TextView textoExplicativoFiltro;
    private EditText editTextFiltro;
    private Button btnBuscarConFiltro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        btnBuscar = view.findViewById(R.id.btnBuscarOfertas);

        textoExplicativoFiltro = view.findViewById(R.id.textoExplicativoFiltro);
        editTextFiltro = view.findViewById(R.id.editTextFiltro);
        btnBuscarConFiltro = view.findViewById(R.id.btnBuscarConFiltro);

        btnBuscar.setOnClickListener(v -> guardarDatos());

        btnBuscarConFiltro.setOnClickListener(v -> {
            String filtro = editTextFiltro.getText().toString().trim();
            if (filtro.isEmpty()) {
                mostrarToast("Por favor, introduce un filtro de búsqueda.");
            } else {
                guardarDatosConParametros(filtro);
            }
        });

        return view;
    }

    private void guardarDatos() {
        new Thread(() -> {
            try {
                JSONArray response = ApiServicio.obtenerDatos();
                if (response == null) {
                    mostrarToast("No se pudo obtener la respuesta de la API.");
                    return;
                }
                new ProcesarDatosApi(getContext()).procesar(response);
                mostrarToast("Datos guardados correctamente.");
            } catch (JSONException e) {
                e.printStackTrace();
                mostrarToast("Error procesando el JSON.");
            }
        }).start();
    }

    private void guardarDatosConParametros(String parametro) {
        new Thread(() -> {
            try {
                JSONArray response = ApiServicio.obtenerDatosConParametros(parametro);
                if (response == null) {
                    mostrarToast("No se pudo obtener la respuesta de la API con parametros.");
                    return;
                }
                new ProcesarDatosApi(getContext()).procesar(response);
                mostrarToast("Datos guardados correctamente con parametros.");
            } catch (JSONException e) {
                e.printStackTrace();
                mostrarToast("Error procesando el JSON con parametros.");
            }
        }).start();
    }

    private void mostrarToast(String mensaje) {
        requireActivity().runOnUiThread(() ->
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show());
    }
}