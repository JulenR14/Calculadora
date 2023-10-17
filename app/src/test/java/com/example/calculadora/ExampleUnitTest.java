package com.example.calculadora;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testAdd2Operands() {

        int total = Calculator.calcularOperacion("5+3");

        assertEquals("X + Y operations not working correctly", 8, total);
    }

    @Test
    public void testAdd1Operands() {

        int total = Calculator.calcularOperacion("4+3+1");

        assertEquals("+X operations not working correctly", 8, total);
    }

    @Test
    public void testMult20Operands() {

        int p1 = Calculator.calcularOperacion("4 * 2");
        int p2 = Calculator.calcularOperacion("2 * 3");
        int p3 = Calculator.calcularOperacion("1 * 2 * 8");
        int p4 = Calculator.calcularOperacion("2 * 2 + 3");
        int p5 = Calculator.calcularOperacion("3 + 2 * 2");
        int p6 = Calculator.calcularOperacion("3 + 2 * 2 + 4");


        assertEquals("4 * X operations not working correctly", 8, p1);
        assertEquals("4 * X operations not working correctly", 6, p2);
        assertEquals("4 * X operations not working correctly", 16, p3);
        assertEquals("4 * X operations not working correctly", 7, p4);
        assertEquals("4 * X operations not working correctly", 7, p5);
        assertEquals("4 * X operations not working correctly", 11, p6);

    }

}