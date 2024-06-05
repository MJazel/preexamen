package com.example.recibodenomina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetallesActivity extends AppCompatActivity {
    private EditText editTextNumRecibo, editTextNombre, editTextHorasTrabajadas, editTextHorasExtras;
    private EditText editTextSubtotal, editTextImpuesto, editTextTotal;
    private TextView textViewNombreTrabajador;
    private RadioGroup radioGroupPuesto;
    private RadioButton radioButtonAuxiliar, radioButtonAlbanil, radioButtonIngObra;

    private Button buttonCalcular, buttonLimpiar, buttonRegresar;
    private String nombre;

    private static final double PAGO_BASE = 200;
    private static final double PORCENTAJE_IMPUESTO = 0.16;
    private static final double INCREMENTO_AUXILIAR = 0.20;
    private static final double INCREMENTO_ALBANIL = 0.50;
    private static final double INCREMENTO_ING_OBRA = 1.00;

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
        radioButtonIngObra = findViewById(R.id.radioButtonIngObra);
        radioButtonAlbanil = findViewById(R.id.radioButtonAlbanil);
        radioButtonAuxiliar = findViewById(R.id.radioButtonAuxiliar);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        textViewNombreTrabajador.setText(nombre);
        editTextNombre.setText(nombre);

        int numRecibo = generarNumeroRecibo();
        editTextNumRecibo.setText(String.valueOf(numRecibo));

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

    private int generarNumeroRecibo() {
        Random random = new Random();
        return random.nextInt(100000);  // Genera un número de recibo aleatorio entre 0 y 99999
    }

    private void calcularPago() {
        try {
            float horasTrabajadas = Float.parseFloat(editTextHorasTrabajadas.getText().toString());
            float horasExtras = Float.parseFloat(editTextHorasExtras.getText().toString());
            int puesto = getPuestoSeleccionado();

            double incremento = 0;
            if (puesto == R.id.radioButtonAuxiliar) {
                incremento = INCREMENTO_AUXILIAR;
            } else if (puesto == R.id.radioButtonAlbanil) {
                incremento = INCREMENTO_ALBANIL;
            } else if (puesto == R.id.radioButtonIngObra) {
                incremento = INCREMENTO_ING_OBRA;
            } else {
                Toast.makeText(this, "Seleccione un puesto", Toast.LENGTH_SHORT).show();
                return;
            }

            double pagoPorHora = PAGO_BASE + (PAGO_BASE * incremento);
            double subtotal = (pagoPorHora * horasTrabajadas) + (pagoPorHora * horasExtras * 2);
            double impuesto = subtotal * PORCENTAJE_IMPUESTO;
            double total = subtotal - impuesto;

            editTextSubtotal.setText(String.format("%.2f", subtotal));
            editTextImpuesto.setText(String.format("%.2f", impuesto));
            editTextTotal.setText(String.format("%.2f", total));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese valores válidos para las horas trabajadas y extras", Toast.LENGTH_SHORT).show();
        }
    }

    private int getPuestoSeleccionado() {
        return radioGroupPuesto.getCheckedRadioButtonId();
    }

    private void limpiarCampos() {
        editTextNumRecibo.setText(String.valueOf(generarNumeroRecibo())); // Genera un nuevo número de recibo
        editTextNombre.setText(nombre); // Restablece el nombre
        editTextHorasTrabajadas.setText("");
        editTextHorasExtras.setText("");
        editTextSubtotal.setText("");
        editTextImpuesto.setText("");
        editTextTotal.setText("");
        radioGroupPuesto.clearCheck();
    }
}
