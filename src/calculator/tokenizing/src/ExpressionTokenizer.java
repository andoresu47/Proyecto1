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
        tokensList = new LinkedList<>();
        char[] charArray = expression.toCharArray();
        int size = charArray.length;
        int i = 0;
        char present;
        StringBuffer sequence;

        while(i < size){
            present = charArray[i];
            if(present == ' ' || present == '\n' || present == '\t'){
                i++;
            }else{
                if(present == '+' || present == '-' || present == '*' || present == '/' || present == '^' ||
                        present == '(' || present == ')'){
                    tokensList.addLast(new OperatorToken(present));
                    i++;
                }else{
                    if(present == 'x'){
                        tokensList.addLast(new VariableToken(present));
                        i++;
                    }else{
                        if(present == 's' || present == 'c' || present == 't'){
                            sequence = new StringBuffer();
                            for(int j = 0; j < 3; j++){
                                sequence.append(present);
                                i++;
                                if(i < size){
                                    present = charArray[i];
                                }else{
                                    break;
                                }
                            }
                            String function = sequence.toString();
                            if(function.equals("sin") || function.equals("cos") || function.equals("tan") ||
                                    function.equals("cot") || function.equals("sec") || function.equals("csc") ||
                                    function.equals("sqr")){
                                tokensList.addLast(new FunctionToken(function));
                            }else{
                                tokensList = null;    //Al no ser válida la entrada, no se tendrán tokens.
                                break;
                            }
                        }else{
                            if(Character.isDigit(present) || present == '.'){
                                sequence = new StringBuffer();

                                while(Character.isDigit(present) || present == '.'){
                                    sequence.append(present);
                                    i++;
                                    if(i < size){
                                        present = charArray[i];
                                    }else{
                                        break;
                                    }
                                }
                                String numberStr = sequence.toString();
                                if(isParsable(numberStr)){
                                    double number = Double.parseDouble(numberStr);
                                    tokensList.addLast(new NumberToken(number));
                                }else{
                                    tokensList = null;
                                    break;
                                }
                            }else{
                                tokensList = null;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Método que devuelve la lista de tokens que se creó
     * a partir de la expresión con la que se construyó
     * el objeto. Esta lista sólo incluirá elementos permitidos
     * por la gramática, descartando caracteres ajenos.
     * @return LinkedList - lista de tokens permitidos
     * con la descomposición de una cadena. Null si la expresion
     * contiene algún valor que no sea uno de los tokens definidos.
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }

    /**
     * Método auxiliar en la determinación de que una cadena
     * pueda ser transformada a un número real.
     * @param input - cadena a verificar.
     * @return boolean - true si sí se puede, false en caso contrario.
     */
    private boolean isParsable(String input){
        boolean parsable = true;
        try{
            Double.parseDouble(input);
        }catch(Exception e){
            parsable = false;
        }

        return parsable;
    }
}
