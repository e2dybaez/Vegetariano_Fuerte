package com.example.e2dy.vegetariano_fuerte.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.example.e2dy.vegetariano_fuerte.databinding.TemplateIngredientesBinding;
import com.example.e2dy.vegetariano_fuerte.models.Ingrediente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by E2dy on 27/05/2016.
 */
public class IngredientesAdapter  extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

    LayoutInflater inflater;
    List<Ingrediente> data;
    Map<Integer, Boolean> selected;

    public IngredientesAdapter(LayoutInflater inflater, List<Ingrediente> data) {
        this.inflater = inflater;
        this.data = data;
        selected = new HashMap<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TemplateIngredientesBinding binding = TemplateIngredientesBinding.inflate(inflater);
        binding.setIngrediente(data.get(position));
        binding.setPos(position);
        binding.setOnChecked(this);
        return binding.getRoot();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = (Integer)buttonView.getTag();

        if(isChecked){
            selected.put(pos, isChecked);
        }else{
            selected.remove(pos);
        }

    }

    public List<Integer> getSelected(){
        List<Integer> ing = new ArrayList<>(selected.keySet());
        return  ing;
    }
}

