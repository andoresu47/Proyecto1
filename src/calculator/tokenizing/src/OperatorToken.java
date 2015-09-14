package calculator.tokenizing.src;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean operadores.
 * @see calculator.tokenizing.src.Token
 */
public class OperatorToken extends Token {

    public static final char LEFT = 'l';
    public static final char RIGHT = 'r';
    public static final char NA_A = 'n';

    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int NA_P = 0;


    private char value;
    private char associativity;
    private int precedence;

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
                associativity = LEFT;
                precedence = TWO;
                break;
            case '-':
                type = MINUS;
                associativity = LEFT;
                precedence = TWO;
                break;
            case '*':
                type = PRODUCT;
                associativity = LEFT;
                precedence = THREE;
                break;
            case '/':
                type = DIVISION;
                associativity = LEFT;
                precedence = THREE;
                break;
            case '^':
                type = EXP;
                associativity = RIGHT;
                precedence = FOUR;
                break;
            case '(':
                type = LEFT_PARENTHESIS;
                associativity = NA_A;
                precedence = NA_P;
                break;
            case ')':
                type = RIGHT_PARENTHESIS;
                associativity = NA_A;
                precedence = NA_P;
                break;
            default:
                type = UNKNOWN_TOKEN;
                associativity = NA_A;
                precedence = NA_P;
        }
    }

    /**
     * Método que regresa el valor del token.
     *
     * @return Character - valor del token.
     */
    @Override
    public Character getValue() {
        return value;
    }

    /**
     * Método que regresa la asociatividad del operador.
     * @return char - l si se asocia a la izquierda,
     *                r si se asocia a la derecha, y
     *                n en caso de que esto no aplique al operador.
     */
    public char getAssociativity() {
        return associativity;
    }

    /**
     * Método que regresa la precedencia del operador.
     * @return int - valor de la precedencia.
     */
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Método que determina si dos tokens son iguales.
     *
     * @return boolean - true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperatorToken that = (OperatorToken) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
