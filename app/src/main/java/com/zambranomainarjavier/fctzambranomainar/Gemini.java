package com.zambranomainarjavier.fctzambranomainar;

import android.os.Handler;
import android.os.Looper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

/*
    Clase que implementa el envio de solicitudes a la API de Gemini usando OkHttp, una libreria
    para hacer peticiones HTTP en Android.
    Enviamos un prompt al modelo Gemini de Google y recibimos una respuesta generada por la IA.
 */
public class Gemini {
    // Clave de API de Gemini almacenada de forma segura
    private static final String API_KEY = BuildConfig.GEMINI_API_KEY;
    // URL del endpoint de la API de Gemini
    private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    private final OkHttpClient client;
    private final Handler mainHandler;
    /*
        Se crea el cliente HTTP para enviar las solicitudes
        El handler se usa para ejecutar el codigo de respuesta en el hilo principal de Android
        Esto es necesario para actualizar la UI.
     */
    public Gemini() {
        client = new OkHttpClient();
        mainHandler = new Handler(Looper.getMainLooper());
    }
    // Permite recibir la respuesta o un mensaje de error de forma asincrona
    public interface GeminiCallback {
        void onSuccess(String respuesta);
        void onError(String error);
    }

    /*
        Contruimos un JSON con el mensaje (prompt) que el usuario ha introducido.
        Enviamos una solicitud POST a la API usando OkHttp.
        Esperamos la respuesta de la API y, si hay una respuesta valida, la extraemos
        y devolvemos el texto generado.
        Si hay un error, se devuelve mediante un callback.
     */
    public void sendPrompt(String prompt, GeminiCallback callback) {
        try {
            JSONObject jsonBody = new JSONObject();

            JSONArray contents = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "user");

            JSONArray parts = new JSONArray();
            parts.put(new JSONObject().put("text", prompt));
            message.put("parts", parts);

            contents.put(message);

            jsonBody.put("contents", contents);

            RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(URL + "?key=" + API_KEY)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mainHandler.post(() -> callback.onError("Error de red: " + e.getMessage()));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        mainHandler.post(() -> callback.onError("Error HTTP: " + response.code()));
                        return;
                    }
                    String respStr = response.body().string();

                    try {
                        JSONObject jsonResponse = new JSONObject(respStr);
                        JSONArray candidates = jsonResponse.getJSONArray("candidates");
                        if (candidates.length() > 0) {
                            JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
                            JSONArray parts = content.getJSONArray("parts");
                            String textoGenerado = parts.getJSONObject(0).getString("text");

                            mainHandler.post(() -> callback.onSuccess(textoGenerado));
                        } else {
                            mainHandler.post(() -> callback.onError("No se recibió respuesta."));
                        }
                    } catch (Exception e) {
                        mainHandler.post(() -> callback.onError("Error parseando respuesta: " + e.getMessage()));
                    }
                }
            });
        } catch (Exception e) {
            callback.onError("Error creando solicitud: " + e.getMessage());
        }
    }
}