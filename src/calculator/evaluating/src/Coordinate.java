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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (Double.compare(that.xCoordinate, xCoordinate) != 0) return false;
        return Double.compare(that.yCoordinate, yCoordinate) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(xCoordinate);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yCoordinate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
