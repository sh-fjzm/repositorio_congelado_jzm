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

public class MapaTags extends Fragment {

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
        List<Tag> tags = daoTag.obtenerTags();

        chipGroupTags.removeAllViews();

        for (Tag tag : tags) {
            Chip chip = new Chip(getContext());
            chip.setText(tag.getNombre());

            chip.setClickable(false);
            chip.setCheckable(false);

            chipGroupTags.addView(chip);
        }
    }
}
