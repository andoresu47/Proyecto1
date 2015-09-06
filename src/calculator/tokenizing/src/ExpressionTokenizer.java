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
     * 
     */
    public ExpressionTokenizer() {
    }

    /**
     *
     * @return LinkedList<Token>
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }
}
