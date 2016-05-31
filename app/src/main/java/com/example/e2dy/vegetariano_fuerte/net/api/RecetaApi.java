package com.example.e2dy.vegetariano_fuerte.net.api;

import android.content.Context;

import com.example.e2dy.vegetariano_fuerte.R;
import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.models.Usuario;
import com.example.e2dy.vegetariano_fuerte.net.HttpApi;
import com.example.e2dy.vegetariano_fuerte.net.HttpAsyncTask;
import com.example.e2dy.vegetariano_fuerte.net.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rjcruz on 28/05/2016.
 */
public class RecetaApi extends HttpApi {

    OnUsuariosListener onUsuariosListener;
    Context context;

    private static final int REQUEST_RECETAS=0;



    public interface OnUsuariosListener{
        void onUsuarios(List<Receta> usuarios);
    }

    public RecetaApi(Context context) {
        super(context);
    }



    public void getRecetas(OnUsuariosListener onUsuariosListener){
        this.onUsuariosListener = onUsuariosListener;
        HttpAsyncTask task = makeTask(REQUEST_RECETAS, HttpAsyncTask.METHOD_GET);
        String url =  urlBase+context.getString(R.string.url_usuarios);
        task.execute(url);
    }

    private void processRecetas(Response response) {

        List<Receta> data;
        if(validateError(response)){
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            data = gson.fromJson(response.getMsg(),type);
        }else{
            data =  new ArrayList<>();
        }

        onUsuariosListener.onUsuarios(data);
    }
    @Override
    public void onResponse(int request, Response response) {
        switch (request){
            case REQUEST_RECETAS:
                processRecetas(response);
                break;

        }
    }
}
