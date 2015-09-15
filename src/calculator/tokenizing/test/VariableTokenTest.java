package calculator.tokenizing.test;

import calculator.tokenizing.src.Token;
import calculator.tokenizing.src.VariableToken;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 05/09/2015.
 * Clase de prueba para la clase "VariableToken".
 */
public class VariableTokenTest {

    /**
     * Método que prueba el método "getType" para la clase
     * "VariableToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetType() throws Exception {

        String variablesSet = "x#";

        int limit = variablesSet.length();

        Random random = new Random();
        int key = random.nextInt(limit);

        char current = variablesSet.charAt(key);

        Token variable = new VariableToken(current);

        //El valor de "type" estará en el conjunto {-1,1}
        int type = variable.getType();
        System.out.println(current);

        if(type > 0){
            assertEquals(key + 1, type);
        }else{
            assertEquals(-1, type);
        }
    }

    /**
     * Método para probar el método "getValue"
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetValue() throws Exception {
        char test = 'x';

        Token variable = new VariableToken(test);

        char returnValue = (char)variable.getValue();

        assertEquals(test, returnValue);
    }

    /**
     * Método para probar el método "getEvaluation"
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetEvaluation() throws Exception {
        char test = 'x';

        VariableToken variable = new VariableToken(test);

        variable.setEvaluation(4.56);

        double actualValue = variable.getEvaluation();

        assertEquals(4.56, actualValue, 0.0001);
    }

    /**
     * Método de prueba para los métodos "equals" y "hashCode".
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testEquals_Symmetric() throws Exception {
        VariableToken variable1 = new VariableToken('x');
        VariableToken variable2 = new VariableToken('x');

        assertTrue(variable1.equals(variable2) && variable2.equals(variable1));
        assertTrue(variable1.hashCode() == variable2.hashCode());
    }
}