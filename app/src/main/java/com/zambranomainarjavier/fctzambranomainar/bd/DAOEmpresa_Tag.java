package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa_Tag;
import java.util.ArrayList;
import java.util.List;

// Clase DAO que gestiona la tabla intermedia empresa_tag
public class DAOEmpresa_Tag {
    private SQLiteDatabase db;

    public DAOEmpresa_Tag(Context context) {
        // Instancia de la base de datos
        BBDDSQLite bbdd = new BBDDSQLite(context);
        // Abre la base de datos en modo lectura
        db = bbdd.getReadableDatabase();
    }

    public List<Empresa_Tag> obtenerRelaciones() {
        List<Empresa_Tag> lista = new ArrayList<>();
        // Consulta SQL para obtener todas las relaciones de la tabla empresa_tag
        Cursor cursor = db.rawQuery("SELECT * FROM empresa_tag", null);
        // Recorre los resultados y crea objetos Empresa_Tag con los datos
        if (cursor.moveToFirst()) {
            do {
                // Obtenemos los dos IDs de la relacion
                int id_empresa = cursor.getInt(0);
                int id_tag = cursor.getInt(1);
                // Creamos objeto Empresa_Tag y lo añadimos a la lista
                Empresa_Tag et = new Empresa_Tag(id_empresa, id_tag);
                lista.add(et);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}