package com.mika.loginsharepreference.ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mika.loginsharepreference.R;
import com.mika.loginsharepreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ViewModelMain vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inicializar();
    }

    private void inicializar() {
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);

        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                binding.tvMensaje.setText(s);
                binding.tvMensaje.setVisibility(View.VISIBLE);
                binding.etCorreoLogin.setText("");
                binding.etContraseALogin.setText("");
            }
        });
        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vm.autenticar(binding.etCorreoLogin.getText().toString(), binding.etContraseALogin.getText().toString());
                binding.etCorreoLogin.setText("");
                binding.etContraseALogin.setText("");
            }
        });
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registar();
            }
        });
    }
}