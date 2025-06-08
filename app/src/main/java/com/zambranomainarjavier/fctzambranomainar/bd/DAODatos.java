package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// Clase para el acceso a los datos de la tabla "datos"
public class DAODatos {
    // Instancia de la clase BBDDSQLite, que gestiona la base de datos
    private BBDDSQLite bd;
    // Constructor que recibe el contexto de la aplicacion y crea una instancia de BBDDSQLite
    public DAODatos(Context context) {
        bd = new BBDDSQLite(context);
    }
    // Metodo que devuelve un cursor con todas las descripciones almacenadas en la tabla "datos"
    public Cursor obtenerDescripciones() {
        // Abre la base de datos en modo solo lectura
        SQLiteDatabase db = bd.getReadableDatabase();
        // Ejecuta una consulta SQL que selecciona solo la columna "descripcion"
        return db.rawQuery("SELECT descripcion FROM datos", null);
    }
}