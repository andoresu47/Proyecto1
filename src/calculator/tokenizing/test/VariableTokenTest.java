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

        if(type > 0){
            assertEquals(key + 1, type);
        }else{
            assertEquals(-1, type);
        }
    }
}