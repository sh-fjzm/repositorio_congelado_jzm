package com.zambranomainarjavier.fctzambranomainar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOTag;
import com.zambranomainarjavier.fctzambranomainar.modelo.Tag;
import java.util.List;

/*
    Fragment que se encarga de mostrar la lista de tags como Chips dentro
    de un ChipGroup usando los datos obtenidos de la tabla Tags.
    Cargamos la interfaz del XML. Al pulsar el boton, obtenemos los tags de
    la tabla Tags, se crea un Chip por cada etiqueta de la tabla y se añade
    al ChipGroup.
 */
public class MapaTags extends Fragment {
    // Contenedor donde se van a mostrar las etiquetas.
    private ChipGroup chipGroupTags;
    private DAOTag daoTag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mapa_tags, container, false);

        chipGroupTags = root.findViewById(R.id.chipGroupTags);

        daoTag = new DAOTag(getContext());

        root.findViewById(R.id.btnMostrarTags).setOnClickListener(v -> mostrarTags());

        return root;
    }

    private void mostrarTags() {
        // Se obtienen todos los tags desde la base de datos.
        List<Tag> tags = daoTag.obtenerTags();
        // Se limpia el ChipGroup para evitar duplicados.
        chipGroupTags.removeAllViews();
        // Para cada etiqueta, creamos un chip visual
        for (Tag tag : tags) {
            Chip chip = new Chip(getContext());
            chip.setText(tag.getNombre());
            // Deshabilitamos el comportamiento clickable del chip.
            chip.setClickable(false);
            chip.setCheckable(false);

            chipGroupTags.addView(chip);
        }
    }
}