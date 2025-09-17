package com.example.evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText lblEmail, lblContraseña;
    private Button btnVer, btnIniciarSesion, btbVolver;
    private CheckBox cboxRecordar;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        lblEmail = findViewById(R.id.lblEmail);
        lblContraseña = findViewById(R.id.lblContraseña);
        btnVer = findViewById(R.id.btnVer);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btbVolver = findViewById(R.id.btbVolver);
        cboxRecordar = findViewById(R.id.cboxRecordar);

        // Mostrar/Ocultar contraseña
        btnVer.setOnClickListener(v -> {
            if (isPasswordVisible) {
                lblContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnVer.setText("👁");
            } else {
                lblContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnVer.setText("🙈");
            }
            lblContraseña.setSelection(lblContraseña.getText().length());
            isPasswordVisible = !isPasswordVisible;
        });

        // Volver a MainActivity
        btbVolver.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Iniciar sesión
        btnIniciarSesion.setOnClickListener(v -> {
            String email = lblEmail.getText().toString().trim();
            String password = lblContraseña.getText().toString().trim();

            if (email.equals("Admin@gmail.com") && password.equals("admin123")) {
                Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, TareasActivity.class);
                startActivity(intent);
                finish();
            } else {
                lblEmail.setError("Correo o contraseña incorrectos");
                lblContraseña.setError("Correo o contraseña incorrectos");
                Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
