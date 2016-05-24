package com.example.e2dy.vegetariano_fuerte.attrs;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by E2dy on 22/05/2016.
 */
public class Attrs {

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView img, String url){
        Picasso.with(img.getContext()).load(Uri.parse(url)).into(img);

    }


}



