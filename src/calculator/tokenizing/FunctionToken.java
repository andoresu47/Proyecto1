package calculator.tokenizing;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean funciones.
 */
public class FunctionToken extends Token {

    /**
     * Constructor que recibe una cadena y representa
     * el tipo del Token.
     * @param function - cadena que se espera que represente
     *                 una de las funciones definidas.
     */
    public FunctionToken(String function) {
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
}
