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

public class RegisterActivity extends AppCompatActivity {

    private Button btnVer1;

    private EditText lblPassword;
    private EditText lblRepetirPassword;

    private boolean isPasswordVisible1 = false;
    private Button btbVolverR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        btnVer1 = findViewById(R.id.btnVer1);
        lblPassword = findViewById(R.id.lblPassword);
        lblRepetirPassword = findViewById(R.id.lblRepetirPassword);
        btbVolverR = findViewById(R.id.btbVolverR);

        // Mostrar/Ocultar contraseña - lblPassword
        btnVer1.setOnClickListener(v -> {
            if (isPasswordVisible1) {
                lblPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                lblRepetirPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnVer1.setText("👁");
            } else {
                lblPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                lblRepetirPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnVer1.setText("🙈");
            }
            lblPassword.setSelection(lblPassword.getText().length());
            lblRepetirPassword.setSelection(lblRepetirPassword.getText().length());
            isPasswordVisible1 = !isPasswordVisible1;
        });

        // Volver a MainActivity
        btbVolverR.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
