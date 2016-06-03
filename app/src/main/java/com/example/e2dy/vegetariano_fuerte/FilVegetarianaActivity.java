package com.example.e2dy.vegetariano_fuerte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FilVegetarianaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fil_vegetariana);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recetas de tipo Vegetarianas");
    }
}
