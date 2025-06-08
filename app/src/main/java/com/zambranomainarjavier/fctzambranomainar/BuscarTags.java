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

/*
    Fragmento encargado de generar etiquetas (tags) a partir de la informacion
    contenida en las descripciones de ofertas, campo datos de empresa u otras fuentes.
    Utiliza un script Python que hemos almacenado en un servidor externo
    para procesar los textos y obtener las palabras clave.
 */
public class BuscarTags extends Fragment {
    // Boton que dispara la generacion de tags
    private Button btnGenerarTags;

    public BuscarTags() {
        // Constructor vacío obligatorio para fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Crear el layout del fragmento
        View root = inflater.inflate(R.layout.fragment_buscar_tags, container, false);

        // Asocia el boton con su ID en el layout
        btnGenerarTags = root.findViewById(R.id.btnGenerarTags);
        // Al pulsar el boton, se lanza un nuevo hilo para procesar descripciones de ofertas
        // Si queremos procesar los datos de otro campo, tenemos que modificar la llamada del metodo
        btnGenerarTags.setOnClickListener(v -> new Thread(() -> procesarDescripcionesOferta()).start());
        // Devuelve la vista generada
        return root;
    }
    /*
        Metodo para procesar descripciones desde la tabla datos. Este metodo se ha usado
        cuando se ha realizado el testeo de la aplicacion.
        No se utiliza en la version final.
     */
    private void procesarDescripciones() {
        DAODatos daoDatos = new DAODatos(getContext());
        DAOTag daoTag = new DAOTag(getContext());
        try {
            // Obtiene las descripciones almacenadas
            Cursor cursor = daoDatos.obtenerDescripciones();
            // Recorre cada fila del cursor
            while (cursor.moveToNext()) {
                // Obtiene la descripcion
                String descripcion = cursor.getString(0);
                // Envia la descripcion al script Python y obtiene una lista de tags
                ArrayList<String> tags = obtenerTagsConScriptPython(descripcion);
                // Inserta cada tag en la base de datos
                for (String tag : tags) {
                    daoTag.insertarTag(tag.trim());
                }
            }
            cursor.close();
            // Muestra mensaje de exito en la interfaz
            if (getActivity() != null) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "¡Tags generados correctamente!", Toast.LENGTH_LONG).show()
                );
            }
        } catch (Exception e) {
            // Muestra mensaje de error
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
            // URL del servidor con el script de Python
            URL url = new URL("https://dce4378b-1b75-4ff5-9e56-70e4251c83a1-00-3olov5vck55pt.kirk.replit.dev/procesar_texto"); // URL del servidor que contiene el script de python
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            // Prepara el JSON con la descripcion
            String jsonInputString = "{\"texto\": \"" + descripcion.replace("\"", "\\\"") + "\"}";
            // Envia el JSON al servidor
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Lee la respuesta del servidor
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Limpia el formato y convierte a lista de tags
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
    /*
        Metodo que procesa las descripciones almacenadas en la tabla oferta.
        Este es el metodo utilizado en la version final.
     */
    private void procesarDescripcionesOferta() {
        DAOOferta daoOferta = new DAOOferta(getContext());
        DAOTag daoTag = new DAOTag(getContext());
        try {
            // Obtiene descripciones de ofertas
            Cursor cursor = daoOferta.obtenerDescripciones();

            while (cursor.moveToNext()) {
                // Extrae la descripcion
                String descripcion = cursor.getString(0);
                // Obtiene los tags desde el servidor
                ArrayList<String> tags = obtenerTagsConScriptPython(descripcion);
                // Inserta los tags en la base de datos
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
    // Metodo que procesa la informacion almacenada en el campo datos de empresas
    // No lo utilizamos en la version final.
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