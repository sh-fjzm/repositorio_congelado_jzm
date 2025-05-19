package com.zambranomainarjavier.fctzambranomainar.modelo;

public class Oferta {
    private long id;
    private String url;
    private String descripcion;
    private String fecha;

    public Oferta(String url, String descripcion, String fecha) {
        this.url = url;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public void setUrl(String urlOferta) {
        this.url = urlOferta;
    }

    public void setFecha(String fechaOferta) {
        this.fecha = fechaOferta;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String toString() {
        return "Id Oferta: " + id +
                "\nUrl: " + url +
                "\nDescripcion: " + descripcion +
                "\nFecha: " + fecha;
    }
}
