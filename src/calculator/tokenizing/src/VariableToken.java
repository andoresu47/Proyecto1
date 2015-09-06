package calculator.tokenizing.src;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean variables.
 */
public class VariableToken extends Token {

    private char value;

    /**
     * Constructor que recibe un caracter y representa el
     * tipo del Token.
     * @param variable - caracter que se espera que represente
     *                 a la variable definida.
     */
    public VariableToken(char variable) {
        value = variable;

        switch (variable){
            case 'x':
                type = VARIABLE;
                break;
            default:
                type = UNKNOWN_TOKEN;
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
}
