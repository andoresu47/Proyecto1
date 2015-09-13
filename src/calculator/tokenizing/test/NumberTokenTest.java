package calculator.tokenizing.test;

import calculator.tokenizing.src.NumberToken;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 05/09/2015.
 * Clase de prueba para la clase "NumberToken".
 */
public class NumberTokenTest {

    /**
     * Método que prueba el método "getType" para la clase
     * "NumberToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetType() throws Exception {

        Random random = new Random();

        double testDouble = random.nextDouble();

        Token number = new NumberToken(testDouble);

        int type = number.getType();

        assertEquals(0, type);
    }

    /**
     * Método que prueba el método "getValue" para la clase
     * "NumberToken".
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetValue() throws Exception {
        Random random = new Random();

        double testDouble = random.nextDouble();

        Token number = new NumberToken(testDouble);

        double value = (double)number.getValue();

        assertEquals(value, testDouble, 0.001);
    }

    /**
     * Método de prueba para los métodos "equals" y "hashCode".
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testEquals_Symmetric() throws Exception {
        NumberToken number1 = new NumberToken(4.56);
        NumberToken number2 = new NumberToken(4.56);

        assertTrue(number1.equals(number2) && number2.equals(number1));
        assertTrue(number1.hashCode() == number2.hashCode());
    }
}