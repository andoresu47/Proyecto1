package calculator.tokenizing.src;

/**
 * Created by Andrés on 05/09/2015.
 * Clase encargada de manejar Tokens que sean números.
 * @see calculator.tokenizing.src.Token
 */
public class NumberToken extends Token {

    private double value;

    /**
     * Constructor que recibe un real y representa
     * el tipo del Token.
     * @param value - numero real con el que inicializar
     *              el Token.
     */
    public NumberToken(double value) {
        this.value = value;
        type = NUMBER;
    }

    /**
     * Método que regresa el valor del token.
     * @return Double - valor del token.
     */
    public Double getValue() {
        return value;
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

        NumberToken that = (NumberToken) o;

        return Double.compare(that.value, value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
