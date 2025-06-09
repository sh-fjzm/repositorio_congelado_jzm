package com.zambranomainarjavier.fctzambranomainar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

/*
    Esta clase se encarga de gestional el menu lateral (Drawer) y mostrar los diferentes
    fragments que contiene la aplicacion segun lo que el usuario va seleccionando.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Contenedor principal que permite abrir/cerrar el menu lateral.
    DrawerLayout drawerLayout;
    // Contenedor que contiene el menu con sus opciones.
    NavigationView navigationView;
    // La barra superior de la app.
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga el layout principal de la actividad
        setContentView(R.layout.activity_main);
        // Vincula el toolbar definido en el layout y lo configura como ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Vincula el DrawerLayout y NavigationView del layout
        drawerLayout = findViewById(R.id.menuHeader);
        navigationView = findViewById(R.id.navigationView);
        // Establece que esta actividad maneje los clicks del menu lateral
        navigationView.setNavigationItemSelectedListener(this);

        // Se crea un ActionBarDrawerToggle para el menu de hamburguesa
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        toogle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.rojoOscuroRed));
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        // Aumentar tamaño del menu hamburguesa en la toolbar
        Drawable navIcon = getResources().getDrawable(R.drawable.menu, null);
        if (navIcon != null) {
            // Aplicamos el color
            navIcon.setColorFilter(getResources().getColor(R.color.rojoRed), PorterDuff.Mode.SRC_IN);
            toolbar.setNavigationIcon(navIcon);
        }
        // Si es la primera vez que se abre la aplicación, mostramos el fragmento de Inicio.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new Inicio()).commit();
            // Marca como seleccionado el item "Inicio" en el NavigationView
            navigationView.setCheckedItem(R.id.Inicio);
        }
        // Manejamos el comportamiento del boton de volver atras.
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Si el menú lateral está abierto
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    // Lo cierra en lugar de salir de la app
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                // Desactivamos este callback para permitir el comportamiento normal de volver atras o cerrar la app.
                    setEnabled(false);
                    MainActivity.super.onBackPressed();
                }
            }
        });
    }

    /*
        Manejamos que los botones de cada menu del drawer lleven a su fragmento correspondiente.
     */
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId()==R.id.Inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new Inicio()).commit();

        } else if (menuItem.getItemId()==R.id.Buscar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new Buscar()).commit();

        } else if (menuItem.getItemId()==R.id.Lista) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new EmpresaLista()).commit();

        } else if (menuItem.getItemId()==R.id.Gemini) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new GeminiFragment()).commit();

        } else if (menuItem.getItemId()==R.id.ObtenerTags) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new BuscarTags()).commit();

        } else if (menuItem.getItemId()==R.id.Mapa) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new MapaTags()).commit();

        } else if (menuItem.getItemId()==R.id.Salir) {
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}