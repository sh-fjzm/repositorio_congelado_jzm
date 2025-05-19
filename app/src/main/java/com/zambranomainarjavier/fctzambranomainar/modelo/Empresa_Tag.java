package com.zambranomainarjavier.fctzambranomainar.modelo;

public class Empresa_Tag {
    private long id_empresa;
    private long id_tag;

    public Empresa_Tag(long id_empresa, long id_tag) {
        this.id_empresa = id_empresa;
        this.id_tag = id_tag;
    }

    public long getId_empresa() {
        return id_empresa;
    }

    public long getId_tag() {
        return id_tag;
    }

    public void setId_empresa(long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setId_tag(long id_tag) {
        this.id_tag = id_tag;
    }

    public String toString() {
        return "Id Empresa: " + id_empresa +
                "\nId Tag: " + id_tag;
    }
}
