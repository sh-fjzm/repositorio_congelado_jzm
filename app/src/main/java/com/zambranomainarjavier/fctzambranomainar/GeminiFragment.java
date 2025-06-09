package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;

/*
    Clase que implementa el fragment encargado de mostrar la interfaz que contiene Gemini.
    Cuando el usuario escribe un prompt y lo ejecuta, se envia el mensaje a la API de Gemini
    y se muestra el resultado en la pantalla.
 */
public class GeminiFragment extends Fragment {

    private TextInputEditText promptEditText;
    private Button sendPromptButton;
    private ProgressBar progressBar;
    private TextView responseTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gemini, container, false);

        promptEditText = view.findViewById(R.id.promptEditText);
        sendPromptButton = view.findViewById(R.id.sendPromptButton);
        progressBar = view.findViewById(R.id.progressBar);
        responseTextView = view.findViewById(R.id.responseTextView);
        /*
            Listener del boton que ejecuta el prompt.
            Se obtiene el texto introducido por el usuario. Si el texto esta vacio,
            se muestra un mensaje. Si hay texto, se muestra un ProgressBar y
            limpiamos el campo con la respuesta anterior.
         */
        sendPromptButton.setOnClickListener(v -> {
            String prompt = promptEditText.getText().toString().trim();
            if (prompt.isEmpty()) {
                responseTextView.setText("Introduce un prompt.");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            responseTextView.setText("");

            Gemini gemini = new Gemini();
            gemini.sendPrompt(prompt, new Gemini.GeminiCallback() {
                @Override
                public void onSuccess(String respuesta) {
                    responseTextView.setText(respuesta);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(String error) {
                    responseTextView.setText(error);
                    progressBar.setVisibility(View.GONE);
                }
            });
        });
        return view;
    }
}