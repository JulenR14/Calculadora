package com.example.calculadora;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    //atributos
    static String muestraOperacion = "";
    static int totalSuma = 0;
    static private ArrayList<String> operacion = new ArrayList<String>();



    /*
    * Ejecuta el boton que se le pasa por parametro y llama al metodo anidarOperacion
    *
    * @param b boton que se le pasa por parametro
    * @param textoOperacion textView que se le pasa por parametro
    * @return void
    *
     */
    public static void ejecutarBoton(Button b, TextView textoOperacion){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anidarOperacion(b.getText().toString(), textoOperacion);
            }//llamamos al metodo que nos añadira el valor del boton a la cadena de la operacion
        });
    }

    /*
    *Metodo que va añadiendo el valor de la operacion a un String y a un array
    * para poder realizar las operaciones
    *
    * @param valor valor que se le pasa por parametro
    * @param textoOperacion textView que se le pasa por parametro
    * @return void
     */
    public static void anidarOperacion(String valor, TextView textoOperacion){

        //comparamos que si el valor es un simbolo ya que si es asi tendremos que comprobar antes de insertarlo en la operacion
        switch (valor){
            case "+":
                //si la lista no esta vacia querra decir que ya habra algun numero delante,
                //asi que podermos realizar la ultima comprobacion
                if(!operacion.isEmpty()){
                    //si el ultimo valor de la lista no es una + podremos añadir el valor +
                    //ya que no pueden haber dos + seguidas
                    if(!operacion.get(operacion.size()-1).equals("+") && !operacion.get(operacion.size()-1).equals("*")){
                        //cuando pase todas las comprobaciones añadimos el + tanto a el array como a el String
                        operacion.add(valor);
                        muestraOperacion += " " + valor + " ";
                    }
                }
                break;
            case "*":
                //si la lista no esta vacia querra decir que ya habra algun numero delante,
                if(!operacion.isEmpty()){
                    //si el ultimo valor de la lista no es un * podremos añadir el valor *
                    //ya que no pueden haber dos * seguidas
                    if(!operacion.get(operacion.size()-1).equals("*") && !operacion.get(operacion.size()-1).equals("+")){
                        //cuando pase todas las comprobaciones añadimos el * tanto a el array como a el String
                        operacion.add(valor);
                        muestraOperacion += " " + valor + " ";
                    }
                }
                break;
            default:
                //si el valor no es un simbolo no tendremos problemas, simplemente añadimos el numero a la cadena
                operacion.add(valor);
                muestraOperacion += valor;
                break;
        }

        //cambiamos el texto de el textView
        textoOperacion.setText(muestraOperacion);
    }

    /*
    * Metodo que se ocupa de limpiar el array y el String que tenemos para la operacion
    *
    * @param c boton que se le pasa por parametro
    * @param textoOperacion textView que se le pasa por parametro
    * @return void
     */
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

    /*
    *Metodo que muestra el resultado de la operacion en el textView de la carlculadora
    * y una vez cambiado el valor de el textView, limpia el array y el String
    * añadiendo el resultado de la operacion en la primera posicion del array
    *
    * @param igual boton que se le pasa por parametro
    * @param textoOperacion textView que se le pasa por parametro
    * @return void
     */
    public static void resultadoOperacion(Button igual, TextView textoOperacion){

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!muestraOperacion.isEmpty()){
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
            }
        });

    }

    /*
    * Metodo que se ocupa de realizar la operacion que se le pasa por parametro y devolver el resultado
    *
    * @param muestra String que se le pasa por parametro
    * @return int
     */
    public static int calcularOperacion(String muestra){

        //separamos la operacion en un array de Strings
        String[] operacion = muestra.split(" ");
        //creamos un arraylist para poder realizar las operaciones
        ArrayList<String> soloSumas = new ArrayList<String>();
        //creamos un contador para recorrer soloSumas y una variable par almacenar resultados de la multiplicacion
        int contadorSoloSum = 0;

        //recorremos el array de la operacion
        for (int i = 0; i < operacion.length; i++ ){

            if(operacion[i].equals("*")){
                //si el valor es * realizamos la multiplicacion y almacenamos el resultado en la posicion anterior
                soloSumas.set(contadorSoloSum - 1, String.valueOf(Integer.parseInt(soloSumas.get(contadorSoloSum - 1)) * Integer.parseInt(operacion[i + 1])));
                //incrementamos el contador para que no se vuelva a calcular el numero de despues de la multiplicacion
                i++;
                //decrementamos el contador para que no nos apunte a una posicion inexistente de el array
                contadorSoloSum--;
            }else if (!operacion[i].equals("+")){
                //si el valor no es + ni * lo añadimos a el arraylist
                soloSumas.add(operacion[i]);
            }else{
                //si el valor es + decrementamos el contador para que no nos apunte a una posicion inexistente de el array
                contadorSoloSum--;
            }
            //vamos sumando el contador para ir añadiendo en las posiciones los valores
            contadorSoloSum++;
        }

        //cuando termine el bucle soloSumas lo tumaremos con programacion funcional, ya que solo tendremos valores a sumar
        return soloSumas.stream().mapToInt(Integer::parseInt).sum();
    }
}

