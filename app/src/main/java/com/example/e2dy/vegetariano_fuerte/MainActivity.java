package com.example.e2dy.vegetariano_fuerte;

import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.example.e2dy.vegetariano_fuerte.adapters.RecetaAdapter;
import com.example.e2dy.vegetariano_fuerte.databinding.ActivityMainBinding;
import com.example.e2dy.vegetariano_fuerte.databinding.HeaderNavBinding;
import com.example.e2dy.vegetariano_fuerte.models.Publicidad;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.models.Usuario;
import com.example.e2dy.vegetariano_fuerte.util.L;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    ActivityMainBinding binding;
    ActionBarDrawerToggle toggle;

    RecetaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Configuracion del HEADER
        HeaderNavBinding header=HeaderNavBinding.inflate(getLayoutInflater());
        Usuario u = new Usuario();
        u.setNombre("Nombre Usuario");
        u.setImagenBanner("http://www.wallpaperfo.com/thumbnails/detail/20120428/green%20leaves%20plants%20macro%20soft%20light%201920x1080%20wallpaper_www.wallpaperfo.com_23.jpg");
        u.setImagen("http://www.gamasutra.com/db_area/images/news2001/32975/110210-rg.jpg");
        header.setUsuario(u);
        binding.nav.addHeaderView(header.getRoot());


        //Configuracion del
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, binding.drawer, R.string.open_menu, R.string.close_menu);
        binding.drawer.addDrawerListener(this);

        //Configuracoin RecyclerView

        L.data=new ArrayList<>();
        adapter=new RecetaAdapter(this, L.data);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        loadItems();
    }

    private void loadItems() {

        Receta r1=new Receta();
        r1.setNombre("Lentejas");
        r1.setCategoria("Vegana");
        r1.setCategoria("Principios");
        r1.setDificultad("Facil");
        r1.setPersonas("4");
        r1.setImagen("http://www.amc.info/uploads/images/lentejas.jpg");
        r1.setTiempo("30 min");

        Receta r2=new Receta();
        r2.setNombre("Frijoles");
        r2.setCategoria("Vegana");
        r2.setCategoria("Principios");
        r2.setDificultad("Medio");
        r2.setPersonas("2");
        r2.setImagen("http://www.javirecetas.com/images/recetas/frijoles.jpg");
        r2.setTiempo("45 min");

        Publicidad p1=new Publicidad();
        p1.setNombreEntidad("Coca Cola");
        p1.setImagenEntidad("http://www.cookingideas.es/imagenes/destapandofelicidad.jpg");


        L.data.add(r1);
        L.data.add(r2);
        L.data.add(p1);

        adapter.notifyDataSetChanged();
    }

    //region Configuracion del Toogle (Region)
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        toggle.onDrawerSlide(drawerView,slideOffset);

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        toggle.onDrawerOpened(drawerView);

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        toggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        toggle.onDrawerStateChanged(newState);

    }
    //endregion

}
