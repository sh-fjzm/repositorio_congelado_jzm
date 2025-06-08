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
    // Boton para hacer la busqueda general
    private Button btnBuscar;
    // Texto que muestra la explicacion
    private TextView textoExplicativoParametro;
    // Campo de texto en el que se introduce el parametro de busqueda
    private EditText editTextParametro;
    // Boton para hacer la busqueda con filtro
    private Button btnBuscarConParametro;

    /*
        Crea y devuelve la vista asociada al fragment. Construye el diseño del
        fragment_buscar.xml (constructor de la interfaz).
        ViewGroup es el contenedor padre en el que se coloca el fragment.
        Usamos un bundle que contiene los datos guardados anteriormente y se usa
        para restaurar el estado del fragment.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /*
            Inflater es una instancia de la clase LayoutInflater que usamos para convertir
            el diseño XML en un objeto View en tiempo de ejecucion y pueda mostrarse en pantalla.
            inflate() crea la jerarquía de las vistas y devuelve un objeto de tipo vista.

         */
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        // Enlaza los elementos visuales con sus identificadores en el layout
        btnBuscar = view.findViewById(R.id.btnBuscarOfertas);
        textoExplicativoParametro = view.findViewById(R.id.textoExplicativoFiltro);
        editTextParametro = view.findViewById(R.id.editTextFiltro);
        btnBuscarConParametro = view.findViewById(R.id.btnBuscarConFiltro);

        // Define el comportamiento al pulsar el boton de busqueda sin filtro
        btnBuscar.setOnClickListener(v -> guardarDatos());
        // Define el comportamiento al pulsar el boton de busqueda con parametro
        btnBuscarConParametro.setOnClickListener(v -> {
            // Obtiene el texto ingresado por el usuario y elimina espacios en blanco
            String parametro = editTextParametro.getText().toString().trim();
            // Verifica si el campo esta vacio
            if (parametro.isEmpty()) {
                // Muestra un mensaje indicando que se debe ingresar un parametro
                mostrarToast("Por favor, introduce un parametro de búsqueda.");
            } else {
                // Llama al metodo para guardar datos usando el parametro introducido
                guardarDatosConParametros(parametro);
            }
        });
        // Devuelve la vista creada
        return view;
    }
    /*
        Metodo que realiza una solicitud a la API sin filtros y guarda los datos obtenidos.
        Se ejecuta en un hilo secundario para evitar bloquear la interfaz.
     */
    private void guardarDatos() {
        new Thread(() -> {
            try {
                // Solicita los datos de la API sin parametros
                JSONArray response = ApiServicio.obtenerDatos();
                // Verifica si la respuesta es nula
                if (response == null) {
                    mostrarToast("No se pudo obtener la respuesta de la API.");
                    return;
                }
                // Procesa los datos obtenidos y los guarda en la base de datos
                new ProcesarDatosApi(getContext()).procesar(response);
                // Muestra un mensaje de exito
                mostrarToast("Datos guardados correctamente.");
            } catch (JSONException e) {
                // Muestra un mensaje en caso de error al procesar el JSON
                e.printStackTrace();
                mostrarToast("Error procesando el JSON.");
            }
        }).start();
        // Iniciamos la ejecución del hilo secundario
    }
    /*
        Metodo que realiza una solicitud a la API utilizando un parametro de busqueda.
        Procesa y guarda los datos obtenidos, ejecutandose en un hilo secundario.
     */
    private void guardarDatosConParametros(String parametro) {
        new Thread(() -> {
            try {
                // Solicita los datos de la API usando el parametro y los almacena en un array de JSON
                JSONArray response = ApiServicio.obtenerDatosConParametros(parametro);
                if (response == null) {
                    mostrarToast("No se pudo obtener la respuesta de la API con parametros.");
                    return;
                }
                // Se procesan y guardan los datos
                new ProcesarDatosApi(getContext()).procesar(response);
                mostrarToast("Datos guardados correctamente con parametros.");
            } catch (JSONException e) {
                e.printStackTrace();
                mostrarToast("Error procesando el JSON con parametros.");
            }
        }).start();
    }
    /*
       Metodo que muestra un mensaje emergente (Toast) en la interfaz del usuario.
       Se asegura de que el Toast se ejecute en el hilo principal.
    */
    private void mostrarToast(String mensaje) {
        requireActivity().runOnUiThread(() ->
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show());
    }
}