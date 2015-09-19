package calculator.graphing.src;

import calculator.evaluating.src.Coordinate;
import calculator.evaluating.src.Evaluate;
import calculator.graphing.customExceptions.*;
import calculator.parsing.src.Parser;
import calculator.tokenizing.src.ExpressionTokenizer;
import calculator.tokenizing.src.Token;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by Andrés on 15/09/2015.
 * Clase que controla la interacción de todos
 * los elementos presentes en la aplicación.
 */
public class MainFrame extends JFrame {
    private GraphArea graphArea;

    private FieldSet optionsArea;

    private Toolbar toolbar;

    private int widthOfGraphArea = 670,
            heightOfGraphArea = 500;

    private LinkedList<LinkedList<Token>> postfixList = new LinkedList<>();


    private double xMin,
            xMax,
            yMin,
            yMax;

    /**
     * Constructor del controlador. Se inicializan los distintos elementos
     * y se organizan en el Frame.
     */
    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new GraphArea();
        optionsArea = new FieldSet();
        toolbar = new Toolbar();

        graphArea.setWeightHeightListener(new WeightHeightListener() {
            /**
             * Método que determina las acciones a llevar a cabo al generarse
             * un cambio en el tamaño de la ventana de la aplicación. Se avisará
             * al área de opciones el nuevo tamaño (ancho y alto en pixeles) de la
             * zona de graficación.
             * @param newWidth - nuevo valor del ancho del área de graficación.
             * @param newHeight - nuevo valor del alto del área de graficación.
             */
            @Override
            public void windowSizeChangeOccurred(int newWidth, int newHeight) {
                optionsArea.setGraphWidth(newWidth);
                optionsArea.setGraphHeight(newHeight);
                setWidth(newWidth);
                setHeight(newHeight);
                if(!postfixList.isEmpty()){
                    reDrawGraph();
                }
            }
        });

        optionsArea.setInputListener(new InputListener() {
            /**
             * Método que determina las acciones a llevar a cabo al hacer click sobre el
             * botón de "Graficar" de la zona de opciones. Se validará la expresión como
             * una fórmula válida en notación infija, se generarán los tokens y se evaluará
             * en varios puntos determinados por el ancho de la ventana y por el rango de
             * valores deseado para el eje X.
             * @param expression - cadena que representa la fórmula matemática a tokenizar,
             *                   validar y evaluar.
             * @param xMin - valor mínimo del rango en el eje X.
             * @param xMax - valor máximo del rango en el eje X.
             */
            @Override
            public void inputEventOccurred(String expression, String xMin, String xMax, String yMin, String yMax) {
                try {
                    logicHandler(expression, xMin, xMax, yMin, yMax);
                } catch (XMinException e) {
                    optionsArea.setXminErrorText("Valor invalido.");
                } catch (XMaxException e) {
                    optionsArea.setXmaxErrorText("Valor invalido.");
                } catch (YMinException e) {
                    optionsArea.setYminErrorText("Valor invalido.");
                } catch (YMaxException e) {
                    optionsArea.setYmaxErrorText("Valor invalido.");
                } catch (SyntaxException e) {
                    optionsArea.setExpressionErrorText("Error de sintaxis.");
                }
            }
        });

        toolbar.setClearScreenListener(new ClearScreenListener() {
            /**
             * Método que se encarga de limpiar la pantalla de la aplicación; es decir,
             * el área de trazado de gráficas.
             */
            @Override
            public void clearScreenEventOccurred() {
                graphArea.clearAll();
                graphArea.setCenter(new Coordinate(0, 0));
                postfixList.clear();
            }
        });

        toolbar.setSaveImageListener(new SaveImageListener() {
            /**
             * Método que se encarga de guardar el área de graficación como archivo de
             * imagen.
             */
            @Override
            public void saveImageEventOccurred() {
                JFileChooser saveFile = new JFileChooser();
                saveFile.setAcceptAllFileFilterUsed(false);
                saveFile.setMultiSelectionEnabled(false);
                saveFile.addChoosableFileFilter(new FileNameExtensionFilter("Imagenes (*.jpg, *.png, *.gif, *.bmp)"
                        , "jpg", "png", "gif", "bmp"));
                saveFile.setDialogTitle("Guardar como");

                int userSelection = saveFile.showSaveDialog(null);

                if(userSelection == JFileChooser.APPROVE_OPTION){
                    File fileToSave = saveFile.getSelectedFile();
                    saveGraph(fileToSave);
                }
            }
        });

        add(toolbar, BorderLayout.PAGE_START);
        add(graphArea, BorderLayout.CENTER);
        add(optionsArea, BorderLayout.PAGE_END);

        this.getContentPane().setPreferredSize(new Dimension(670, 500));
        this.pack();
        //this.setSize(670, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Método que asigna un entero a la variable de ancho del
     * área de graficación.
     * @param width - entero que representa ( en pixeles) el nuevo valor del
     *              ancho del área de graficación.
     */
    public void setWidth(int width) {
        this.widthOfGraphArea = width;
    }

    /**
     * Método que asigna un entero a la variable de alto del
     * área de graficación.
     * @param height - entero que representa (en pixeles) el nuevo valor de
     *               la altura del área de graficación.
     */
    public void setHeight(int height) {
        this.heightOfGraphArea = height;
    }

    /**
     * Método que se encarga de filtrar la información introducida por el usuario en
     * la interfaz gráfica. Esto, de manera que se verifica si los datos fueron introducidos
     * correctamente o no. De ser así, se ejecuta el resto del programa normalmente, pero
     * si se detecta alguna entrada inválida, se detiene la graficación; ie, ni siquiera
     * se calculan los puntos, y se muestra en pantalla el error que sucedió debido al input
     * del usuario.
     * @param newExpression - cadena que representa la función introducida por el usuario en la
     *                      interfaz gráfica.
     * @param newXMin - cadena que representa la cota mínima en X definida por el usuario.
     * @param newXMax - cadena que representa la cota máxima en X definida por el usuario.
     * @param newYMin - cadena que representa la cota mínima en Y definida por el usuario.
     * @param newYMax - cadena que representa la cota máxima en Y definida por el usuario.
     * @throws XMinException si la cadena introducida para xMin no es un real.
     * @throws XMaxException si la cadena introducida para xMax no es un real.
     * @throws YMinException si la cadena introducida para yMin no es un real.
     * @throws YMaxException si la cadena introducida para yMax no es un real.
     * @throws SyntaxException si el parser detecta un error en la sintaxis de la fórmula.
     */
    private void logicHandler(String newExpression, String newXMin, String newXMax, String newYMin, String newYMax)
            throws XMinException,XMaxException, YMinException, YMaxException, SyntaxException {
        try{
            xMin = Double.parseDouble(newXMin);
        }catch(NumberFormatException e){
            throw new XMinException();
        }
        try{
            xMax = Double.parseDouble(newXMax);
        }catch(NumberFormatException e){
            throw new XMaxException();
        }try{
            yMin = Double.parseDouble(newYMin);
        }catch(NumberFormatException e){
            throw new YMinException();
        }
        try{
            yMax = Double.parseDouble(newYMax);
        }catch(NumberFormatException e){
            throw new YMaxException();
        }
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(newExpression);
        LinkedList<Token> infixTokens = tokenizer.getTokensList();
        Parser parser = new Parser(infixTokens);
        if(!parser.isValid()){
            throw new SyntaxException();
        }
        LinkedList<Token> newPostfixTokens = Evaluate.infixToPostfix(infixTokens);
        if(postfixList.isEmpty()){
            this.postfixList.addLast(newPostfixTokens);
        }else{
            LinkedList<Token> lastPostfixTokens = postfixList.getLast();
            if(!newPostfixTokens.equals(lastPostfixTokens)){
                this.postfixList.addLast(newPostfixTokens);
            }
        }
        reDrawGraph();
    }

    /**
     * Método que se encarga de hacerle saber al área de trazado de la gráfica los nuevos puntos
     * que se van a graficar. No obstante, si se detecta que una lista de puntos está vacía,
     * significa que la expresión introducida no es válida, pero sí tiene sintaxis correcta.
     * Tal sería el caso de una raiz cuadrada de número negativo. Esto se le hace saber al usuario
     * en caso de que ocurra.
     */
    private void reDrawGraph(){
        graphArea.clearAll();
        for(LinkedList<Token> postfixTokens : postfixList){
            LinkedList<Coordinate> rawPoints = Evaluate.generatePoints(postfixTokens, xMin, xMax, widthOfGraphArea);
            if(rawPoints.isEmpty()){
                optionsArea.setExpressionErrorText("Expresion invalida.");
                postfixList.removeLast();
            }else{
                LinkedList<Coordinate> rescaledPoints = rescalePoints(rawPoints);
                graphArea.addCoordinates(rescaledPoints);
            }
        }
    }

    /**
     * Método que se encarga de transformar coordenadas del plano cartesiano a coordenadas
     * en términos de pixeles. Coordenadas que ya pueden ser trazadas directamente por el
     * área encargada del graficado.
     * @param rawPoints - coordenadas cartesianas obtenidas de evaluar la expresión introducida
     *                  por el usuario.
     * @return LinkedList<Coordinate> - lista de coordenadas transformadas en términos de pixeles.
     */
    private LinkedList<Coordinate> rescalePoints(LinkedList<Coordinate> rawPoints){
        LinkedList<Coordinate> rescaled = new LinkedList<>();
        double abstractX,
                abstractY,
                newXinPixels,
                newYinPixels;
        double xDifference = xMax - xMin,
                yDifference = yMax - yMin;
        double xTransformationFactor = widthOfGraphArea/xDifference,
                yTransformationFactor = heightOfGraphArea/yDifference;
        double xOrigin = widthOfGraphArea/2,
                yOrigin = heightOfGraphArea/2;
        double midPointX = (xMax + xMin)/2,
                midPointY = (yMax + yMin)/2;

        for(Coordinate present : rawPoints){
            abstractX = present.getxCoordinate();
            abstractY = present.getyCoordinate();
            newXinPixels = ((abstractX - midPointX) * xTransformationFactor) + xOrigin;
            newYinPixels = yOrigin + ((midPointY - abstractY) * yTransformationFactor);
            rescaled.addLast(new Coordinate(newXinPixels, newYinPixels));
        }
        graphArea.setCenter(new Coordinate(midPointX*xTransformationFactor, midPointY*yTransformationFactor));
        return rescaled;
    }

    /**
     * Método encargado de generar y guardar una imagen de la gráfica que este
     * en pantalla.
     * @param file - archivo en el que se guardará la gráfica. Este puede ser cualquier formato de
     *             imagen.
     */
    private void saveGraph(File file){
        Dimension size = graphArea.getSize();
        BufferedImage image = new BufferedImage(
                size.width, size.height
                , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        graphArea.paint(g2);
        try
        {
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "La imagen se ha guardado con exito.", "Confirmacion", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Ocurrio un problema al guardar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}