package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zambranomainarjavier.fctzambranomainar.modelo.Tag;
import java.util.ArrayList;
import java.util.List;

public class DAOTag {
    private SQLiteDatabase db;

    public DAOTag(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getReadableDatabase();
    }

    public List<Tag> obtenerTags() {
        List<Tag> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tag", null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getInt(0);
                String nombre = cursor.getString(1);

                Tag t = new Tag(id, nombre);
                lista.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}