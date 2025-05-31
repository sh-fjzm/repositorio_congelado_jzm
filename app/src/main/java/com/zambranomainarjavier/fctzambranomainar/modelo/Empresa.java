package com.zambranomainarjavier.fctzambranomainar.modelo;

import java.util.List;
import java.io.Serializable;

public class Empresa implements Serializable {
    private long id;
    private String nombre;
    private String sector;
    private String logo;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String email;
    private String linkedin_url;
    private String web;
    private String datos; // Campo del JSON linkedin_org_specialties, importante para buscar tags
    private List<Tag> listaTags;

    public Empresa(String nombre, String sector, String logo,
                   String direccion, String ciudad, String telefono, String email, String linkedin_url,
                   String web, String datos) {
        this.nombre = nombre;
        this.sector = sector;
        this.logo = logo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
        this.linkedin_url = linkedin_url;
        this.web = web;
        this.datos = datos;
    }

    public Empresa(String nombre, String sector, String logo,
                   String direccion, String ciudad, String linkedin_url,
                   String web, String datos) {
        this.nombre = nombre;
        this.sector = sector;
        this.logo = logo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = null;
        this.email = null;
        this.linkedin_url = linkedin_url;
        this.web = web;
        this.datos = datos;
    }

    public Empresa() {
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public String toString() {
        return "Id Empresa: " + id +
                "\nNombre: " + nombre +
                "\nSector: " + sector +
                "\nDireccion: " + direccion +
                "\nCiudad: " + ciudad +
                "\nTelefono: " + telefono +
                "\nEmail: " + email +
                "\nLinkedIn: " + linkedin_url +
                "\nWeb: " + web +
                "\nDatos: " + datos +
                "\nTags: " + listaTags.toString();
    }

    public void setNombre(String nombreEmpresa) {
        this.nombre = nombreEmpresa;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLinkedin_url(String urlLinkedin) {
        this.linkedin_url = urlLinkedin;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getEspecialidades() {
        return datos;
    }

    public String getWeb() {
        return web;
    }

    public String getLinkedinUrl() {
        return linkedin_url;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLogo() {
        return logo;
    }

    public String getSector() {
        return sector;
    }

    public String getTelefono() {
        return null;
    }

    public String getEmail() {
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEspecialidades(String datos) {
        this.datos = datos;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedin_url = linkedinUrl;
    }
}
