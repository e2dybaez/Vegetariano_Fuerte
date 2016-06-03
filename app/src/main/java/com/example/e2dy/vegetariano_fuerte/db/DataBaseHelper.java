package com.example.e2dy.vegetariano_fuerte.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by E2dy on 2/06/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="recetas.db";
    public static int VERSION=1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recetas (_id INTEGER AUTO_INCREMENT PRIMARY KEY"
                +",nombre VARCHAR"
                +",imagen VARCHAR"
                +",tipo VARCHAR"
                +",tiempo VARCHAR"
                +",dificultad VARCHAR"
                +",personas VARCHAR"
                +",categoria VARCHAR"
                +",ingredientes VARCHAR"
                +",preparacion VARCHAR"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE recetas");
        onCreate(db);
    }
}
