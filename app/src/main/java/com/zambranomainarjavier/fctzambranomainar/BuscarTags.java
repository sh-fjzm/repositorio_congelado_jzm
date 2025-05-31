package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.zambranomainarjavier.fctzambranomainar.bd.DAODatos;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOEmpresa;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOOferta;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOTag;
import android.database.Cursor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class BuscarTags extends Fragment {

    private Button btnGenerarTags;

    public BuscarTags() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buscar_tags, container, false);

        btnGenerarTags = root.findViewById(R.id.btnGenerarTags);

        // Modificar el metodo si queremos probar otros datos.
        btnGenerarTags.setOnClickListener(v -> new Thread(() -> procesarDescripcionesOferta()).start());

        return root;
    }

    private void procesarDescripciones() {
        DAODatos daoDatos = new DAODatos(getContext());
        DAOTag daoTag = new DAOTag(getContext());

        try {
            Cursor cursor = daoDatos.obtenerDescripciones();

            while (cursor.moveToNext()) {
                String descripcion = cursor.getString(0);  // Solo descripcion

                ArrayList<String> tags = obtenerTagsConScriptPython(descripcion);

                for (String tag : tags) {
                    daoTag.insertarTag(tag.trim());
                }
            }
            cursor.close();

            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "¡Tags generados correctamente!", Toast.LENGTH_LONG).show()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error generando tags: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }
    }

    /*
        Se establece una conexion HTTP tipo POST con la URL del servidor en Replit que
        contiene el script de Python encargado de obtener las palabras clave.

        Se le envia la descripcion de la tabla datos, que es el texto de ofertas de trabajo
        para ser analizadas. Con ella, se forma un JSON que se envia al servidor y se espera
        la respuesta, que será una lista con todas las palabras clave obtenidas.
     */
    private ArrayList<String> obtenerTagsConScriptPython(String descripcion) {
        ArrayList<String> resultado = new ArrayList<>();
        try {
            URL url = new URL("https://dce4378b-1b75-4ff5-9e56-70e4251c83a1-00-3olov5vck55pt.kirk.replit.dev/procesar_texto"); // URL del servidor que contiene el script de python
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"texto\": \"" + descripcion.replace("\"", "\\\"") + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                String resp = response.toString();
                resp = resp.replace("[", "").replace("]", "").replace("\"", "");
                if (!resp.isEmpty()) {
                    resultado.addAll(Arrays.asList(resp.split(",")));
                }
            }
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    private void procesarDescripcionesOferta() {
        DAOOferta daoOferta = new DAOOferta(getContext());
        DAOTag daoTag = new DAOTag(getContext());

        try {
            Cursor cursor = daoOferta.obtenerDescripciones();

            while (cursor.moveToNext()) {
                String descripcion = cursor.getString(0);  // Campo descripcion

                ArrayList<String> tags = obtenerTagsConScriptPython(descripcion);

                for (String tag : tags) {
                    daoTag.insertarTag(tag.trim());
                }
            }
            cursor.close();

            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "¡Tags generados desde OFERTA!", Toast.LENGTH_LONG).show()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error en oferta: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }
    }

    private void procesarDatosEmpresa() {
        DAOEmpresa daoEmpresa = new DAOEmpresa(getContext());
        DAOTag daoTag = new DAOTag(getContext());

        try {
            Cursor cursor = daoEmpresa.obtenerDatosEspecialidades();

            while (cursor.moveToNext()) {
                String datos = cursor.getString(0);

                ArrayList<String> tags = obtenerTagsConScriptPython(datos);

                for (String tag : tags) {
                    daoTag.insertarTag(tag.trim());
                }
            }
            cursor.close();

            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "¡Tags generados desde EMPRESA!", Toast.LENGTH_LONG).show()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error en empresa: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }
    }

}