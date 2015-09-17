package calculator.graphing.src;

/**
 * Created by Andrés on 16/09/2015.
 * Interfaz que funge como intermediario entre
 * las clases "FieldSet" y "GraphArea" para que
 * al cambiar el tamaño de la ventana, se muestre en
 * el campo de opciones cuál es el alto y ancho de la
 * nueva ventana modificada.
 * Se utiliza la interfaz para mantener a todas las clases
 * lo mas independientes posible, unas de otras.
 */
public interface WeightHeightListener {
    /**
     * Método que determina las acciones a realizar tras recibir
     * nuevos valores del ancho y alto de un área de trabajo de la
     * aplicación.
     * @param newWidth - nuevo valor (en pixeles) del ancho.
     * @param newHeight - nuevo valor (en pixeles) del alto.
     */
    public void windowSizeChangeOccurred(int newWidth, int newHeight);
}
