package calculator.tokenizing.src;

/**
 * Created by Andrés on 05/09/2015.
 * Clase abstracta encargada de definir lo que
 * es un "Token" para esta aplicación.
 */
public abstract class Token {

    public static final int NUMBER = 0;
    public static final int VARIABLE = 1;
    public static final int PLUS = 2;
    public static final int MINUS = 3;
    public static final int PRODUCT = 4;
    public static final int DIVISION = 5;
    public static final int EXP = 6;
    public static final int LEFT_PARENTHESIS = 7;
    public static final int RIGHT_PARENTHESIS = 8;
    public static final int SIN = 9;
    public static final int COS = 10;
    public static final int TAN = 11;
    public static final int COT = 12;
    public static final int SEC = 13;
    public static final int CSC = 14;
    public static final int SQRT = 15;
    public static final int UNKNOWN_TOKEN = -1;

    protected int type;

    /**
     * Método que regresa el tipo del Token.
     *
     * @return int - entero correspondiente al
     * tipo del Token.
     */
    public int getType(){
        return type;
    }

}
