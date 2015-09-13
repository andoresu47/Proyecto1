package calculator.tokenizing.test;

import calculator.tokenizing.src.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 05/09/2015.
 * Clase de prueba para la clase "ExpressionTokenizer".
 */
public class ExpressionTokenizerTest {

    /**
     * Método que prueba "getTokenList".
     * Se verifica que la lista de Tokens esté en el orden
     * correcto; es decir, como la cadena fue introducida por
     * el usuario. Así mismo, que solo se tomen en cuenta caracteres
     * permitidos por la gramática.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetTokensList() throws Exception {
        ExpressionTokenizer expTokenizer;

        String clean = "sin(x + 4.56)",
               dirty = "sn(x + 4.5.8.)";

        LinkedList<Token> cleanList = new LinkedList<>();
        cleanList.addLast(new FunctionToken("sin"));
        cleanList.addLast(new OperatorToken('('));
        cleanList.addLast(new VariableToken('x'));
        cleanList.addLast(new OperatorToken('+'));
        cleanList.addLast(new NumberToken(4.56));
        cleanList.addLast(new OperatorToken(')'));

        Random random = new Random();
        int bound = random.nextInt(2);

        if(bound == 0){                 //Prueba con datos limpios
            expTokenizer = new ExpressionTokenizer(clean);
            LinkedList<Token> testing = expTokenizer.getTokensList();
            System.out.println("Datos limpios");

            assertEquals(cleanList, testing);
        }else{                          //Prueba con datos sucios
            expTokenizer = new ExpressionTokenizer(dirty);
            LinkedList<Token> testing = expTokenizer.getTokensList();

            System.out.println("Datos sucios.");
            assertEquals(null, testing);
        }
    }
}