package com.example.e2dy.vegetariano_fuerte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailPageActivity extends AppCompatActivity {

    public static final String EXTRA_POS = "pos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalle de receta");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
