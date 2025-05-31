package com.zambranomainarjavier.fctzambranomainar.modelo;

public class Datos {
    private String titulo;
    private String empresa;
    private String estudio;
    private String descripcion;
    private String subcategoria;
    private String localidad;

    public Datos(String titulo, String empresa, String estudio, String descripcion, String subcategoria, String localidad) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.estudio = estudio;
        this.descripcion = descripcion;
        this.subcategoria = subcategoria;
        this.localidad = localidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String toString() {
        return "Titulo: " + titulo +
                "Empresa: " + empresa +
                "Estudio: " + estudio +
                "Descripcion: " + descripcion +
                "Subcategoria: " + subcategoria +
                "Localidad: " + localidad;
    }


}
