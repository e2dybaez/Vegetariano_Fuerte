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
import com.example.e2dy.vegetariano_fuerte.db.RecetaDao;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.util.F;
import com.example.e2dy.vegetariano_fuerte.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements RecetaAdapter.OnItemClickAdpapter   {

    RecetaDao dao;
    RecetaAdapter adapter;
    FragmentHomeBinding binding;
    OnFavoritoItemClick onFavoritoItemClick;


    public FavoritosFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        onFavoritoItemClick= (OnFavoritoItemClick) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(getLayoutInflater((savedInstanceState)));

        dao = new RecetaDao(getContext());


        if(F.data==null)
            F.data=new ArrayList<>();

        adapter=new RecetaAdapter(getContext(), F.data, this);
        binding.setAdapter(adapter);
        binding.recycler.setLayoutManager(new GridLayoutManager(getContext(),2));

        //List<Receta> data = new ArrayList<>();

       List<Receta> data = dao.getAll();

        for(Receta receta : data){
            F.data.add(receta);
        }

        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }


    public interface OnFavoritoItemClick{
        void  onFavoritoClick(int pos, int type);
    }

    @Override
    public void onClick(View v) {
        int pos = binding.recycler.getChildAdapterPosition(v);
        onFavoritoItemClick.onFavoritoClick(pos,F.data.get(pos).getType()
        );
    }
}
