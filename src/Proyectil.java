/**
 * Proyectil
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

public class Proyectil extends Base {
    int iMovimiento;
    
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
     * @param iMovimiento es el <code>Movimiento</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Proyectil(int iX, int iY , int iAncho, int iAlto, int iMovimiento, 
            Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iMovimiento = iMovimiento;
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************    
    /**
     * movimiento
     * 
     * Metodo que modifica la posicion del objeto.
     * 
     */
    public void movimiento(){
        int iAuxX = this.getX();
        int iAuxY = this.getY();
        switch (this.iMovimiento) {
            case 1: // Derecha
                iAuxX += 10;
                break;
            case 2: // Derecha Abajo
                iAuxX += 10;
                iAuxY += 10;
                break;
            case 3: // Abajo
                iAuxY += 10;
                break;
            case 4: // Izquierda Abajo
                iAuxX -= 10;
                iAuxY += 10;
                break;
            case 5: // Izquierda
                iAuxX -= 10;
                break;
            case 6: // Izquierda Arriba
                iAuxX -= 10;
                iAuxY -= 10;
                break;
            case 7: // Arriba
                iAuxY -= 10;
                break;
            case 8: // Derecha Arriba
                iAuxX += 10;
                iAuxY -= 10;
                break;
        }
        
        this.setXY(iAuxX, iAuxY); // Actualiza la posicion
    }
    // *************************************************************************
}