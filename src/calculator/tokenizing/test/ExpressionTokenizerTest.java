package calculator.tokenizing.test;

import calculator.tokenizing.src.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase de prueba para la clase "ExpressionTokenizer".
 */
public class ExpressionTokenizerTest {

    /**
     * M�todo que prueba "getTokenList".
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetTokensList() throws Exception {
        ExpressionTokenizer expTokenizer;

        String clean = "x+2",
               dirty = "test";

        LinkedList<Token> cleanList = new LinkedList<>();
        cleanList.addLast(new VariableToken('x'));
        cleanList.addLast(new OperatorToken('+'));
        cleanList.addLast(new NumberToken(2));

        Random random = new Random();
        int bound = random.nextInt(2);

        if(bound == 0){                 //Prueba con datos limpios
            expTokenizer = new ExpressionTokenizer(clean);
            LinkedList<Token> testing = expTokenizer.getTokensList();

            assertEquals(cleanList, testing);
        }else{                          //Prueba con datos sucios
            expTokenizer = new ExpressionTokenizer(dirty);
            LinkedList<Token> testing = expTokenizer.getTokensList();

            assertEquals(null, testing);
        }
    }
}