package com.zambranomainarjavier.fctzambranomainar.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;
import java.util.ArrayList;
import java.util.List;

/*
    Desde las clases DAO se van a realizar las operaciones que tengan que relacionar
    la base de datos con el modelo de datos. Utilizamos una clase de este tipo por
    cada modelo de datos y tabla en la base de datos.
 */
public class DAOEmpresa {
    // Objeto para manipular la base de datos
    private SQLiteDatabase db;
    /*
        Constructor que inicia la base de datos usando la clase BBDDSQLite,
        la obtiene en modo escritura con getWritableDatabase() y la asignamos a
        una variable para poder usarla mas adelante.
     */
    public DAOEmpresa(Context context) {
        BBDDSQLite bbdd = new BBDDSQLite(context);
        // Accedemos a la base de datos con permisos de escritura
        db = bbdd.getWritableDatabase();
    }
    /*
        Metodo para insertar empresas en la base de datos.
        Realizamos una comprobación previa para comprobar que no existe una empresa
        con ese nombre en la base de datos. Así evitamos duplicar datos.
     */
    public long insertarEmpresa(Empresa empresa) {
        if (obtenerNombre(empresa.getNombre())) {
            // Ya existe la empresa, no la insertamos
            return -1;
        }
        // Creamos un objeto ContentValues con los datos de la empresa
        /*
            ContentValues es una clase Android que funciona como un contenedor temporal clave-valor
            utilizado para insertar o actualizar registros en la bd SQLite
         */
        ContentValues values = new ContentValues();
        values.put("nombre", empresa.getNombre());
        values.put("sector", empresa.getSector());
        values.put("empresa_logo", empresa.getLogo());
        values.put("direccion", empresa.getDireccion());
        values.put("ciudad", empresa.getCiudad());
        values.put("linkedin_url", empresa.getLinkedinUrl());
        values.put("web", empresa.getWeb());
        values.put("datos", empresa.getEspecialidades());
        // Comprobamos si telefono o email son nulos antes de insertarlos
        values.put("telefono", empresa.getTelefono() != null ? empresa.getTelefono() : "");
        values.put("email", empresa.getEmail() != null ? empresa.getEmail() : "");
        // Insertamos los datos en la tabla empresa y devolvemos el id de la fila insertada
        return db.insert("empresa", null, values);
    }
    /*
        Metodo auxiliar para obtener el nombre de una empresa y usarlo en el metodo
        anterior.
     */
    public boolean obtenerNombre(String nombre) {
        // Realizamos una consulta para ver si ya existe una empresa con ese nombre
        Cursor cursor = db.rawQuery("SELECT id FROM empresa WHERE nombre = ?", new String[]{nombre});
        // Si la empresa ya existe
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
    /*
        Metodo que utilizamos para obtener una lista de objetos empresa y poder utilizarla en el
        menú Lista Empresas.

        Seleccionamos todas las empresas que tenemos en la base de datos y creamos un objeto con los
        datos que nos interesan. Lo añadimos a una lista y la devolvemos.

        Cerramos el cursor al finalizar.
     */
    public List<Empresa> obtenerEmpresas() {
        List<Empresa> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM empresa", null);
        // Recorremos los resultados y vamos creando objetos Empresa con los datos
        if (cursor.moveToFirst()) {
            do {
                Empresa empresa = new Empresa();
                empresa.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                empresa.setSector(cursor.getString(cursor.getColumnIndexOrThrow("sector")));
                empresa.setLogo(cursor.getString(cursor.getColumnIndexOrThrow("empresa_logo")));
                empresa.setDireccion(cursor.getString(cursor.getColumnIndexOrThrow("direccion")));
                empresa.setCiudad(cursor.getString(cursor.getColumnIndexOrThrow("ciudad")));
                empresa.setLinkedinUrl(cursor.getString(cursor.getColumnIndexOrThrow("linkedin_url")));
                empresa.setWeb(cursor.getString(cursor.getColumnIndexOrThrow("web")));
                empresa.setEspecialidades(cursor.getString(cursor.getColumnIndexOrThrow("datos")));
                // Añadimos la empresa a la lista
                lista.add(empresa);
            } while (cursor.moveToNext());
        }
        // Cerramos el cursor para liberar recursos
        cursor.close();
        // Empleado para hacer comprobaciones
        Log.d("DAOEmpresa", "Empresas obtenidas: " + lista.size());
        // Devolvemos la lista con las empresas encontradas
        return lista;
    }
    /*
        Metodo utilizado para obtener la informacion guardada en el campo datos de la
        tabla empresa. Este campo contiene informacion relevante de la empresa.
     */
    public Cursor obtenerDatosEspecialidades() {
        // Devolvemos un cursor con todos los campos datos de todas las empresas
        return db.rawQuery("SELECT datos FROM empresa", null);
    }
}