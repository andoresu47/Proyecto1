package calculator.tokenizing.src;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean funciones.
 * @see calculator.tokenizing.src.Token
 */
public class FunctionToken extends Token {

    private String value;

    /**
     * Constructor que recibe una cadena y representa
     * el tipo del Token.
     * @param function - cadena que se espera que represente
     *                 una de las funciones definidas.
     */
    public FunctionToken(String function) {
        value = function;

        switch (function) {
            case "sin":
                type = SIN;
                break;
            case "cos":
                type = COS;
                break;
            case "tan":
                type = TAN;
                break;
            case "cot":
                type = COT;
                break;
            case "sec":
                type = SEC;
                break;
            case "csc":
                type = CSC;
                break;
            case "sqr":
                type = SQRT;
                break;
            default:
                type = UNKNOWN_TOKEN;
        }
    }

    /**
     * Método que devuelve el valor del token.
     *
     * @return String - valor del token.
     */
    @Override
    public String getValue() {
        return value;
    }
}
