package com.example.recibodenomina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleActivity extends AppCompatActivity {

    private EditText editTextNumRecibo, editTextNombre, editTextHorasTrabajadas, editTextHorasExtras;
    private EditText editTextSubtotal, editTextImpuesto, editTextTotal;
    private TextView textViewNombreTrabajador;
    private RadioGroup radioGroupPuesto;
    private Button buttonCalcular, buttonLimpiar, buttonRegresar;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        editTextNumRecibo = findViewById(R.id.editTextNumRecibo);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextHorasTrabajadas = findViewById(R.id.editTextHorasTrabajadas);
        editTextHorasExtras = findViewById(R.id.editTextHorasExtras);
        editTextSubtotal = findViewById(R.id.editTextSubtotal);
        editTextImpuesto = findViewById(R.id.editTextImpuesto);
        editTextTotal = findViewById(R.id.editTextTotal);
        textViewNombreTrabajador = findViewById(R.id.textViewNombreTrabajador);
        radioGroupPuesto = findViewById(R.id.radioGroupPuesto);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);
        buttonRegresar = findViewById(R.id.buttonRegresar);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        textViewNombreTrabajador.setText(nombre);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPago();
            }
        });

        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        buttonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calcularPago() {
        // Lógica de cálculo de pago aquí
    }

    private void limpiarCampos() {
        editTextNumRecibo.setText("");
        editTextNombre.setText("");
        editTextHorasTrabajadas.setText("");
        editTextHorasExtras.setText("");
        editTextSubtotal.setText("");
        editTextImpuesto.setText("");
        editTextTotal.setText("");
        radioGroupPuesto.clearCheck();
    }
}


