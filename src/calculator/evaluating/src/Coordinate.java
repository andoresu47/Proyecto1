package calculator.evaluating.src;

/**
 * Created by Andrés on 15/09/2015.
 * Clase encargada de manejar coordenadas en el
 * plano cartesiano.
 */
public class Coordinate {

    private double xCoordinate,
                   yCoordinate;

    /**
     * Constructor que recibe dos reales como parámetros.
     * @param xCoordinate - coordenada "x" del punto.
     * @param yCoordinate - coordenada "y" del punto.
     */
    public Coordinate(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Método que devuelve la coordenada "x" de un punto.
     * @return double - valor de la coordenada "x" del punto.
     */
    public double getxCoordinate() {
        return xCoordinate;
    }
    /**
     * Método que devuelve la coordenada "y" de un punto.
     * @return double - valor de la coordenada "y" del punto.
     */
    public double getyCoordinate() {
        return yCoordinate;
    }
}
