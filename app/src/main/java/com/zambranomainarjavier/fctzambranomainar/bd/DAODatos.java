package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAODatos {

    private BBDDSQLite bd;

    public DAODatos(Context context) {
        bd = new BBDDSQLite(context);
    }

    public Cursor obtenerDescripciones() {
        SQLiteDatabase db = bd.getReadableDatabase();
        return db.rawQuery("SELECT descripcion FROM datos", null);
    }
}