package com.zambranomainarjavier.fctzambranomainar.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/*
    Conexion con la API de RapidAPI para obtener las ofertas de trabajo y poder sacar las empresas
 */
public class ApiServicio {
    // Datos que vienen en la web de la API
    private static final String API_URL = "https://linkedin-job-search-api.p.rapidapi.com/active-jb-7d?limit=10&offset=0&title_filter=%22Data%20Engineer%22&location_filter=%22Zaragoza%22%20OR%20%22zaragoza%22%20OR%20%22ZARAGOZA%22&description_type='text'";
    private static final String API_KEY = "9cdcdf5b70msh861ba844d2f3889p1208aejsn9ff6ffc3bd4a";
    private static final String API_HOST = "linkedin-job-search-api.p.rapidapi.com";

    public static JSONObject obtenerDatos() throws JSONException {
        // Instancia para hacer las peticiones con OkHttp
        OkHttpClient client = new OkHttpClient();

        // Petición GET a la API
        Request request = new Request.Builder()
                .url(API_URL)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", API_HOST)
                .build();

        Call call = client.newCall(request);

        /*
            Se hace la peticion usando OkHttp y si la respuesta es exitosa,
            la guardamos en un JSONObject y lo devolvemos

            Si falla, se saca un mensaje de error por consola y se devuelve null.
         */
        try (Response response = call.execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return new JSONObject(responseData);
            } else {
                System.out.println("Error en la respuesta: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la petición:");
            e.printStackTrace();
        }

        return null;
    }
}