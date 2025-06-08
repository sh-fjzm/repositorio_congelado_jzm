package com.zambranomainarjavier.fctzambranomainar.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/*
    Conexion con la API de RapidAPI para obtener las ofertas de trabajo y poder sacar las empresas
 */
public class ApiServicio {
    /*
        Datos para conectar con la API de RapidAPI que nos proporciona ofertas de empleo de
        LinkedIn de las que tomamos los datos para crear objetos empresa y oferta.
     */
    // Hay que modificar este valor si se usan otros parametros desde la web de la API (ahora, limit = 10 y description_type = text)
    private static final String API_URL = "https://linkedin-job-search-api.p.rapidapi.com/active-jb-7d?limit=30&offset=0&location_filter=%22Zaragoza%22%20OR%20%22ZARAGOZA%22%20OR%20%22zaragoza%22&description_type=text";
    // Hay que modificar este valor si se crea una nueva cuenta para usar la API
    private static final String API_KEY = "447dd14821msh1c608d8eeda3010p13ff07jsn42994a12f191";
    // No hace falta modificar este valor
    private static final String API_HOST = "linkedin-job-search-api.p.rapidapi.com";

    public static JSONArray obtenerDatos() throws JSONException {
        // Se crea una instancia del cliente HTTP
        OkHttpClient client = new OkHttpClient();
        // Se construye la solicitud HTTP con metodo GET y se agregan los encabezados necesarios
        Request request = new Request.Builder()
                .url(API_URL) // URL del API al que se hace la solicitud
                .get() // Metodo GET
                .addHeader("x-rapidapi-key", API_KEY) // Clave de acceso a la API
                .addHeader("x-rapidapi-host", API_HOST) // Host de la API
                .build(); // Construye la solicitud
        // Se crea una llamada HTTP a partir del cliente y la solicitud
        Call call = client.newCall(request);
        // Se ejecuta la llamada y se maneja la respuesta
        try (Response response = call.execute()) {
            // Si la respuesta es exitosa, se obtiene el cuerpo de la respuesta como cadena
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                // Se convierte la cadena en un JSONArray
                return new JSONArray(responseData);
            } else {
                System.out.println("Error en la respuesta: " + response.code());
            }
        } catch (IOException e) {
            // En caso de error al ejecutar la solicitud, se imprime el error
            System.out.println("Error al realizar la peticion:");
            e.printStackTrace();
        }
        // Si ocurre un error o la respuesta no es exitosa, se retorna null
        return null;
    }

    /*
        Metodo para realizar la busqueda de ofertas con parametros introducidos
        por el usuario. Se ha cambiado la URL de la API para introducir los
        parametros en la parte indicada para ello.
     */
    public static JSONArray obtenerDatosConParametros(String parametro) throws JSONException {
        // Se crea una instancia del cliente HTTP
        OkHttpClient client = new OkHttpClient();

        try {
            // Se eliminan las comillas del parametro y se codifica para ser usado en una URL
            String filtroEscapado = URLEncoder.encode(parametro.replace("\"", ""), StandardCharsets.UTF_8.toString());
            String url = API_URL
                    + "?limit=10" // Limita la cantidad de resultados a 10
                    + "&offset=0" // Empieza desde el primer resultado
                    + "&location_filter=%22Zaragoza%22%20OR%20%22ZARAGOZA%22%20OR%20%22zaragoza%22" // Filtra por ubicacion
                    + "&description_filter=" + filtroEscapado // Agrega el parametro de busqueda
                    + "&description_type=text"; // Se obtiene el texto de la oferta de empleo
            // Se construye la solicitud HTTP con metodo GET y encabezados necesarios
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-key", API_KEY)
                    .addHeader("x-rapidapi-host", API_HOST)
                    .build();
            // Se crea y ejecuta la llamada HTTP
            Call call = client.newCall(request);

            try (Response response = call.execute()) {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    return new JSONArray(responseData);
                } else {
                    System.out.println("Error en la respuesta: " + response.code());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la peticion:");
            e.printStackTrace();
        }
        return null;
    }
}