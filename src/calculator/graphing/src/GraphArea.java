package calculator.graphing.src;

import calculator.evaluating.src.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que maneja el área de trazado de la gráfica.
 */
public class GraphArea extends JPanel {

    private LinkedList<Coordinate> coordinates = null;

    private Coordinate center = new Coordinate(0,0);

    private WeightHeightListener weightHeightListener;

    private int pastWidth = 0,
            pastHeight = 0;

    /**
     * Método que se encarga del trazado de pixeles en el área
     * destinada para el trazado de la gráfica. Este método comienza
     * a ejecutarse tan pronto se crea una instancia de la clase. Así mismo,
     * está constantemente pendiente de cambios.
     * @param g - componente necesario para la sobreescritura de este método, que
     *          se encarga de pintar cualquier elemento que se necesite sobre
     *          el área destinada para ello.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.WHITE);

        Dimension size = getSize();
        int presentWidth = size.width ;
        int presentHeight = size.height;

        //Se dibujan los ejes, siempre tomando en cuenta el ancho y alto
        setGrid(presentWidth, presentHeight, g);
        setAxes(presentWidth, presentHeight, g);

        if(coordinates != null){
            drawGraph(g);
        }

        //Sólo si hubo un cambio de tamaño de ventana, se hace la notificación al MainFrame
        if(presentWidth != pastWidth || presentHeight != pastHeight){
            weightHeightListener.windowSizeChangeOccurred(presentWidth, presentHeight);
        }

        pastWidth = presentWidth;
        pastHeight = presentHeight;
    }

    /**
     * Método que inicializa el conjunto de coordenadas de puntos a graficar.
     * @param coordinates - coordenadas de puntos.
     */
    public void setCoordinates(LinkedList<Coordinate> coordinates) {
        this.coordinates = coordinates;
        this.repaint();
    }

    /**
     * Coordenadas en pixeles del punto medio del área de graficado. Estas se
     * usan para reescalar los ejes y la malla coordenados.
     * @param center - coordenadas en pizeles del nuevo centro.
     */
    public void setCenter(Coordinate center) {
        this.center = center;
    }

    /**
     * Método que asigna un escucha recibido como parámetro al escucha
     * definido en la estructura de la clase.
     * @param weightHeightListener - escucha a asignar al propio de la clase.
     */
    public void setWeightHeightListener(WeightHeightListener weightHeightListener) {
        this.weightHeightListener = weightHeightListener;
    }

    /**
     * Método que se encarga de definir los ejes coordenados, y los
     * reescala según se modifique la ventana de visualización.
     * @param width - ancho actual del área de graficado.
     * @param height - largo actual del área de graficado.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void setAxes(int width, int height, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(1));

        int xMidpoint = Math.round(width / 2) - (int)center.getxCoordinate(),
                yMidpoint = (int)center.getyCoordinate() + Math.round(height / 2);

        g2d.drawLine(0, yMidpoint, width, yMidpoint);
        g2d.drawLine(xMidpoint, 0, xMidpoint, height);

        g.setFont(new Font("Verdana", Font.PLAIN, 15));
        g.drawString("x", width - 20, height - 20);
        g.drawString("y", 20, 20);
    }

    /**
     * Método que se encarga de definir una malla coordenada, y la
     * reescala según se modifique la ventana de visualización, o bien
     * la escala de valores en "x" y "y".
     * @param width - ancho actual del área de graficado.
     * @param height - largo actual del área de graficado.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void setGrid(int width, int height, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.LIGHT_GRAY);

        float[] dashingPattern = {2f, 2f};
        Stroke stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 2.0f);

        g2d.setStroke(stroke);

        int interval = 10;

        int xStep = Math.round(width/interval),
                yStep = Math.round(height/interval),
                xMidpoint = Math.round(width/2) - (int)center.getxCoordinate(),
                yMidpoint = Math.round(height/2) + (int)center.getyCoordinate();

        //Se hace esto de definir el centro como el punto de partida del trazado, puesto que los pixeles son números
        //enteros, y no tienen la precisión que si tienen las coordenadas abstractas, entonces, si
        //no empezara por mis ejes coordenados, se tendría una malla desfasada con respecto a estos por
        //unos cuantos pixeles que, aunque es poco, se ve desagradable.
        int x, y;

        x = xMidpoint;
        while(x < width){
            g2d.drawLine(x, 0, x, height);
            x = x + xStep;
        }
        x = xMidpoint;
        while(x > 0){
            g2d.drawLine(x, 0, x, height);
            x = x - xStep;
        }

        y = yMidpoint;
        while(y < height){
            g2d.drawLine(0, y, width, y);
            y = y + yStep;
        }
        y = yMidpoint;
        while(y > 0){
            g2d.drawLine(0, y, width, y);
            y = y - yStep;
        }
    }

    /**
     * Método que se encarga de pintar el gráfico en su área designada. Para
     * esto, se toma la lista de coordenadas de pixeles y se van dibujando una a
     * una, uniendolas por pares con líneas rectas. Dado que con muchos puntos, el
     * efecto de la recta se pierde y si se pueden apreciar formas curvas.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void drawGraph(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        int x1,
                x2,
                y1,
                y2;

        Coordinate currentPoint;

        for (int i = 1; i < coordinates.size(); i++) {
            currentPoint = coordinates.get(i-1);
            x1 = (int)Math.round(currentPoint.getxCoordinate());
            y1 = (int)Math.round(currentPoint.getyCoordinate());

            currentPoint = coordinates.get(i);
            x2 = (int)Math.round(currentPoint.getxCoordinate());
            y2 = (int)Math.round(currentPoint.getyCoordinate());

            //Condición para que no se dibuje una línea entre puntos separados por una asíntota.
            if(Math.abs(y2 - y1) < 100){
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }
}