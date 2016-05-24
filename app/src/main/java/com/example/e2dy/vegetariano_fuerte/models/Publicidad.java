package com.example.e2dy.vegetariano_fuerte.models;

/**
 * Created by E2dy on 22/05/2016.
 */
public class Publicidad implements Item {

    String nombreEntidad, imagenEntidad;

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getImagenEntidad() {
        return imagenEntidad;
    }

    public void setImagenEntidad(String imagenEntidad) {
        this.imagenEntidad = imagenEntidad;
    }

    @Override
    public int getType() {
        return TYPE_PUBLICIDAD;
    }
}
