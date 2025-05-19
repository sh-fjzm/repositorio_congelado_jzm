package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDDSQLite extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "empresas.db";
    private static final int VERSION_BD = 1;

    // Constructor de la base de datos
    public BBDDSQLite(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    // Crear las tablas de la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE empresa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "sector TEXT, " +
                "empresa_logo TEXT, " +
                "direccion TEXT, " +
                "ciudad TEXT, " +
                "telefono TEXT, " +
                "email TEXT, " +
                "linkedin_url TEXT, " +
                "web TEXT, " +
                "datos TEXT" +
                ");");

        db.execSQL("CREATE TABLE tag (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE oferta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "url TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "descripcion TEXT" +
                ");");

        db.execSQL("CREATE TABLE empresa_tag (" +
                "empresa_id INTEGER NOT NULL, " +
                "tag_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, tag_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE" +
                ");");

        db.execSQL("CREATE TABLE empresa_oferta (" +
                "empresa_id INTEGER NOT NULL, " +
                "oferta_id INTEGER NOT NULL, " +
                "PRIMARY KEY (empresa_id, oferta_id), " +
                "FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (oferta_id) REFERENCES oferta(id) ON DELETE CASCADE" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS empresa_oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa_tag");
        db.execSQL("DROP TABLE IF EXISTS oferta");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS tag");
        onCreate(db);
    }
}