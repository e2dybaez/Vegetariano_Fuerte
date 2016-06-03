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
import com.example.e2dy.vegetariano_fuerte.util.F;
import com.example.e2dy.vegetariano_fuerte.util.L;

public class DetailPageActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_POS = "pos";
    public static final String EXTRA_TYPE="type";

    public static final int DATA_HOME = 0;
    public static final int DATA_FAVORITE = 1;

    ActivityDetailPageBinding binding;

    RecetaDao dao;
    Receta receta ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int pos = getIntent().getIntExtra(EXTRA_POS,0);
        int lista = getIntent().getIntExtra(EXTRA_TYPE,0);
        if(lista == DATA_HOME)
        receta = (Receta) L.data.get(pos);
        else
            receta = (Receta) F.data.get(pos);
        //receta = (Receta) Y.data.get(pos);

        binding.setReceta(receta);
        binding.setOnClick(this);

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
