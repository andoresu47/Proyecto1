package calculator.graphing.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que maneja la organización y el
 * comportamiento de una barra de herramientas.
 */
public class Toolbar extends JPanel implements ActionListener{
    private JButton button1;
    private JButton button2;

    private ClearScreenListener clearScreenListener;
    private SaveImageListener saveImageListener;

    /**
     * Constructor de la barra de herramientas. Se inicializan
     * cada uno de sus componentes y se organizan en el Panel.
     */
    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());

        button1 = new JButton("Guardar");
        button2 = new JButton("Limpiar");

        button1.addActionListener(this);
        button2.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(button1);
        add(button2);
    }

    /**
     * Método invocado cuando sucede una acción.
     * @param e - hacer click sobre alguno de los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton caller = (JButton)e.getSource();

        if(caller == button1 && this.saveImageListener != null){
            saveImageListener.saveImageEventOccurred();
        }
        if(caller == button2 && this.clearScreenListener != null){
            clearScreenListener.clearScreenEventOccurred();
        }
    }

    /**
     * Método que asigna un escucha producto de limpier la pantalla, al campo correspondiente.
     * @param clearScreenListener - escucha a asignar al campo propio de la clase.
     */
    public void setClearScreenListener(ClearScreenListener clearScreenListener) {
        this.clearScreenListener = clearScreenListener;
    }

    /**
     * Método que asigna un escucha producto de guardar la pantalla, al campo correspondiente.
     * @param saveImageListener - escucha a asignar al campo propio de la clase.
     */
    public void setSaveImageListener(SaveImageListener saveImageListener) {
        this.saveImageListener = saveImageListener;
    }
}
