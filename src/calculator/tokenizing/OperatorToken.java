package calculator.tokenizing;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean operadores.
 */
public class OperatorToken extends Token {

    /**
     * Constructor que recibe un caracter y representa
     * el tipo del Token.
     * @param operator - caracter que se espera sea
     *                 uno de los operadores definidos.
     */
    public OperatorToken(char operator) {
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
     * Método que regresa el tipo del Token.
     *
     * @return int - entero correspondiente al
     * tipo del Token.
     */
    @Override
    public int getType() {
        return type;
    }
}
