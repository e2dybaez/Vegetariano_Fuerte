package com.example.e2dy.vegetariano_fuerte;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.e2dy.vegetariano_fuerte.fragments.FavoritosFragment;
import com.example.e2dy.vegetariano_fuerte.fragments.HomeFragment;
import com.example.e2dy.vegetariano_fuerte.models.Item;
import com.example.e2dy.vegetariano_fuerte.models.Publicidad;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.models.Usuario;
import com.example.e2dy.vegetariano_fuerte.net.api.RecetaApi;
import com.example.e2dy.vegetariano_fuerte.util.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnHomeItemClick, RecetaApi.OnUsuariosListener {

    ActivityMainBinding binding;
    ActionBarDrawerToggle toggle;

    HomeFragment home;
    FavoritosFragment favoritos;

    List<Receta> data;
    RecetaApi api;
    RecetaAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //Configuracion del HEADER
        HeaderNavBinding header=HeaderNavBinding.inflate(getLayoutInflater());
        Usuario u = new Usuario();

        data= new ArrayList<>();
        adapter = new RecetaAdapter(getLayoutInflater(), data);
        api= new RecetaApi(this);

        //binding.setAdapter(adapter);

         api.getRecetas(this);



        u.setNombre("Rango");
        u.setImagenBanner("http://www.wallpaperfo.com/thumbnails/detail/20120428/green%20leaves%20plants%20macro%20soft%20light%201920x1080%20wallpaper_www.wallpaperfo.com_23.jpg");
        u.setImagen("http://www.gamasutra.com/db_area/images/news2001/32975/110210-rg.jpg");
        header.setUsuario(u);
        binding.nav.addHeaderView(header.getRoot());


        //Configuracion del
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, binding.drawer, R.string.open_menu, R.string.close_menu);
        binding.drawer.addDrawerListener(this);

        //Fragments

        home=new HomeFragment();
        favoritos=new FavoritosFragment();

        putFragment(R.id.container,home);
        binding.nav.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle(R.string.nav_recetas);
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

    private void putFragment(int container, Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container, fragment);
        ft.commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_recetas:
                getSupportActionBar().setTitle(R.string.nav_recetas);
                putFragment(R.id.container,home);
                break;
            case R.id.nav_favorites:
                getSupportActionBar().setTitle(R.string.nav_favorites);
                putFragment(R.id.container,favoritos);
                break;
            case R.id.nav_logout:
                putFragment(R.id.container, favoritos);
        }

        binding.drawer.closeDrawers();
        return false;
    }

    @Override
    public void onHomeClick(int pos, int type) {
        if(type== Item.TYPE_RECETA){
            Intent intent = new Intent(this, DetailPageActivity.class);
            intent.putExtra(DetailPageActivity.EXTRA_POS, pos);
            startActivity(intent);
        }
    }

    @Override
    public void onUsuarios(List<Receta> recetas) {
        for(Receta r: recetas){
            data.add(r);

        }
        adapter.notifyDataSetChanged();
    }
}
