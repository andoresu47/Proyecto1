package calculator.tokenizing.src;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase encargada de manejar Tokens que sean operadores.
 * @see calculator.tokenizing.src.Token
 */
public class OperatorToken extends Token {

    private char value;

    /**
     * Constructor que recibe un caracter y representa
     * el tipo del Token.
     * @param operator - caracter que se espera sea
     *                 uno de los operadores definidos.
     */
    public OperatorToken(char operator) {
        value = operator;

        switch (operator) {
            case '+':
                type = PLUS;
                break;
            case '-':
                type = MINUS;
                break;
            case '*':
                type = PRODUCT;
                break;
            case '/':
                type = DIVISION;
                break;
            case '^':
                type = EXP;
                break;
            case '(':
                type = LEFT_PARENTHESIS;
                break;
            case ')':
                type = RIGHT_PARENTHESIS;
                break;
            default:
                type = UNKNOWN_TOKEN;
        }
    }

    /**
     * M�todo que regresa el valor del token.
     *
     * @return Character - valor del token.
     */
    @Override
    public Character getValue() {
        return value;
    }
}