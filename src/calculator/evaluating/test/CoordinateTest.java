package calculator.evaluating.test;

import calculator.evaluating.src.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andrés on 15/09/2015.
 * Clase de prueba para la clase "Coordinate".
 */
public class CoordinateTest {
    /**
     * Método que prueba "getxCoordinate"
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetxCoordinate() throws Exception {

        double expected = 1.0;
        Coordinate toTest = new Coordinate(expected, 2.5);
        double actual = toTest.getxCoordinate();

        assertEquals(expected, actual, 0.001);
    }

    /**
     * Método que prueba "getyCoordinate"
     * @throws Exception si ocurre alguna anomalía.
     */
    @Test
    public void testGetyCoordinate() throws Exception {
        double expected = 2.5;
        Coordinate toTest = new Coordinate(1.0, expected);
        double actual = toTest.getyCoordinate();

        assertEquals(expected, actual, 0.001);
    }
}