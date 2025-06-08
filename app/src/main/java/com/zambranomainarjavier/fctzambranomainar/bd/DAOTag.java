package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zambranomainarjavier.fctzambranomainar.modelo.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOTag {
    private SQLiteDatabase db;

    public DAOTag(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getReadableDatabase();
    }

    public List<Tag> obtenerTags() {
        List<Tag> lista = new ArrayList<>();
        // Consulta para obtener todos los registros de la tabla tag
        Cursor cursor = db.rawQuery("SELECT * FROM tag", null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                // Creamos objeto Tag con los datos obtenidos
                Tag t = new Tag(id, nombre);
                lista.add(t);
            } while (cursor.moveToNext());
        }
        // Cerramos cursor para liberar recursos
        cursor.close();
        return lista;
    }

    public long insertarTag(String tag) {
        ContentValues values = new ContentValues();
        values.put("nombre", tag);

        // Inserta y devuelve el ID autogenerado
        return db.insert("tag", null, values);
    }
    // Usamos HashMap para almacenar los tags y su frecuencia
    public HashMap<String, Integer> contarTags() {
        HashMap<String, Integer> frecuencias = new HashMap<>();
        // Consulta que obtiene los 10 tags mas repetidos de la tabla tags
        Cursor cursor = db.rawQuery("SELECT tag, COUNT(*) as repeticiones FROM tags GROUP BY tag ORDER BY repeticiones DESC LIMIT 10", null);
        // Recorremos los resultados y guardamos en el hashmap
        while (cursor.moveToNext()) {
            String tag = cursor.getString(0);
            int repeticiones = cursor.getInt(1);
            frecuencias.put(tag, repeticiones);
        }
        // Cerramos cursor
        cursor.close();
        // Cerramos base de datos
        db.close();
        return frecuencias;
    }



}