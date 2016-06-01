package com.example.e2dy.vegetariano_fuerte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.e2dy.vegetariano_fuerte.databinding.ActivityDetailPageBinding;
import com.example.e2dy.vegetariano_fuerte.databinding.ActivityMainBinding;
import com.example.e2dy.vegetariano_fuerte.db.RecetaDao;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.util.L;

public class DetailPageActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_POS = "pos";

    ActivityDetailPageBinding binding;
    Receta receta = (Receta) L.data;


    RecetaDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int pos = getIntent().getIntExtra(EXTRA_POS,0);
        receta = (Receta) L.data.get(pos);

        binding.setReceta(receta);

        //setContentView(binding.getRoot());


        dao = new RecetaDao(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalle de receta");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


        dao.insert(receta);

    }
}
