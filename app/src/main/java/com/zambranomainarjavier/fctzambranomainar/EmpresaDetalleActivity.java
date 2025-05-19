package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;

public class EmpresaDetalleActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private TextView txtNombre, txtSector, txtDireccion, txtCiudad, txtLinkedIn, txtWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_detalle);

        imgLogo = findViewById(R.id.imgLogoDetalle);
        txtNombre = findViewById(R.id.txtNombreDetalle);
        txtSector = findViewById(R.id.txtSectorDetalle);
        txtDireccion = findViewById(R.id.txtDireccionDetalle);
        txtCiudad = findViewById(R.id.txtCiudadDetalle);
        txtLinkedIn = findViewById(R.id.txtLinkedInDetalle);
        txtWeb = findViewById(R.id.txtWebDetalle);

        Empresa empresa = (Empresa) getIntent().getSerializableExtra("empresa");

        if (empresa != null) {
            txtNombre.setText(empresa.getNombre());
            txtSector.setText(empresa.getSector());
            txtDireccion.setText(empresa.getDireccion());
            txtCiudad.setText(empresa.getCiudad());
            txtLinkedIn.setText(empresa.getLinkedinUrl());
            txtWeb.setText(empresa.getWeb());

            String logoUrl = empresa.getEmpresa_logo();
            if (logoUrl != null && !logoUrl.isEmpty()) {
                Glide.with(this)
                        .load(logoUrl)
                        .into(imgLogo);
            } else {
                imgLogo.setImageDrawable(null); // Si no hay url de logo, se deja el ImageView vacio
            }
        }
    }
}