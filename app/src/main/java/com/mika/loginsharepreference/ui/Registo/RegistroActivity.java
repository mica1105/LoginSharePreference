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
import com.mika.loginsharepreference.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private ViewModelRegistro vmr;
    private EditText etDni, etApellido, etNombre, etEmail, etPassword;
    private Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    private void inicializar() {
        vmr= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);
        this.etDni= findViewById(R.id.etDni);
        this.etApellido= findViewById(R.id.etApellido);
        this.etNombre= findViewById(R.id.etNombre);
        this.etEmail= findViewById(R.id.etCorreo);
        this.etPassword= findViewById(R.id.etPassword);
        this.btnGuardar= findViewById(R.id.btnGuardar);
        vmr.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etEmail.setText(usuario.getMail());
                etPassword.setText(usuario.getPassword());
            }
        });
        vmr.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vmr.registrar(etDni.getText().toString(), etApellido.getText().toString(), etNombre.getText().toString(),etEmail.getText().toString(), etPassword.getText().toString());
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