/**
 * Mano
 *
 * Modela la definicion de todos los objetos de tipo
 * <code>Mano</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Rectangle;

public class Mano extends Base {
    
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
    public Mano(int iX, int iY , int iAncho, int iAlto) {
        super(iX, iY, iAncho, iAlto);
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************
    /**
     * intersectaEnemigo
     * 
     * Metodo que checa si la mano a colicionado con algun objeto enemigo
     * 
     * @param eneEnemigo es el <code>Object</code> con el que se quiere checar
     *         la colicion.
     * @return <code>boolean</code> 
     * 
     */
    public boolean intersectaEnemigo(Object eneEnemigo) {
        // se crea los rectangulos de ambos
        Rectangle rctEste = new Rectangle(this.getX(), this.getY(),
                this.getAncho(), this.getAlto());
        
        // Se castea el objeto a Base antes de crear su rectangulo
        Base basTemp = (Base) eneEnemigo;
        Rectangle rctParam = new Rectangle(basTemp.getX(), 
                basTemp.getY(), basTemp.getAncho(), basTemp.getAlto());

        // revisa si existe colision
        if (rctEste.intersects(rctParam)) {
            System.out.println("Pase2");
            return true;
        } // si no hay colision
        return false;
    }
    // *************************************************************************
}