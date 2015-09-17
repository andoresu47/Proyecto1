package calculator.graphing.src;

import java.util.EventListener;

/**
 * Created by Andrés on 16/09/2015.
 * Interfaz que funge como intermediario entre
 * las clases "FieldSet" y "GraphArea" para que
 * al presionar el boton de "Graficar" en el área de
 * opciones esto tenga efecto sobre el área de graficado
 * de la aplicación.
 * Se utiliza la interfaz para mantener a todas las clases
 * lo mas independientes posible, unas de otras.
 */
public interface InputListener extends EventListener{
    /**
     * Método que se encarga de realizar acciones debidas con la
     * expresión introducida por el usuario, junto con el rango en X,
     * para llevar a cabo la evaluación y posterior graficación de la
     * función.
     * @param expression - cadena introducida por el usuario que se espera
     *                   sea una función matemática válida, en notación infija.
     * @param xMin - cadena que representa el valor mínimo del rango en X.
     * @param xMax - cadena que representa el valor máximo del rango en X.
     */
    public void inputEventOccurred(String expression, String xMin, String xMax);
}
