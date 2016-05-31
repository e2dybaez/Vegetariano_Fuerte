package com.example.e2dy.vegetariano_fuerte.net.api;

import android.content.Context;
import android.util.Log;

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
 * Created by Hamilton Urbano on 30/05/2016.
 */
public class RecetaApi extends HttpApi{

    OnRecetasListener onRecetasListener;
    Context context;

    public static final int REQUEST_RECETAS=0;



    public interface OnRecetasListener{
        void onRecetas(List<Receta> recetas);
    }

    public RecetaApi(Context context) {
        super(context);
    }



    public void getRecetas(OnRecetasListener onRecetasListener){
        this.onRecetasListener = onRecetasListener;
        HttpAsyncTask task = makeTask(REQUEST_RECETAS, HttpAsyncTask.METHOD_GET);
        //TODO: cambiar String
        //String url =  urlBase+context.getString(R.string.url_recetas);
        String url= "http://192.168.0.31:3000/apix/recetas/recetas";
        Log.i("haur","Entra a get recetas:");
        task.execute(url);
    }

    private void processRecetas(Response response) {

        List<Receta> data;
        if(validateError(response)){
            Type type = new TypeToken<List<Receta>>(){}.getType();
            data = gson.fromJson(response.getMsg(),type);
        }else{
            data =  new ArrayList<>();
        }
        Log.i("haur","Llega a on Process, data: "+data.size());
        onRecetasListener.onRecetas(data);
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
