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
    String muestraOperacion = "";
    int totalSuma = 0;
    private ArrayList<String> operacion = new ArrayList<String>();
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
        ejecutarBoton(btn0);
        ejecutarBoton(btn1);
        ejecutarBoton(btn2);
        ejecutarBoton(btn3);
        ejecutarBoton(btn4);
        ejecutarBoton(btn5);
        ejecutarBoton(btn6);
        ejecutarBoton(btn7);
        ejecutarBoton(btn8);
        ejecutarBoton(btn9);
        ejecutarBoton(btnMas);
        limpiarCalculadora(btnC);
        resultadoOperacion(btnIgual);
    }

    //metodo que ejecura el metodo onClick del boton que se le pasa por parametro
    public void ejecutarBoton(Button b){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anidarOperacion(b.getText().toString());
            }//llamamos al metodo que nos añadira el valor a la cadena de la operacion
        });
    }

    //metodo que ira creando la operacion para calcularla mas adelante
    public void anidarOperacion(String valor){

        //comparamos que si el valor es una + ya que si es asi tendremos que comprobar antes de insertarlo en la operacion
        if(valor.equals("+")){
            //si la lista no esta vacia querra decir que ya habra algun numero delante,
            //asi que podermos realizar la ultima comprobacion
            if(!this.operacion.isEmpty()){
                //si el ultimo valor de la lista no es una + podremos añadir el valor +
                //ya que no pueden haber dos + seguidas
                if(!this.operacion.get(this.operacion.size()-1).equals("+")){
                    //cuando pase todas las comprobaciones añadimos el + tanto a el array como a el String
                    this.operacion.add(valor);
                    muestraOperacion += valor;
                }
            }
        }else{
            //si el valor no es + no tendremos problemas, simplemente añadimos el numero a la cadena
            this.operacion.add(valor);
            muestraOperacion += valor;
        }
        //cambiamos el texto de el textView
        textoOperacion.setText(muestraOperacion);
    }

    //metodo para limpiar la calculadora
    public void limpiarCalculadora(Button c){
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //limpiamos el array, quitamos el valor de el String en la que tenemos la operacion
                //cambiamos el texto del textView al valor de nuestraOperacion actual
                operacion.clear();
                muestraOperacion = "";
                textoOperacion.setText(muestraOperacion);
            }
        });
    }

    //metodo que muestra el resultado de la operacion
    public void resultadoOperacion(Button igual){


        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //variable en la que iremos sumando lo valores
                totalSuma = Arrays.stream(muestraOperacion.split("\\+")).mapToInt(n -> Integer.parseInt(n)).sum();
                //cambiamos el valor de nuestra operacion al resultado de la suma
                muestraOperacion = String.valueOf(totalSuma);
                //modficamos el textView con el resultado de la operacion
                textoOperacion.setText(muestraOperacion);
                //limpiamos la operacion anterior, porque ya no la necesitamos
                operacion.clear();
                //y añadimos en la primera posicion del array el valor del resultado
                //por si quiere seguir operando con el resultado
                operacion.add(muestraOperacion);
                //damos valor 0 al total para poder seguir operando
                totalSuma = 0;
            }
        });

    }
}