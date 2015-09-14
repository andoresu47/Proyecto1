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
}