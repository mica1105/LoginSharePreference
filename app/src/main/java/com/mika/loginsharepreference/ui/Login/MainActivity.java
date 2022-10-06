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

public class MainActivity extends AppCompatActivity {
    private ViewModelMain vm;
    private EditText etCorreo, etContraseña;
    private TextView tvMensaje;
    private Button btnLogear, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    private void inicializar() {
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);
        this.etCorreo= findViewById(R.id.etCorreoLogin);
        this.etContraseña= findViewById(R.id.etContraseñaLogin);
        this.tvMensaje= findViewById(R.id.tvMensaje);
        this.btnLogear= findViewById(R.id.btnIniciarSesion);
        this.btnRegistrar= findViewById(R.id.btnRegistrar);
        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMensaje.setText(s);
                tvMensaje.setVisibility(View.VISIBLE);
                etCorreo.setText("");
                etContraseña.setText("");
            }
        });
        btnLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.autenticar(etCorreo.getText().toString(), etContraseña.getText().toString());
                etCorreo.setText("");
                etContraseña.setText("");
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registar();
            }
        });
    }
}