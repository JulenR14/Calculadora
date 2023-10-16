package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //atributos



    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnMas, btnIgual, btnC;
    private TextView textoOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos todos los Objetos
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnMas = findViewById(R.id.buttonMas);
        btnIgual = findViewById(R.id.buttonIgual);
        btnC = findViewById(R.id.buttonC);
        textoOperacion = findViewById(R.id.textView);


        //llamamos a los metodos cuando se aprete a un boton de los creados
        Calculator.ejecutarBoton(btn0, textoOperacion);
        Calculator.ejecutarBoton(btn1, textoOperacion);
        Calculator.ejecutarBoton(btn2, textoOperacion);
        Calculator.ejecutarBoton(btn3, textoOperacion);
        Calculator.ejecutarBoton(btn4, textoOperacion);
        Calculator.ejecutarBoton(btn5, textoOperacion);
        Calculator.ejecutarBoton(btn6, textoOperacion);
        Calculator.ejecutarBoton(btn7, textoOperacion);
        Calculator.ejecutarBoton(btn8, textoOperacion);
        Calculator.ejecutarBoton(btn9, textoOperacion);
        Calculator.ejecutarBoton(btnMas, textoOperacion);
        Calculator.limpiarCalculadora(btnC, textoOperacion);
        Calculator.resultadoOperacion(btnIgual, textoOperacion);
    }
}