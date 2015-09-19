package calculator.graphing.src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                       yMaxField;

    private JButton okButton;

    private InputListener inputListener;

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
        widthLabel = new JLabel("");
        heightLabel = new JLabel("");

        inputField = new JTextField("<Escriba una expresion>", 30);
        xMinField = new JTextField("-10", 5);
        xMaxField = new JTextField("10", 5);
        yMinField = new JTextField("-10", 5);
        yMaxField = new JTextField("10", 5);

        okButton = new JButton("Graficar");

        okButton.addActionListener(new ActionListener() {
            /**
             * Método que determina las acciones a llevar a cabo al presionar el
             * botón de "Graficar" en el campo de opciones. Como su nombre lo dice, se
             * procederá a trazar la gráfica de la expresión escrita.
             * @param e - Acción que accionará el método.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputListener != null) {
                    inputListener.inputEventOccurred(inputField.getText(),
                            xMinField.getText(),
                            xMaxField.getText(),
                            yMinField.getText(),
                            yMaxField.getText());
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Parametros"),
                outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
        setMouseClicks();
    }

    /**
     * Método encargado de definir las acciones del programa al hacer click sobre las
     * distintas opciones del campo de entradas.Como todop esto fue mucho código, se
     * relegó a su propio método.
     */
    private void setMouseClicks(){
        inputField.addMouseListener(new MouseAdapter() {
            /**
             * Método que determina la acción a llevar acabo al hacer click
             * sobre el campo de inserción de texto de la expresión matemática.
             * Al dar click por primera vez sobre el campo, se eliminará el
             * texto que había en él.
             * @param e - evento que accionará el método.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String fieldText = inputField.getText();
                if (fieldText.equals("<Escriba una expresion>")
                        || fieldText.equals("Error de sintaxis.")
                        || fieldText.equals("Expresion invalida.")) {
                    inputField.setForeground(Color.BLACK);
                    inputField.setText("");
                }
            }
        });

        xMinField.addMouseListener(new MouseAdapter() {
            /**
             * Método que determina la acción a llevar acabo al hacer click
             * sobre el campo de inserción de texto del rango izquierdo de x.
             * Solo si ha habido un error de input previo, se notará alguna acción.
             * @param e - evento que accionará el método.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String fieldText = xMinField.getText();
                if (fieldText.equals("Valor invalido.")) {
                    xMinField.setForeground(Color.BLACK);
                    xMinField.setText("");
                }
            }
        });

        xMaxField.addMouseListener(new MouseAdapter() {
            /**
             * Método que determina la acción a llevar acabo al hacer click
             * sobre el campo de inserción de texto del rango derecho de x.
             * Solo si ha habido un error de input previo, se notará alguna acción.
             * @param e - evento que accionará el método.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String fieldText = xMaxField.getText();
                if (fieldText.equals("Valor invalido.")) {
                    xMaxField.setForeground(Color.BLACK);
                    xMaxField.setText("");
                }
            }
        });

        yMinField.addMouseListener(new MouseAdapter() {
            /**
             * Método que determina la acción a llevar acabo al hacer click
             * sobre el campo de inserción de texto del rango izquierdo de y.
             * Solo si ha habido un error de input previo, se notará alguna acción.
             * @param e - evento que accionará el método.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String fieldText = yMinField.getText();
                if (fieldText.equals("Valor invalido.")) {
                    yMinField.setForeground(Color.BLACK);
                    yMinField.setText("");
                }
            }
        });

        yMaxField.addMouseListener(new MouseAdapter() {
            /**
             * Método que determina la acción a llevar acabo al hacer click
             * sobre el campo de inserción de texto del rango derecho de y.
             * Solo si ha habido un error de input previo, se notará alguna acción.
             * @param e - evento que accionará el método.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                String fieldText = yMaxField.getText();
                if (fieldText.equals("Valor invalido.")) {
                    yMaxField.setForeground(Color.BLACK);
                    yMaxField.setText("");
                }
            }
        });
    }

    /**
     * Método encargado de organizar los distintos elementos en el Panel.
     * La organización tiene forma de filas y columnas, con elementos que
     * pueden abarcar más de una columna de espacio. Este método debe su
     * existencia a la extensión de su contenido.
     */
    private void layoutComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //1ra fila - 1ra columna
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(inputLabel, gc);

        //1ra fila - 2da columna
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 0.5;
        gc.weighty = 0.1;
        gc.gridwidth = 7; //Abarca 7 columnas

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(inputField, gc);

        //2da fila - 1ra columna
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMinLabel, gc);

        //2da fila - 2da columna
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMinField, gc);

        //2da fila - 3ra columna
        gc.fill = GridBagConstraints.NONE;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(xMaxLabel, gc);

        //2da fila - 4ta columna
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(xMaxField, gc);

        //2da fila - 5ta columna
        gc.fill = GridBagConstraints.NONE;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(yMinLabel, gc);

        //2da fila - 6ta columna
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(yMinField, gc);

        //2da fila - 7ma columna
        gc.fill = GridBagConstraints.NONE;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(yMaxLabel, gc);

        //2da fila - 8va columna
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx++;
        gc.gridy = 1;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(yMaxField, gc);

        //3ra fila - 1ra columna
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 0);
        add(widthLabel, gc);

        //3ra fila - 2da columna
        gc.gridx++;
        gc.gridy = 2;
        gc.weightx = 0.5;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 10, 0, 0);
        add(heightLabel, gc);

        //3ra fila - 6ta columna
        gc.gridx = 6;
        gc.gridwidth = 2;
        gc.gridy = 2;
        gc.weightx = 0;
        gc.weighty = 0.5;

        gc.anchor = GridBagConstraints.PAGE_END;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(20, 0, 0, 0);
        add(okButton, gc);

    }

    /**
     * Método que asigna un escucha (o "manejador de eventos") al campo especificado.
     * @param inputListener - escucha a asignar.
     */
    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    /**
     * Método que recibe un entero como parámetro y lo utiliza para escribir en la ventana
     * principal de la aplicación un mensaje indicando el ancho en pixeles que ocupa actualmente
     * el área de graficación de funciones.
     * @param graphWidth - entero que representa el ancho del área de graficación actual.
     */
    public void setGraphWidth(int graphWidth) {
        widthLabel.setText("Ancho:  " + graphWidth + " px");
    }

    /**
     * Método que recibe un entero como parámetro y lo utiliza para escribir en la ventana
     * principal de la aplicación un mensaje indicando la altura en pixeles que ocupa actualmente
     * el área de graficación de funciones.
     * @param graphHeight - entero que representa el alto del área de graficación actual.
     */
    public void setGraphHeight(int graphHeight) {
        heightLabel.setText("Alto:  " + graphHeight + " px");
    }

    /**
     * Método encargado de mostrar en el recuadro de texto correspondiente
     * a la inserción de una expresión, un mensaje de error en caso de darse.
     * @param message - mensaje de error (pintado en rojo) a desplegar en pantalla.
     */
    public void setExpressionErrorText(String message){
        inputField.setForeground(Color.RED);
        inputField.setText(message);
    }

    /**
     * Método encargado de mostrar en el recuadro de texto correspondiente
     * a la inserción de xMin, un mensaje de error en caso de darse.
     * @param message - mensaje de error (pintado en rojo) a desplegar en pantalla.
     */
    public void setXminErrorText(String message){
        xMinField.setForeground(Color.RED);
        xMinField.setText(message);
    }

    /**
     * Método encargado de mostrar en el recuadro de texto correspondiente
     * a la inserción de xMax, un mensaje de error en caso de darse.
     * @param message - mensaje de error (pintado en rojo) a desplegar en pantalla.
     */
    public void setXmaxErrorText(String message){
        xMaxField.setForeground(Color.RED);
        xMaxField.setText(message);
    }

    /**
     * Método encargado de mostrar en el recuadro de texto correspondiente
     * a la inserción de yMin, un mensaje de error en caso de darse.
     * @param message - mensaje de error (pintado en rojo) a desplegar en pantalla.
     */
    public void setYminErrorText(String message){
        yMinField.setForeground(Color.RED);
        yMinField.setText(message);
    }

    /**
     * Método encargado de mostrar en el recuadro de texto correspondiente
     * a la inserción de yMax, un mensaje de error en caso de darse.
     * @param message - mensaje de error (pintado en rojo) a desplegar en pantalla.
     */
    public void setYmaxErrorText(String message){
        yMaxField.setForeground(Color.RED);
        yMaxField.setText(message);
    }
}
