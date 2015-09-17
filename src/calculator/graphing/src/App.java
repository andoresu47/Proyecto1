package calculator.graphing.src;

import javax.swing.*;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que ejecuta la aplicación.
 */
public class App{
    /**
     * Método main que inicializa nuestra aplicación en un
     * thread de ejecución.
     * @param args - argumentos recibidos de línea de comando.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}