package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;

/*
    Clase para mostrar los detalles ampliados de una empresa despues de mostrar todas las
    empresas disponibles con EmpresaLista.
 */
public class EmpresaDetalleActivity extends AppCompatActivity {
    // Declaracion de los componentes de la interfaz
    private ImageView imgLogo;
    private TextView txtNombre, txtSector, txtDireccion, txtCiudad, txtLinkedIn, txtWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos el layout que se va a mostrar en esta actividad
        setContentView(R.layout.activity_empresa_detalle);
        // Asociamos cada variable con su componente en el activity_empresa_detalle.xml
        imgLogo = findViewById(R.id.imgLogoDetalle);
        txtNombre = findViewById(R.id.txtNombreDetalle);
        txtSector = findViewById(R.id.txtSectorDetalle);
        txtDireccion = findViewById(R.id.txtDireccionDetalle);
        txtCiudad = findViewById(R.id.txtCiudadDetalle);
        txtLinkedIn = findViewById(R.id.txtLinkedInDetalle);
        txtWeb = findViewById(R.id.txtWebDetalle);
        // Recupera el objeto Empresa enviado desde otra actividad
        Empresa empresa = (Empresa) getIntent().getSerializableExtra("empresa");
        // Si se recibe correctamente una empresa (no esta vacia)
        if (empresa != null) {
            // Se asigna los valores de la empresa a los campos de texto
            txtNombre.setText(empresa.getNombre());
            txtSector.setText(empresa.getSector());
            txtDireccion.setText(limpiarCampo(empresa.getDireccion()));
            txtCiudad.setText(limpiarCampo(empresa.getCiudad()));
            txtLinkedIn.setText(empresa.getLinkedinUrl());
            txtWeb.setText(empresa.getWeb());
            String logoUrl = empresa.getLogo();

            // Carga el logo desde la URL usando Glide
            if (logoUrl != null && !logoUrl.isEmpty()) {
                Glide.with(this)
                        .load(logoUrl)
                        .into(imgLogo);
            } else {
                // Si no hay url de logo, se deja el ImageView vacio
                imgLogo.setImageDrawable(null);
            }
        }
        // Accion del boton "Volver" que cierra la activity y vuelve atras
        findViewById(R.id.btnVolver).setOnClickListener(v -> finish());
    }

    // Metodo para eliminar corchetes y comillas que aparecen delante de algunos campos.
    private String limpiarCampo(String campo) {
        if (campo != null && campo.startsWith("[\"") && campo.endsWith("\"]")) {
            return campo.substring(2, campo.length() - 2);
        }
        return campo;
    }
}