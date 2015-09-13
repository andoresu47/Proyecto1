package calculator.parsing.src;

import calculator.tokenizing.src.FunctionToken;
import calculator.tokenizing.src.Token;

import java.util.LinkedList;

/**
 * Created by Andrés on 12/09/2015.
 * Clase encargada de verificar la sintaxis (en este caso infija)
 * de las fichas en la lista previamente formada.
 */
public class Parser {

    private LinkedList<Token> tokensList;

    /**
     * Constructor que recibe una lista de tokens en notación
     * infija, y la asigna a su lista propia.
     * @param tokensList - lista de tokens en notación infija.
     */
    public Parser(LinkedList<Token> tokensList){
        this.tokensList = tokensList;
    }

    /**
     * Método que se encarga de determinar si una lista de tokens
     * contiene la sintaxis correcta de gramática infija.
     * @return boolean - true si la sitaxis es correcta, false en otro caso.
     */
    public boolean isValid() {
        return tokensList != null && parseExpression(tokensList);
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return boolean - true si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpression(LinkedList<Token> tokensList){
        return parseNumber(tokensList)
                || parseVariable(tokensList)
                || parseParenthesisExpression(tokensList)
                || parseMinusExpression(tokensList)
                || parseFunctionExpression(tokensList)
                || parseExpressionOperatorExpression(tokensList);
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<número>" en la gramática*.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseNumber(LinkedList<Token> tokensList){
        return tokensList.size() == 1 && tokensList.getFirst().getType() == Token.NUMBER;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<variable>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseVariable(LinkedList<Token> tokensList){
        return tokensList.size() == 1 && tokensList.getFirst().getType() == Token.VARIABLE;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""(" <expresión> ")"" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseParenthesisExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isLeftParenthesis = newTokensList.removeFirst().getType() == Token.LEFT_PARENTHESIS;
        boolean isRightParenthesis = newTokensList.removeLast().getType() == Token.RIGHT_PARENTHESIS;

        return isLeftParenthesis && parseExpression(newTokensList) && isRightParenthesis;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""-" <expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseMinusExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 2){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isMinusSign = newTokensList.removeFirst().getType() == Token.MINUS;

        return isMinusSign && parseExpression(newTokensList);
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<función> "(" <expresión> ")"" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseFunctionExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 6){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isFunction = newTokensList.removeFirst() instanceof FunctionToken;
        boolean isLeftParenthesis = newTokensList.removeFirst().getType() == Token.LEFT_PARENTHESIS;
        boolean isRightParenthesis = newTokensList.removeLast().getType() == Token.RIGHT_PARENTHESIS;

        return isFunction && isLeftParenthesis && parseExpression(newTokensList) && isRightParenthesis;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresión> <operador> <expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionOperatorExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        //Se inicializa en i = 1, y va hasta el tamaño menos uno, pues un operador
        //no puede estar ni al principio ni al final de una expresión.
        for(int i = 1; i < tokensList.size() - 1; i++){

            int type = tokensList.get(i).getType();

            boolean isOperator = type == Token.PLUS ||
                                 type == Token.MINUS ||
                                 type == Token.PRODUCT ||
                                 type == Token.DIVISION ||
                                 type == Token.EXP;

            if(isOperator){
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                return parseExpression(leftExpression) && parseExpression(rightExpression);
            }
        }

        return false;
    }
}
