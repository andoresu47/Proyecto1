package calculator.tokenizing.src;

import java.util.LinkedList;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de desmembrar una expresión (String) en
 * sus componentes (llamémosle a estas, "fichas"; o en
 * inglés, "Tokens").
 */
public class ExpressionTokenizer {

    private LinkedList<Token> tokensList;

    /**
     * Constructor que recibe la cadena a desmembrar en
     * tokens.
     * @param expression - expresión que se descompondrá
     *                   en tokens.
     */
    public ExpressionTokenizer(String expression) {

    }

    /**
     * Método que devuelve la lista de tokens que se creó
     * a partir de la expresión con la que se construyó
     * el objeto.
     * @return LinkedList<Token> - lista de tokens con
     * la descomposición de una cadena.
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }
}
