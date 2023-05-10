package com.mika.loginsharepreference.ui.Registo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mika.loginsharepreference.R;
import com.mika.loginsharepreference.databinding.ActivityRegistroBinding;
import com.mika.loginsharepreference.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private ViewModelRegistro vmr;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializar();
    }

    private void inicializar() {
        vmr= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        vmr.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni()+"");
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etCorreo.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
            }
        });
        vmr.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vmr.registrar(binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etCorreo.getText().toString(),
                        binding.etPassword.getText().toString());
            }
        });
        vmr.mostrar((Usuario) getIntent().getSerializableExtra("usuario"));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}