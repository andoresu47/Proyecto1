package calculator.graphing.src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que maneja la organización y el comportamiento de
 * un área de opciones.
 */
public class FieldSet extends JPanel {
    private JLabel inputLabel,
                   xMinLabel,
                   xMaxLabel,
                   yMinLabel,
                   yMaxLabel,
                   widthLabel,
                   heightLabel;

    private JTextField inputField,
                       xMinField,
                       xMaxField,
                       yMinField,
                       yMaxField,
                       widthField,
                       heightField;

    private JButton okButton;

    /**
     * Constructor del campo de opciones. Se inicializan cada una de
     * las componentes, y se organizan en el Panel.
     */
    public FieldSet(){
        inputLabel = new JLabel("f(x): ");
        xMinLabel = new JLabel("xMin: ");
        xMaxLabel = new JLabel("xMax: ");
        yMinLabel = new JLabel("yMin: ");
        yMaxLabel = new JLabel("yMax: ");
        widthLabel = new JLabel("Ancho: ");
        heightLabel = new JLabel("Alto: ");

        inputField = new JTextField("<Inserte una expresion>", 30);
        xMinField = new JTextField(5);
        xMaxField = new JTextField(5);
        yMinField = new JTextField(5);
        yMaxField = new JTextField(5);
        widthField = new JTextField(5);
        heightField = new JTextField(5);

        okButton = new JButton("Graficar");

        Border innerBorder = BorderFactory.createTitledBorder("Parametros"),
                outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    /**
     * Método encargado de organizar los distintos elementos en el Panel.
     * Este método debe su existencia a la extensión de su contenido.
     */
    public void layoutComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        //1ra fila - 1ra columna
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(inputLabel, gc);

        //1ra fila - 2da columna
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 0.5;
        gc.weighty = 0.1;
        gc.gridwidth = 11; //Abarca 11 columnas

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(inputField, gc);

        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        //2da fila - 1ra columna
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMinLabel, gc);

        //2da fila - 2da columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMinField, gc);

        //2da fila - 3ra columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(xMaxLabel, gc);

        //2da fila - 4ta columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMaxField, gc);

        //2da fila - 5ta columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(yMinLabel, gc);

        //2da fila - 6ta columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(yMinField, gc);

        //2da fila - 7ma columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(yMaxLabel, gc);

        //2da fila - 8va columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(yMaxField, gc);

        //2da fila - 9na columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(widthLabel, gc);

        //2da fila - 10ma columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(widthField, gc);

        //2da fila - 11va columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(heightLabel, gc);

        //2da fila - 12va columna
        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(heightField, gc);

        //3ra fila - 1ra columna
        gc.gridx = 10;
        gc.gridwidth = 2;
        gc.gridy = 2;
        gc.weightx = 0;
        gc.weighty = 0.5;

        gc.anchor = GridBagConstraints.PAGE_END;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(20, 0, 0, 0);
        add(okButton, gc);
    }
}
