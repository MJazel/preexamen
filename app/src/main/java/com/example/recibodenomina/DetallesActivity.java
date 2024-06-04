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

    private EditText editTextNumRecibo, editTextHorasTrabajadas, editTextHorasExtras;
    private RadioGroup radioGroupPuesto;
    private TextView textViewSubtotal, textViewImpuesto, textViewTotal;
    private Button buttonCalcular;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        editTextNumRecibo = findViewById(R.id.editTextNumRecibo);
        editTextHorasTrabajadas = findViewById(R.id.editTextHorasTrabajadas);
        editTextHorasExtras = findViewById(R.id.editTextHorasExtras);
        radioGroupPuesto = findViewById(R.id.radioGroupPuesto);
        textViewSubtotal = findViewById(R.id.textViewSubtotal);
        textViewImpuesto = findViewById(R.id.textViewImpuesto);
        textViewTotal = findViewById(R.id.textViewTotal);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numRecibo = Integer.parseInt(editTextNumRecibo.getText().toString());
                    int horasTrabajadas = Integer.parseInt(editTextHorasTrabajadas.getText().toString());
                    int horasExtras = Integer.parseInt(editTextHorasExtras.getText().toString());
                    int puesto = obtenerPuestoSeleccionado();

                    if (puesto != 0) {
                        ReciboDeNomina recibo = new ReciboDeNomina(numRecibo, nombre, horasTrabajadas, horasExtras, puesto);

                        double subtotal = recibo.calcularSubtotal();
                        double impuesto = recibo.calcularImpuesto();
                        double total = recibo.calcularTotalAPagar();

                        textViewSubtotal.setText("Subtotal: " + subtotal);
                        textViewImpuesto.setText("Impuesto: " + impuesto);
                        textViewTotal.setText("Total a pagar: " + total);
                    } else {
                        Toast.makeText(DetalleActivity.this, "Seleccione un puesto", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(DetalleActivity.this, "Por favor, ingrese todos los campos correctamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int obtenerPuestoSeleccionado() {
        int selectedId = radioGroupPuesto.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonAuxiliar) {
            return 1;
        } else if (selectedId == R.id.radioButtonAlbanil) {
            return 2;
        } else if (selectedId == R.id.radioButtonIngObra) {
            return 3;
        } else {
            return 0; // Ningún puesto seleccionado
        }
    }
}

