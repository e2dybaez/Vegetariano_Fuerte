package com.example.e2dy.vegetariano_fuerte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.e2dy.vegetariano_fuerte.databinding.ActivityLoginBinding;
import com.example.e2dy.vegetariano_fuerte.models.Usuario;
import com.example.e2dy.vegetariano_fuerte.net.api.UsuariosApi;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UsuariosApi.OnLoginListener {

    UsuariosApi usuariosApi;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setOnClick(this);

        usuariosApi = new UsuariosApi(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg:
            Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ent:

                String usr = binding.usr.getText().toString();
                String pass = binding.pass.getText().toString();
                usuariosApi.login(usr, pass, this);

                break;
        }
    }

    @Override
    public void onLogin(boolean success, Usuario usr) {
        if(success){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show();
        }


    }
}
