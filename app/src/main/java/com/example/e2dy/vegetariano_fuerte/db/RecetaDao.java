package com.example.e2dy.vegetariano_fuerte.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.e2dy.vegetariano_fuerte.models.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E2dy on 2/06/2016.
 */
public class RecetaDao {

    static final String TABLE="recetas";
    static final String C_NOMBRE="nombre";
    static final String C_IMAGEN="imagen";
    static final String C_TIPO="tipo";
    static final String C_TIEMPO="tiempo";
    static final String C_DIFICULTAD="dificultad";
    static final String C_PERSONAS="personas";
    static final String C_CATEGORIA="categoria";
    static final String C_INGREDIENTES="ingredientes";
    static final String C_PREPARACION="preparacion";


    SQLiteDatabase db;

    public RecetaDao(Context context){
        DataBaseHelper helper=new DataBaseHelper(context);
        db=helper.getWritableDatabase();
    }

    public void insert(Receta receta){
        Log.i("UNO", "insert: insterta");
        ContentValues cV=new ContentValues();
        cV.put(C_NOMBRE, receta.getNombre());
        cV.put(C_IMAGEN, receta.getImagen());
        cV.put(C_TIPO, receta.getTipo());
        cV.put(C_TIEMPO, receta.getTiempo());
        cV.put(C_DIFICULTAD, receta.getDificultad());
        cV.put(C_PERSONAS, receta.getPersonas());
        cV.put(C_CATEGORIA, receta.getCategoria());
        cV.put(C_INGREDIENTES, receta.getIngredientes());
        cV.put(C_PREPARACION, receta.getPreparacion());
        long id = db.insert(TABLE,null,cV);

        receta.setId(id);
        Log.i("PRUEBA", "insert: insterta");
    }

    public void update(Receta receta){
        ContentValues cV=new ContentValues();
        cV.put(C_NOMBRE, receta.getNombre());
        cV.put(C_IMAGEN, receta.getImagen());
        cV.put(C_TIPO, receta.getTipo());
        cV.put(C_TIEMPO, receta.getTiempo());
        cV.put(C_DIFICULTAD, receta.getDificultad());
        cV.put(C_PERSONAS, receta.getPersonas());
        cV.put(C_CATEGORIA, receta.getCategoria());
        cV.put(C_INGREDIENTES, receta.getIngredientes());
        cV.put(C_PREPARACION, receta.getPreparacion());
        db.update(TABLE,cV,"_id=?", new String[]{receta.getId()+""});
    }

    public void delete(long id){
        db.delete(TABLE,"_id"+id,null);
    }

    public Receta getById(long id){
        Cursor c= db.rawQuery("SELECT * FROM recetas WHERE _id="+id,null);
        return cursorToReceta(c);
    }

    public List<Receta> getAll(){
        Cursor c= db.rawQuery("SELECT * FROM recetas",null);
        return cursortoList(c);
    }

    public List<Receta>getByName(String nombre ){
        Cursor c= db.rawQuery("SELECT * FROM recetas WHERE nombre LIKE '%"+nombre+"%'",null);
        return cursortoList(c);
    }

    private Receta cursorToReceta(Cursor c){
        Receta receta=null;
        if(c.moveToNext()){
            receta= new Receta();
            receta.setId(c.getLong(0));
            receta.setNombre(c.getString(1));
            receta.setImagen(c.getString(2));
            receta.setTipo(c.getString(3));
            receta.setTiempo(c.getString(4));
            receta.setDificultad(c.getString(5));
            receta.setPersonas(c.getString(6));
            receta.setCategoria(c.getString(7));
            receta.setIngredientes(c.getString(8));
            receta.setPreparacion(c.getString(9));
        }
        return receta;
    }

    private List<Receta> cursortoList(Cursor c){
        List<Receta> data=new ArrayList<>();
        for(int i=0;i<c.getCount();i++){
            Receta r=cursorToReceta(c);
            data.add(r);
        }
        return data;
    }
}
