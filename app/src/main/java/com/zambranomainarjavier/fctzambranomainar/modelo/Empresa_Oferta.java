package com.zambranomainarjavier.fctzambranomainar.modelo;

public class Empresa_Oferta {
    private long id_empresa;
    private long id_oferta;

    public Empresa_Oferta(long id_empresa, long id_oferta) {
        this.id_empresa = id_empresa;
        this.id_oferta = id_oferta;
    }

    public long getId_empresa() {
        return id_empresa;
    }

    public long getId_oferta() {
        return id_oferta;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String toString() {
        return "Id Empresa: " + id_empresa +
                "\nId Oferta: " + id_oferta;
    }
}
