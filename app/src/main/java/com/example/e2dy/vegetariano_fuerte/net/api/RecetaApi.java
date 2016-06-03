package com.example.e2dy.vegetariano_fuerte.net.api;

import android.content.Context;

import com.example.e2dy.vegetariano_fuerte.models.Receta;
import com.example.e2dy.vegetariano_fuerte.net.HttpApi;
import com.example.e2dy.vegetariano_fuerte.net.HttpAsyncTask;
import com.example.e2dy.vegetariano_fuerte.net.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by E2dy on 1/06/2016.
 */
public class RecetaApi extends HttpApi {

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
        //String url =  urlBase+context.getString(R.string.url_recetas);
        String url= "http://10.130.8.48:3000/apix/recetas/";
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
