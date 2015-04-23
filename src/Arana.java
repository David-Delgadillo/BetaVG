/**
 * Arana
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Arana</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Image;

public class Arana extends BaseEnemigo {
    // *************************** CONSTRUCTOR *********************************
    /**
     * Mano
     * 
     * Metodo constructor usado para crear el objeto Mano
     * extendiendo funciones de la clase Base.
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * 
     */
    public Arana(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, iVida, iTiempo, iVelocidad, imaImagen);
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************
    public void movimiento() {

    }
    // *************************************************************************
}