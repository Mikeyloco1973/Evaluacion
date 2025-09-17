package com.example.evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class TareasActivity extends AppCompatActivity {

    private Button btbVolver, btnAgregar;
    private EditText edtNuevaTarea;
    private ProgressBar progresoTareas;
    private TextView txtPorcentaje;
    private LinearLayout listaTareas;

    private List<CheckBox> tareasCheckboxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tareas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btbVolver = findViewById(R.id.btbVolver);
        btnAgregar = findViewById(R.id.btnAgregar);
        edtNuevaTarea = findViewById(R.id.edtNuevaTarea);
        progresoTareas = findViewById(R.id.progresoTareas);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        listaTareas = findViewById(R.id.listaTareas);

        btnAgregar.setOnClickListener(v -> {
            String descripcion = edtNuevaTarea.getText().toString().trim();
            if (!descripcion.isEmpty()) {
                agregarTarea(descripcion);
                edtNuevaTarea.setText("");
            }
        });

        btbVolver.setOnClickListener(v -> {
            Intent intent = new Intent(TareasActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void agregarTarea(String descripcion) {
        // Crear layout horizontal para la tarea
        LinearLayout tareaLayout = new LinearLayout(this);
        tareaLayout.setOrientation(LinearLayout.HORIZONTAL);
        tareaLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        tareaLayout.setPadding(8, 16, 8, 16);

        // Crear CheckBox
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Crear EditText editable
        EditText editText = new EditText(this);
        editText.setText(descripcion);
        editText.setTextSize(16);
        editText.setTextColor(getResources().getColor(android.R.color.black));
        editText.setTypeface(null, android.graphics.Typeface.BOLD);
        editText.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        ));

        // Crear botón eliminar con ❌ roja
        Button btnEliminar = new Button(this);
        btnEliminar.setText("❌");
        btnEliminar.setTextSize(18);
        btnEliminar.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        btnEliminar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // Agregar vistas al layout horizontal
        tareaLayout.addView(checkBox);
        tareaLayout.addView(editText);
        tareaLayout.addView(btnEliminar);

        // Agregar layout al contenedor principal
        listaTareas.addView(tareaLayout);
        tareasCheckboxes.add(checkBox);
        actualizarProgreso();

        // Listener para eliminar tarea con confirmación
        btnEliminar.setOnClickListener(v -> {
            new AlertDialog.Builder(TareasActivity.this)
                    .setTitle("Eliminar tarea")
                    .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        listaTareas.removeView(tareaLayout);
                        tareasCheckboxes.remove(checkBox);
                        actualizarProgreso();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        // Listener para marcar como completada
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> actualizarProgreso());
    }

    private void actualizarProgreso() {
        int total = tareasCheckboxes.size();
        int completadas = 0;
        for (CheckBox tarea : tareasCheckboxes) {
            if (tarea.isChecked()) completadas++;
        }

        int porcentaje = total == 0 ? 0 : (completadas * 100 / total);
        progresoTareas.setProgress(porcentaje);
        txtPorcentaje.setText(porcentaje + "% completado");
    }
}
