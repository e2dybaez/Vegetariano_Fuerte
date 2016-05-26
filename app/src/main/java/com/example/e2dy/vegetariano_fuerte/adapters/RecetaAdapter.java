package com.example.e2dy.vegetariano_fuerte.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.e2dy.vegetariano_fuerte.DetailPageActivity;
import com.example.e2dy.vegetariano_fuerte.R;
import com.example.e2dy.vegetariano_fuerte.databinding.TemplatePublicidadBinding;
import com.example.e2dy.vegetariano_fuerte.databinding.TemplateRecetaBinding;
import com.example.e2dy.vegetariano_fuerte.models.Item;
import com.example.e2dy.vegetariano_fuerte.models.Publicidad;
import com.example.e2dy.vegetariano_fuerte.models.Receta;

import java.util.List;

import static android.content.Intent.getIntent;
import static android.content.Intent.parseIntent;

/**
 * Created by E2dy on 22/05/2016.
 */
public class RecetaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public interface OnItemClickAdpapter{
         void onClick(View v);
    }

    Context context;
    List<Item> data;
    OnItemClickAdpapter onItemClickAdpapter;

    public RecetaAdapter(Context context, List<Item> data,OnItemClickAdpapter onItemClickAdpapter) {
        this.context = context;
        this.data = data;
        this.onItemClickAdpapter = onItemClickAdpapter;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        if(viewType==Item.TYPE_RECETA){
            View v = View.inflate(context, R.layout.template_receta,null);
            viewHolder=new RecetaViewHolder(v);
        }else {
            View v = View.inflate(context,R.layout.template_publicidad,null);
            viewHolder=new PublicidadViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Item i =data.get(position);
        if(i.getType()==Item.TYPE_RECETA){
            Receta r = (Receta) i;
            RecetaViewHolder h= (RecetaViewHolder) holder;
            h.binding.setReceta(r);
            h.binding.getRoot().setOnClickListener(this);
        }else{
            Publicidad p = (Publicidad) i;
            PublicidadViewHolder h= (PublicidadViewHolder) holder;
            h.binding.setPubli(p);
            h.binding.getRoot().setOnClickListener(this);


        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    @Override
    public int getItemViewType(int position) {
        /*if(position%4==0){
            return 0;
        }else
            return 1;*/

        return data.get(position).getType();
    }

    @Override
    public void onClick(View v) {

        onItemClickAdpapter.onClick(v);



    }


    class RecetaViewHolder extends RecyclerView.ViewHolder{

        TemplateRecetaBinding binding;

        public RecetaViewHolder(View itemView) {
            super(itemView);
            binding = TemplateRecetaBinding.bind(itemView);
        }


   }

    public static class PublicidadViewHolder extends RecyclerView.ViewHolder {

        TemplatePublicidadBinding binding;

        public PublicidadViewHolder(View itemView) {
            super(itemView);
            binding = TemplatePublicidadBinding.bind(itemView);
        }

    }

}
