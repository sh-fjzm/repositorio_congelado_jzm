package com.zambranomainarjavier.fctzambranomainar.controlador;

import android.content.Context;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOEmpresa;
import com.zambranomainarjavier.fctzambranomainar.bd.DAOOferta;
import com.zambranomainarjavier.fctzambranomainar.modelo.Empresa;
import com.zambranomainarjavier.fctzambranomainar.modelo.Oferta;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/*
    En esta clase se procesan los datos que se han obtenido previamente de la clase ApiServicio.

    Recorremos el JSONObject que nos devuelve la API y recorremos sus resultados uno por uno.
    Buscamos los campos en el JSON con los datos que nos interesan y los guardamos en una variable
    auxiliar que nos permita crear los objetos empresa y oferta.

    Una vez hemos creado un objeto, lo insertamos en la base de datos mediante el metodo creado en
    las clases DAO y continuamos con el siguiente resultado. Recorremos los JSON hasta que llegamos
    a la longitud del array que contiene los resultados.
 */
public class ProcesarDatosApi {

    private DAOEmpresa daoEmpresa;
    private DAOOferta daoOferta;

    // Establecer el tamaño que va a tener como maximo el logo que se va a guardar.
    private static final int LIMITE_PESO_LOGO_BYTES = 600 * 1024;

    /*
        Constructor que recibe la llamada del menu Buscar Empresas y crea objetos DAO
        para manejar los datos de las empresas y las ofertas.
     */
    public ProcesarDatosApi(Context context) {
        this.daoEmpresa = new DAOEmpresa(context);
        this.daoOferta = new DAOOferta(context);
    }

    public void procesar(JSONArray resultados) {
        try {

            System.out.println("Número de resultados recibidos: " + resultados.length()); // 👈 AÑADE AQUÍ



            // Recorremos el array de resultados con un bucle for que va de 0 a la longitud del array
            for (int i = 0; i < resultados.length(); i++) {
                // Obtenemos los objetos JSON para cada iteracion del bucle
                JSONObject item = resultados.getJSONObject(i);

                // Obtener los datos de la empresa
                String nombre = item.optString("organization", null);
                String sector = item.optString("linkedin_org_industry", null);
                String logo = item.optString("organization_logo", null);
                String direccion = item.optString("linkedin_org_locations", null);
                String ciudad = item.optString("cities_derived", null);
                String linkedin_url = item.optString("organization_url", null);
                String web = item.optString("linkedin_org_url", null);
                String datos = item.optString("linkedin_org_specialties", null);

                // Creamos un objeto empresa con los datos obtenidos
                Empresa empresa = new Empresa(nombre, sector, logo, direccion, ciudad, linkedin_url, web, datos);

                daoEmpresa.insertarEmpresa(empresa);

                // Obtener los datos de la oferta
                String urlOferta = item.optString("url", null);
                String fecha = item.optString("date_posted", null);
                String descripcion = item.optString("description_text", null); // Descripcion de la oferta

                // Creamos un objeto oferta con los datos obtenidos
                Oferta oferta = new Oferta(urlOferta, descripcion, fecha);

                // Insertamos la oferta en la base de datos empleando el metodo de clase DAO
                daoOferta.insertarOferta(oferta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Error procesando el JSON de LinkedIn");
        }
    }

}

