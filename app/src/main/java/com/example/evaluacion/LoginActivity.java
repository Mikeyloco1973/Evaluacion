package com.example.evaluacion;

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
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Mantener compatibilidad con EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        lblContraseña = findViewById(R.id.lblContraseña);
        btnVer = findViewById(R.id.btnVer);

        // Lógica para mostrar/ocultar contraseña
        btnVer.setOnClickListener(v -> {
            if (isPasswordVisible) {
                lblContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnVer.setText("👁"); // Ícono para ocultar
            } else {
                lblContraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnVer.setText("🙈"); // Ícono para mostrar
            }
            lblContraseña.setSelection(lblContraseña.getText().length());
            isPasswordVisible = !isPasswordVisible;
        });
    }
}
