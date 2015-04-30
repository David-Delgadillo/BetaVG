/**
 * Mosquito
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Mosquito</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Image;
import java.awt.Point;

public class Mosquito extends BaseEnemigo {
    // *************************** CONSTRUCTOR *********************************    
    /**
     * Mosquito
     * 
     * Metodo constructor usado para crear el objeto Mosquito
     * extendiendo funciones de la clase Base.
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * @param iVida es la <code>Vida</code> del objeto.
     * @param iTiempo es el <code>Tiempo</code> del objeto.
     * @param iVelocidad es la <code>Velocidad</code> del objeto.
     * @param iFrecuencia es la <code>Frecuencia</code> del objeto.
     * @param iEntrada es la <code>Entrada</code> del objeto.
     * @param bSale es la <code>Salida</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Mosquito(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, int iFrecuencia, int iEntrada, 
            boolean bSale, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, iVida, iTiempo, iVelocidad, iFrecuencia, 
                iEntrada, bSale, imaImagen);
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************    
    // *************************************************************************
}