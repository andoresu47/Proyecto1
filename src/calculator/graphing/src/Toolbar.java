package calculator.graphing.src;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que maneja la organización y el
 * comportamiento de una barra de herramientas.
 */
public class Toolbar extends JPanel{
    private JButton button1;
    private JButton button2;

    /**
     * Constructor de la barra de herramientas. Se inicializan
     * cada uno de sus componentes y se organizan en el Panel.
     */
    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());

        button1 = new JButton("Guardar");
        button2 = new JButton("Limpiar");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(button1);
        add(button2);
    }
}
