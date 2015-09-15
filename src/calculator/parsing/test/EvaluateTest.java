package calculator.parsing.test;

import calculator.parsing.src.Evaluate;
import calculator.tokenizing.src.ExpressionTokenizer;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 13/09/2015.
 * Clase de prueba para la clase "Evaluate".
 */
public class EvaluateTest {
    /**
     * Método que prueba "infixToPostfix".
     * Se verifica que en efecto se transforme una lista de tokens
     * en notación infija a una lista de tokens en notación posfija.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testInfixToPostfix() throws Exception {
        String infixTokens = "3 + sqr(4 * 2) / (1 - 5) ^ 2 ^ 3",
               postfixTokens = "3 4 2 * sqr 1 5 - 2 3 ^ ^ / +";

        ExpressionTokenizer tokenizer;

        tokenizer = new ExpressionTokenizer(infixTokens);
        LinkedList<Token> infix = tokenizer.getTokensList();

        tokenizer = new ExpressionTokenizer(postfixTokens);
        LinkedList<Token> expectedPostfix = tokenizer.getTokensList();

        LinkedList<Token> postfix = Evaluate.infixToPostfix(infix);

        assertEquals(expectedPostfix, postfix);
    }

    /**
     * Método que prueba "postfixEvaluation".
     * Se verifica que en efecto se evalúe correctamente una expresión
     * tokenizada en notación posfija.
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testPostfixEvaluation() throws Exception {
        String infixTokens = "sin(x * 3.1416) + 2^3^2";

        ExpressionTokenizer tokenizer = new ExpressionTokenizer(infixTokens);
        LinkedList<Token> infix = tokenizer.getTokensList();

        double actual = Evaluate.postfixEvaluation(infix, 2);
        double expected = 512;

        assertEquals(expected, actual, 0.001);
    }
}