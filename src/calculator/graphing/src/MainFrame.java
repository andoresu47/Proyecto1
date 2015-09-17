package calculator.graphing.src;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que controla la interacción de todos
 * los elementos presentes en la aplicación.
 */
public class MainFrame extends JFrame {
    private GraphArea graphArea;
    private FieldSet optionsArea;
    private Toolbar toolbar;
    private int widthOfGraphArea = 670,
                heightOfGraphArea = 500;

    /**
     * Constructor del controlador. Se inicializan los distintos elementos
     * y se organizan en el Frame.
     */
    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new GraphArea(new WeightHeightListener() {
            /**
             * Método que determina las acciones a llevar a cabo al generarse
             * un cambio en el tamaño de la ventana de la aplicación. Se avisará
             * al área de opciones el nuevo tamaño (ancho y alto en pixeles) de la
             * zona de graficación.
             * @param newWidth - nuevo valor del ancho del área de graficación.
             * @param newHeight - nuevo valor del alto del área de graficación.
             */
            @Override
            public void windowSizeChangeOccurred(int newWidth, int newHeight) {
                optionsArea.setGraphWidth(newWidth);
                optionsArea.setGraphHeight(newHeight);
                setWidth(newWidth);
                setHeight(newHeight);
            }
        });
        optionsArea = new FieldSet();
        toolbar = new Toolbar();

        optionsArea.setInputListener(new InputListener() {
            /**
             * Método que determina las acciones a llevar a cabo al hacer click sobre el
             * botón de "Graficar" de la zona de opciones. Se validará la expresión como
             * una fórmula válida en notación infija, se generarán los tokens y se evaluará
             * en varios puntos determinados por el ancho de la ventana y por el rango de
             * valores deseado para el eje X.
             * @param expression - cadena que representa la fórmula matemática a tokenizar,
             *                   validar y evaluar.
             * @param xMin - valor mínimo del rango en el eje X.
             * @param xMax - valor máximo del rango en el eje X.
             */
            @Override
            public void inputEventOccurred(String expression, String xMin, String xMax) {

            }
        });

        add(toolbar, BorderLayout.PAGE_START);
        add(graphArea, BorderLayout.CENTER);
        add(optionsArea, BorderLayout.PAGE_END);

        this.getContentPane().setPreferredSize(new Dimension(670, 500));
        this.pack();
        //this.setSize(670, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Método que asigna un entero a la variable de ancho del
     * área de graficación.
     * @param width - entero que representa ( en pixeles) el nuevo valor del
     *              ancho del área de graficación.
     */
    public void setWidth(int width) {
        this.widthOfGraphArea = width;
    }

    /**
     * Método que asigna un entero a la variable de alto del
     * área de graficación.
     * @param height - entero que representa (en pixeles) el nuevo valor de
     *               la altura del área de graficación.
     */
    public void setHeight(int height) {
        this.heightOfGraphArea = height;
    }
}
