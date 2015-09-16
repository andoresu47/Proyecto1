package calculator.graphing.src;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que controla la interacción de todos
 * los elementos presentes en la aplicación.
 */
public class MainFrame extends JFrame {
    private GraphArea graphArea;
    private FieldSet optionsArea;
    private Toolbar toolbar;

    /**
     * Constructor del controlador. Se inicializan los distintos elementos
     * y se organizan en el Frame.
     */
    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new GraphArea();
        optionsArea = new FieldSet();
        toolbar = new Toolbar();

        add(toolbar, BorderLayout.PAGE_START);
        add(graphArea, BorderLayout.CENTER);
        add(optionsArea, BorderLayout.PAGE_END);

        this.setSize(670, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
