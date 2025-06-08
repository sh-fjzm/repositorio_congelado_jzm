package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
    Esta clase DAO se encarga de gestionar la tabla intermedia "empresa_oferta"
    que relaciona empresas con ofertas. A traves de ella podemos insertar registros
    que indiquen que empresa esta asociada a que oferta.
 */
public class DAOEmpresa_Oferta {
    private SQLiteDatabase db;
    /*
        Constructor que inicia la base de datos usando la clase BBDDSQLite.
        Se obtiene una instancia de la base de datos en modo escritura para
        poder insertar relaciones entre empresas y ofertas.
     */
    public DAOEmpresa_Oferta(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        db = bbdd.getWritableDatabase();
    }
    /*
        Metodo para insertar una relacion entre una empresa y una oferta en
        la tabla "empresa_oferta".
        Creamos un objeto ContentValues para almacenar los dos IDs que queremos
        relacionar, y luego insertamos esa informacion en la tabla.
        Esta tabla esta compuesta por claves foraneas hacia las tablas
        "empresa" y "oferta", y cada combinacion debe ser unica.
     */
    public void insertarRelacion(int empresaId, int ofertaId) {
        ContentValues values = new ContentValues();
        values.put("empresa_id", empresaId);
        values.put("oferta_id", ofertaId);
        db.insert("empresa_oferta", null, values);
    }
}