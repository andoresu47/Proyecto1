package calculator.parsing.src;

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
    public boolean isValid(){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return boolean - true si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<número>" en la gramática*.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseNumber(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<variable>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseVariable(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""(" <expresión> ")"" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseParenthesisExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""-" <expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseMinusExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<función> "(" <expresión> ")"" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseFunctionExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * Método que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresión> <operador> <expresión>" en la gramática.
     * @param tokensList - lista de tokens en notación infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionOperatorExpression(LinkedList<Token> tokensList){
        return false;
    }
}
