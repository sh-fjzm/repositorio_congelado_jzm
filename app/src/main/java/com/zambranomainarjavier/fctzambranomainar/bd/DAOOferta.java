package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zambranomainarjavier.fctzambranomainar.modelo.Oferta;

public class DAOOferta {
    private SQLiteDatabase db;

    public DAOOferta(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getWritableDatabase();
    }

    public long insertarOferta(Oferta oferta) {
        if (obtenerPorUrlYFecha(oferta.getUrl(), oferta.getFecha())) {
            // Ya existe la oferta, no la insertamos
            return -1;
        }

        ContentValues values = new ContentValues();
        values.put("url", oferta.getUrl());
        values.put("descripcion", oferta.getDescripcion());
        values.put("fecha", oferta.getFecha());

        return db.insert("oferta", null, values);
    }

    public boolean obtenerPorUrlYFecha(String url, String fecha) {
        Cursor cursor = db.rawQuery("SELECT id FROM oferta WHERE url = ? AND fecha = ?", new String[]{url, fecha});
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    /*
        Metodo utilizado para obtener la informacion almacenada en el campo descripcion
        de la tabla oferta. Este campo se va a utilizar para obtener las palabras clave
        (tags) que se utilizaran para mostrar una lista o un mapa.
     */
    public Cursor obtenerDescripciones() {
        return db.rawQuery("SELECT descripcion FROM oferta", null);
    }

}
