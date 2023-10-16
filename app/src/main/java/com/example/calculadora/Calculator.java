package com.example.calculadora;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    static String muestraOperacion = "";
    static int totalSuma = 0;
    static private ArrayList<String> operacion = new ArrayList<String>();


    //metodo que ejecura el metodo onClick del boton que se le pasa por parametro
    public static void ejecutarBoton(Button b, TextView textoOperacion){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anidarOperacion(b.getText().toString(), textoOperacion);
            }//llamamos al metodo que nos añadira el valor a la cadena de la operacion
        });
    }

    //metodo que ira creando la operacion para calcularla mas adelante
    public static void anidarOperacion(String valor, TextView textoOperacion){

        //comparamos que si el valor es una + ya que si es asi tendremos que comprobar antes de insertarlo en la operacion
        if(valor.equals("+")){
            //si la lista no esta vacia querra decir que ya habra algun numero delante,
            //asi que podermos realizar la ultima comprobacion
            if(!operacion.isEmpty()){
                //si el ultimo valor de la lista no es una + podremos añadir el valor +
                //ya que no pueden haber dos + seguidas
                if(!operacion.get(operacion.size()-1).equals("+")){
                    //cuando pase todas las comprobaciones añadimos el + tanto a el array como a el String
                    operacion.add(valor);
                    muestraOperacion += valor;
                }
            }
        }else{
            //si el valor no es + no tendremos problemas, simplemente añadimos el numero a la cadena
            operacion.add(valor);
            muestraOperacion += valor;
        }
        //cambiamos el texto de el textView
        textoOperacion.setText(muestraOperacion);
    }

    //metodo para limpiar la calculadora
    public static void limpiarCalculadora(Button c, TextView textoOperacion){
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
    public static void resultadoOperacion(Button igual, TextView textoOperacion){

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cambiamos el valor de nuestra operacion al resultado de la suma
                muestraOperacion = String.valueOf(calcularOperacion(muestraOperacion));
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

    public static int calcularOperacion(String muestra){
        int total = 0;
        String[] operacion = muestra.split("");
        ArrayList<String> soloSumas = new ArrayList<String>();
        int contadorSoloSum = 0;
        int resultadoMultiplicacion;

        for (int i = 0; i < operacion.length; i++ ){
            if(operacion[i].equals("*")){
                resultadoMultiplicacion = Integer.parseInt(operacion[i - 1]) * Integer.parseInt(operacion[i + 1]);
                soloSumas.set(contadorSoloSum - 1, String.valueOf(resultadoMultiplicacion));
                i++;
            }else if (!operacion[i].equals("+")){
                soloSumas.add(operacion[i]);
            }
            contadorSoloSum++;
        }

        return soloSumas.stream().mapToInt(Integer::parseInt).sum();


        //return Arrays.stream(muestra.split("\\+")).mapToInt(n -> Integer.parseInt(n)).sum();
    }
}

