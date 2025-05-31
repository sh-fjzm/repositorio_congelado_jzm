package com.zambranomainarjavier.fctzambranomainar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.menuHeader);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new Inicio()).commit();
            navigationView.setCheckedItem(R.id.Inicio);
        }
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}