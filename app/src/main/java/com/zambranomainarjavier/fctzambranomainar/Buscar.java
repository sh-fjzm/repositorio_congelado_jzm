package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.zambranomainarjavier.fctzambranomainar.api.ApiServicio;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOEmpresa;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOOferta;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;
import com.zambranomainarjavier.fctzambranomainar.modelo.Oferta;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
    Fragment utilizado en el menu Buscar Empresas.

    Contiene un boton que al accionar, se llama al metodo obtenerDatos() de la clase ApiServicio
    y se obtienen los datos de la API. Esos datos se utilizan para crear objetos Empresa y Oferta y
    almacenarlos en la base de datos.
 */
public class Buscar extends Fragment {

    // Boton para buscar ofertas
    private Button btnBuscar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        btnBuscar = view.findViewById(R.id.btnBuscarOfertas);

        btnBuscar.setOnClickListener(v -> guardarDatos());

        return view;
    }

    private void guardarDatos() {
        new Thread(() -> {
            try {
                // Obtener datos de la API
                JSONObject response = ApiServicio.obtenerDatos();
                // Si la respuesta es nula, mostramos un mensaje de error en un Toast
                if (response == null) {
                    mostrarToast("No se pudo obtener la respuesta de la API.");
                    return;
                }

                // Si la respuesta no contiene datos, mostramos un mensaje de error en otro Toast
                JSONArray resultados = response.optJSONArray("data");
                if (resultados == null) {
                    mostrarToast("El JSON no contiene datos.");
                    return;
                }

                // Objetos DAO para poder hacer los insert en la base de datos.
                DAOEmpresa daoEmpresa = new DAOEmpresa(getContext());
                DAOOferta daoOferta = new DAOOferta(getContext());

                // Recorrer los resultados y crear objetos Empresa y Oferta
                for (int i = 0; i < resultados.length(); i++) {
                    JSONObject obj = resultados.getJSONObject(i);

                    /*
                        Obtenemos los campos por el nombre en el que aparecen en el JSON y que
                        obtuvimos realizando pruebas previas para saber los datos que nos
                        interesaba conservar.
                     */
                    String organization = obj.optString("organization", null);
                    String location = obj.optString("linkedin_org_locations", null);
                    String ciudad = obj.optString("cities_derived", null);
                    String organizationUrl = obj.optString("organization_url", null);
                    String logo = obj.optString("organization_logo", null);
                    String linkedinUrl = obj.optString("linkedin_org_url", null);
                    String sector = obj.optString("linkedin_org_industry", null);
                    String specialties = obj.optString("linkedin_org_specialties", null);
                    String ofertaUrl = obj.optString("url", null);
                    String fecha = obj.optString("date_posted", null);

                    // Creamos el objeto empresa con los datos obtenidos.
                    Empresa empresa = new Empresa(
                            organization,
                            sector,
                            logo,
                            location,
                            ciudad,
                            linkedinUrl,
                            organizationUrl,
                            specialties
                    );

                    // Creamos el objeto oferta con los datos obtenidos.
                    Oferta oferta = new Oferta(ofertaUrl, null, fecha);

                    // Insertamos ambos en la base de datos.
                    daoEmpresa.insertarEmpresa(empresa);
                    daoOferta.insertarOferta(oferta);
                }

                // Mostramos un mensaje cuando se hayan guardado los datos.
                mostrarToast("Datos guardados correctamente.");
            } catch (JSONException e) {
                e.printStackTrace();
                mostrarToast("Error procesando el JSON.");
            }
        }).start();
    }

    // Metodo para mostrar un mensaje en un Toast que se ejecuta en el hilo principal.
    private void mostrarToast(String mensaje) {
        requireActivity().runOnUiThread(() ->
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show());
    }
}