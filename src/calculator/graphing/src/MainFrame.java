package calculator.graphing.src;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que controla la interacción de todos
 * los elementos presentes en la aplicación.
 */
public class MainFrame extends JFrame {
    private PixelCanvas graphArea;
    private FieldSet optionsArea;
    private Toolbar toolbar;

    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new PixelCanvas();
        optionsArea = new FieldSet();
        toolbar = new Toolbar();

        add(toolbar, BorderLayout.NORTH);
        add(graphArea, BorderLayout.CENTER);
        add(optionsArea, BorderLayout.SOUTH);

        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
