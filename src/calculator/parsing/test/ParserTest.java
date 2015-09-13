package calculator.parsing.test;

import calculator.parsing.src.Parser;
import calculator.tokenizing.src.ExpressionTokenizer;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 13/09/2015.
 * Clase de prueba para la clase "Parser".
 */
public class ParserTest {
    /**
     * Método que prueba "isValid".
     * Se verifica que para una entrada válida el resultado sea
     * el correcto; lo mismo para una entrada inválida.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testIsValid() throws Exception {
        String clean = "sin(x ^ 4.56)",
                dirty = "sinx + 4.56)(";

        ExpressionTokenizer expTokenizer;
        Parser parser;

        Random random = new Random();
        int bound = random.nextInt(2);

        if(bound == 0){
            expTokenizer = new ExpressionTokenizer(clean);
            parser = new Parser(expTokenizer.getTokensList());
            System.out.println("Datos limpios");

            assertTrue(parser.isValid());

        }else{
            expTokenizer = new ExpressionTokenizer(dirty);
            parser = new Parser(expTokenizer.getTokensList());
            System.out.println("Datos sucios");

            assertFalse(parser.isValid());
        }
    }
}