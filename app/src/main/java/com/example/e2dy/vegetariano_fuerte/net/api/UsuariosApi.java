package com.example.e2dy.vegetariano_fuerte.net.api;

import android.content.Context;

import com.example.e2dy.vegetariano_fuerte.R;
import com.example.e2dy.vegetariano_fuerte.models.Usuario;
import com.example.e2dy.vegetariano_fuerte.net.HttpApi;
import com.example.e2dy.vegetariano_fuerte.net.HttpAsyncTask;
import com.example.e2dy.vegetariano_fuerte.net.Response;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Dario Chamorro on 22/05/2016.
 */
public class UsuariosApi extends HttpApi {

    private  static final int REQUEST_REG=0;
    private  static final int REQUEST_LOGIN=1;
    private  static final int REQUEST_USUARIOS=2;

    //region Callback
    public interface OnRegListener{
        void onReg(boolean success, Usuario usr);
    }

    public interface OnLoginListener{
        void onLogin(boolean success, Usuario usr);
    }

    public interface OnUsuariosListener{
        void onUsuarios(List<Usuario> usuarios);
    }

    OnRegListener onRegListener;
    OnLoginListener onLoginListener;
    OnUsuariosListener onUsuariosListener;
    //endregion


    public UsuariosApi(Context context) {
        super(context);
    }

    //region Registrar
    public void registrar(Usuario usuario, OnRegListener onRegListener){
        this.onRegListener = onRegListener;
        HttpAsyncTask task = makeTask(REQUEST_REG, HttpAsyncTask.METHOD_POST);
        String url = urlBase+context.getString(R.string.url_usuarios);
        task.execute(url, gson.toJson(usuario));

    }

    private void processReg(Response response) {
        if(validateError(response)){
            try {
                JSONObject json = new JSONObject(response.getMsg());
                boolean success = json.getBoolean("success");
                if(success){
                    JSONObject usuario = json.getJSONObject("obj");
                    onRegListener.onReg(true, gson.fromJson(usuario.toString(), Usuario.class));
                }else{
                    onRegListener.onReg(false, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            onRegListener.onReg(false, null);
        }
    }
    //endregion

    //region Login
    public void login(String usuario, String password, OnLoginListener onLoginListener){
        this.onLoginListener = onLoginListener;
        HttpAsyncTask task = makeTask(REQUEST_LOGIN, HttpAsyncTask.METHOD_POST);
        String url = urlBase+context.getString(R.string.url_login);


        try {
            JSONObject obj = new JSONObject();
            obj.put("usuario", usuario);
            obj.put("password", password);
            task.execute(url, obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void processLogin(Response response) {
        if(validateError(response)){
            try {
                JSONObject json = new JSONObject(response.getMsg());
                boolean success = json.getBoolean("success");
                if(success){
                    JSONObject usuario = json.getJSONObject("usuario");
                    onLoginListener.onLogin(true, gson.fromJson(usuario.toString(), Usuario.class));
                }else{
                    onLoginListener.onLogin(false, null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            onLoginListener.onLogin(false, null);
        }
    }
    //endregion

    //region Usuarios
    public void getUsuarios(OnUsuariosListener onUsuariosListener){
        this.onUsuariosListener = onUsuariosListener;
        HttpAsyncTask task = makeTask(REQUEST_USUARIOS, HttpAsyncTask.METHOD_GET);
        String url = urlBase+context.getString(R.string.url_usuarios);
        task.execute(url);
    }

    private void processUsuarios(Response response) {
        List<Usuario> data;
        if(validateError(response)){
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            data = gson.fromJson(response.getMsg(),type);
        }else{
            data =  new ArrayList<>();
        }

        onUsuariosListener.onUsuarios(data);
    }
    //endregion


    @Override
    public void onResponse(int request, Response response) {
        switch (request){
            case REQUEST_REG:
                processReg(response);
                break;
            case REQUEST_LOGIN:
                processLogin(response);
                break;
            case REQUEST_USUARIOS:
                processUsuarios(response);
                break;
        }
    }






}
