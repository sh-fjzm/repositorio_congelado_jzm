package com.zambranomainarjavier.fctzambranomainar.modelo;

public class Tag {
    private long id;
    private String nombre;

    public Tag(long id, String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreTag) {
        this.nombre = nombreTag;
    }

    public String toString() {
        return "Id: " + id + "\nNombre: " + nombre;
    }
}
