package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa_Tag;
import java.util.ArrayList;
import java.util.List;

public class DAOEmpresa_Tag {
    private SQLiteDatabase db;

    public DAOEmpresa_Tag(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getReadableDatabase();
    }

    public List<Empresa_Tag> obtenerRelaciones() {
        List<Empresa_Tag> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM empresa_tag", null);

        if (cursor.moveToFirst()) {
            do {
                int id_empresa = cursor.getInt(0);
                int id_tag = cursor.getInt(1);

                Empresa_Tag et = new Empresa_Tag(id_empresa, id_tag);
                lista.add(et);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}