package com.example.e2dy.vegetariano_fuerte.models;

/**
 * Created by E2dy on 2/06/2016.
 */
public class Favorito implements Item {

    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getType() {
        return TYPE_RECETA;
    }
}
