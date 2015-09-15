package calculator.parsing.src;

import calculator.tokenizing.src.FunctionToken;
import calculator.tokenizing.src.NumberToken;
import calculator.tokenizing.src.OperatorToken;
import calculator.tokenizing.src.Token;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Andrés on 13/09/2015.
 * Clase que toma expresiones en notación infija, las
 * convierte a posfija y las evalúa.
 */
public class Evaluate {

    public static LinkedList<Token> infixToPostfix(LinkedList<Token> infixTokens){
        Stack<Token> operatorStack = new Stack<>();
        LinkedList<Token> postfixTokens = new LinkedList<>();

        for (Token present : infixTokens) {
            if (present instanceof FunctionToken) {
                operatorStack.push(present);
            }
            if (present.getType() == Token.NUMBER) {
                postfixTokens.addLast(present);
            }
            if (present.getType() == Token.VARIABLE) {
                postfixTokens.addLast(present);
            }
            if (present.getType() == Token.PLUS ||
                    present.getType() == Token.MINUS ||
                    present.getType() == Token.PRODUCT ||
                    present.getType() == Token.DIVISION ||
                    present.getType() == Token.EXP) {
                OperatorToken operator1 = (OperatorToken) present;
                OperatorToken operator2;

                while (!operatorStack.isEmpty()) {
                    operator2 = (OperatorToken) operatorStack.peek();
                    if ((operator1.getAssociativity() == OperatorToken.LEFT
                            && operator1.getPrecedence() <= operator2.getPrecedence())
                            || (operator1.getAssociativity() == OperatorToken.RIGHT
                            && operator1.getPrecedence() < operator2.getPrecedence())) {
                        postfixTokens.addLast(operatorStack.pop());
                    } else {
                        break;
                    }
                }
                operatorStack.push(present);
            }
            if (present.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(present);
            }
            if (present.getType() == Token.RIGHT_PARENTHESIS) {
                while(operatorStack.peek().getType() != Token.LEFT_PARENTHESIS){
                    postfixTokens.addLast(operatorStack.pop());
                }
                operatorStack.pop();
                if(operatorStack.peek() instanceof FunctionToken){
                    postfixTokens.addLast(operatorStack.pop());
                }
            }
        }
        while(!operatorStack.isEmpty()){
            postfixTokens.addLast(operatorStack.pop());
        }
        return postfixTokens;
    }

    /**
     * Método que evalúa una expresión representada por una lista
     * de Tokens en notación infija. Para esto, dicha lista se convierte a
     * notación posfija y se realiza la evaluación por medio de una pila.
     * @param postfixTokens - lista de tokens en notación infija.
     * @param currentEvaluation - valor que tomará la variable, en caso de haber.
     * @return double - resultado de la evaluación de la expresión.
     * @throws ArithmeticException si se detecta una potencia fraccionaria de un
     * número negativo, o alguna división entre cero.
     */
    public static double postfixEvaluation(LinkedList<Token> postfixTokens, double currentEvaluation) throws ArithmeticException{
        Stack<Token> numbersStack = new Stack<>();

        for(Token present : postfixTokens){
            if(present.getType() == Token.NUMBER){
                numbersStack.push(present);
            }
            if(present.getType() == Token.VARIABLE){
                Token evaluation = new NumberToken(currentEvaluation);
                numbersStack.push(evaluation);
            }
            if(present instanceof FunctionToken){
                double value = (Double)numbersStack.pop().getValue();

                double solved = 0;

                switch(present.getType()){
                    case Token.SIN:
                        solved = Math.sin(value);
                        break;
                    case Token.COS:
                        solved = Math.cos(value);
                        break;
                    case Token.TAN:
                        solved = Math.tan(value);
                        break;
                    case Token.COT:
                        solved = (1.0/Math.tan(value));
                        break;
                    case Token.SEC:
                        solved = (1.0/Math.cos(value));
                        break;
                    case Token.CSC:
                        solved = (1.0/Math.sin(value));
                        break;
                    case Token.SQRT:
                        if(value >= 0){
                            solved = Math.sqrt(value);
                        }else{
                            throw new ArithmeticException();
                        }
                        break;
                }
                Token result = new NumberToken(solved);
                numbersStack.push(result);
            }
            if(present instanceof OperatorToken){
                double operand1 = (Double)numbersStack.pop().getValue();
                double operand2 = (Double)numbersStack.pop().getValue();

                double solved = 0;

                switch(present.getType()){
                    case Token.PLUS:
                        solved = operand2 + operand1;
                        break;
                    case Token.MINUS:
                        solved = operand2 - operand1;
                        break;
                    case Token.PRODUCT:
                        solved = operand2 * operand1;
                        break;
                    case Token.DIVISION:
                        if(operand1 == 0){
                            throw new ArithmeticException();
                        }else{
                            solved = operand2 / operand1;
                        }
                        break;
                    case Token.EXP:
                        if(operand2 < 0 && operand1 < 1 && operand1 > 0){
                            throw new ArithmeticException();
                        }else{
                            solved = Math.pow(operand2, operand1);
                        }
                        break;
                }
                Token result = new NumberToken(solved);
                numbersStack.push(result);
            }
        }
        double result = (Double)numbersStack.pop().getValue();

        return result;
    }
}
