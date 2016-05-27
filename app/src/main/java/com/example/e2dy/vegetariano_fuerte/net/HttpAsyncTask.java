package com.example.e2dy.vegetariano_fuerte.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Dario Chamorro on 22/05/2016.
 */
public class HttpAsyncTask extends AsyncTask<String, Void, Response> {

    public static final int METHOD_GET=0;
    public static final int METHOD_POST=1;
    public static final int METHOD_PUT=2;
    public static final int METHOD_DELETE=3;

    public interface OnResponseListener{
        void onResponse(int request, Response response);
    }

    Context context;
    int method;
    OnResponseListener onResponseListener;
    int request;

    public HttpAsyncTask(Context context,int request,int method, OnResponseListener onResponseListener) {
        this.method = method;
        this.context = context;
        this.request = request;
        this.onResponseListener = onResponseListener;
    }

    @Override
    protected Response doInBackground(String... params) {
        HttpConnection con =  new HttpConnection();

        Response response = null;
        if(isConnected()){
            try {
                switch (method) {
                    case METHOD_GET:
                        response = con.get(params[0]);
                        break;
                    case METHOD_POST:
                        response = con.post(params[0],params[1]);
                        break;
                    case METHOD_PUT:
                        response = con.put(params[0],params[1]);
                        break;
                    case METHOD_DELETE:
                        response = con.delete(params[0],params[1]);
                        break;
                }
                response.setError(HttpError.NO_ERROR);

            }catch (SocketTimeoutException e){
                response = new Response(HttpError.TIMEOUT);
            }catch(IOException e){
                response = new Response(HttpError.SERVER);
            }
        }else{
            response = new Response(HttpError.NO_INTERNET);
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        onResponseListener.onResponse(request,response);
    }

    private boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }
}
