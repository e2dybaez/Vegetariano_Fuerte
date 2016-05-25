package com.example.e2dy.vegetariano_fuerte;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        u.setNombre("Rango");
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
        binding.recycler.setLayoutManager(new GridLayoutManager(this,2));

        loadItems();


    }

    private void loadItems() {

        Receta r1=new Receta();
        r1.setNombre("Lentejas");
        r1.setTipo("Vegana");
        r1.setCategoria("Principios");
        r1.setDificultad("Facil");
        r1.setPersonas("4");
        r1.setImagen("http://www.amc.info/uploads/images/lentejas.jpg");
        r1.setTiempo("30 min");

        Receta r2=new Receta();
        r2.setNombre("Frijoles");
        r2.setTipo("Vegana");
        r2.setCategoria("Principios");
        r2.setDificultad("Media");
        r2.setPersonas("2");
        r2.setImagen("http://www.javirecetas.com/images/recetas/frijoles.jpg");
        r2.setTiempo("45 min");

        Receta r3=new Receta();
        r3.setNombre("Postre de frutas");
        r3.setTipo("Vegetariana");
        r3.setCategoria("Postres");
        r3.setDificultad("Facil");
        r3.setPersonas("2");
        r3.setImagen("http://www.pequerecetas.com/wp-content/uploads/2015/01/postres-fruta-2.jpg");
        r3.setTiempo("25  min");

        Receta r4=new Receta();
        r4.setNombre("Flan de mandarina y kiwi");
        r4.setTipo("Vegetariana");
        r4.setCategoria("Postres");
        r4.setDificultad("Dificil");
        r4.setPersonas("3");
        r4.setImagen("http://www.rafaela.com/cms/files/multimedias/21555_Super_light_Postres_ricos,_faciles_y_con_frutas2.jpg");
        r4.setTiempo("1 hora");

        Publicidad p1=new Publicidad();
        p1.setNombreEntidad("Coca Cola");
        p1.setImagenEntidad("https://s-media-cache-ak0.pinimg.com/736x/df/07/f5/df07f5c4386924ac7e72abce4d2e3e58.jpg");
        p1.setWpEntidad("www.coca-cola.com.com");

        Publicidad p2=new Publicidad();
        p2.setNombreEntidad("Ades");
        p2.setImagenEntidad("http://www.theinsidersnet.com/cms/app/modules/imagegallery/disposal/pl_1/campaigns/colombia/unilever_ades_1410/Ades_Blogs_Inv.jpg");
        p2.setWpEntidad("http://www.ades.mx");


        L.data.add(r1);
        L.data.add(r2);
        L.data.add(p1);
        L.data.add(r3);
        L.data.add(r4);
        L.data.add(r1);
        L.data.add(r4);
        L.data.add(r1);
        L.data.add(r2);
        L.data.add(p2);
        L.data.add(r1);
        L.data.add(r2);
        L.data.add(p1);
        L.data.add(r3);
        L.data.add(r4);
        L.data.add(r1);
        L.data.add(r4);
        L.data.add(r1);
        L.data.add(r2);
        L.data.add(p2);

        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filtros,menu);
        return super.onCreateOptionsMenu(menu);
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

        switch (item.getItemId()){
            case R.id.action_filtro:

                break;
        }
        Toast.makeText(this,"Seleccionaste"+item.getTitle(),Toast.LENGTH_SHORT).show();

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
