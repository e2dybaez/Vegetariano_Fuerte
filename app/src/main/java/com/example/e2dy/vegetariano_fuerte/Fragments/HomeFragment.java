package com.example.e2dy.vegetariano_fuerte.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e2dy.vegetariano_fuerte.R;
import com.example.e2dy.vegetariano_fuerte.adapters.RecetaAdapter;
import com.example.e2dy.vegetariano_fuerte.databinding.FragmentHomeBinding;
import com.example.e2dy.vegetariano_fuerte.models.Publicidad;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.util.L;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RecetaAdapter.OnItemClickAdpapter {

    public interface OnHomeItemClick{
        void  onHomeClick(int pos, int type);
    }
    RecetaAdapter adapter;
    FragmentHomeBinding binding;
    OnHomeItemClick onHomeItemClick;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onHomeItemClick= (OnHomeItemClick) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater((savedInstanceState)));

        if (savedInstanceState==null)
            L.data=new ArrayList<>();
        //adapter=new RecetaAdapter(getContext(), L.data, this);
        //adapter = new RecetaAdapter(,)
        binding.setAdapter(adapter);
        binding.recycler.setLayoutManager(new GridLayoutManager(getContext(),2));

        if(savedInstanceState==null)
            loadItems();
        return binding.getRoot();
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
    public void onClick(View v) {
        int pos = binding.recycler.getChildAdapterPosition(v);
        onHomeItemClick.onHomeClick(pos,L.data.get(pos).getType()
        );



    }
}
