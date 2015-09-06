package calculator.tokenizing.test;

import calculator.tokenizing.src.FunctionToken;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 05/09/2015.
 * Clase de prueba para la clase "FunctionToken".
 */
public class FunctionTokenTest {

    /**
     * Método que prueba el método "getType" para la clase
     * "FunctionToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetType() throws Exception {
        String[] functionsSet = {"sin", "cos", "tan", "cot", "sec", "csc", "sqr", "#"};
        int limit = functionsSet.length;

        Random random = new Random();
        int key = random.nextInt(limit);

        String current = functionsSet[key];

        Token function = new FunctionToken(current);

        //El valor de "type" estará en el conjunto {-1,9,10,11,12,13,14,15}
        int type = function.getType();

        if(type > 0){
            //Se le suma un 2 por la declaración de constantes en la clase "Token"
            assertEquals(key + 9, type);
        }else{
            assertEquals(-1, type);
        }
    }

    /**
     * Método que prueba el método "getValue".
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetValue() throws Exception {
        String test = "sin";

        Token function = new FunctionToken(test);

        String returnValue = (String)function.getValue();

        assertEquals(test, returnValue);
    }
}