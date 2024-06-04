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

public class DetallesActivity extends AppCompatActivity {

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
                int numRecibo = Integer.parseInt(editTextNumRecibo.getText().toString());
                int horasTrabajadas = Integer.parseInt(editTextHorasTrabajadas.getText().toString());
                int horasExtras = Integer.parseInt(editTextHorasExtras.getText().toString());
                int puesto = 0;

                switch (radioGroupPuesto.getCheckedRadioButtonId()) {
                    case (R.id.radioButtonAuxiliar):
                        puesto = 1;
                        break;
                    case R.id.radioButtonAlbanil:
                        puesto = 2;
                        break;
                    case R.id.radioButtonIngObra:
                        puesto = 3;
                        break;
                }

                ReciboDeNomina recibo = new ReciboDeNomina(numRecibo, nombre, horasTrabajadas, horasExtras, puesto);

                double subtotal = recibo.calcularSubtotal();
                double impuesto = recibo.calcularImpuesto();
                double total = recibo.calcularTotalAPagar();

                textViewSubtotal.setText("Subtotal: " + subtotal);
                textViewImpuesto.setText("Impuesto: " + impuesto);
                textViewTotal.setText("Total a pagar: " + total);
            }
        });
    }
}
