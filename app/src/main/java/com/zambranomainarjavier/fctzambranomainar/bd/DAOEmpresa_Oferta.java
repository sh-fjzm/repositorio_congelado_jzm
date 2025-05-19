package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAOEmpresa_Oferta {
    private SQLiteDatabase db;

    public DAOEmpresa_Oferta(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getWritableDatabase();
    }

    public void insertarRelacion(int empresaId, int ofertaId) {
        ContentValues values = new ContentValues();
        values.put("empresa_id", empresaId);
        values.put("oferta_id", ofertaId);

        db.insert("empresa_oferta", null, values);
    }
}