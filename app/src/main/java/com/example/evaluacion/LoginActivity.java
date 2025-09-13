package com.example.evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText lblContraseña;
    private Button btnVer;
    private Button btbVolver;
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
        lblContraseña = findViewById(R.id.lblContraseña);
        btnVer = findViewById(R.id.btnVer);
        btbVolver = findViewById(R.id.btbVolver);

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
            finish(); // Opcional: cierra LoginActivity para que no quede en el back stack
        });
    }
}
