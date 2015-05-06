/**
 * Proyectil
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Mosquito</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280iVel6
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Image;

public class Proyectil extends Base {
    private int iMovimiento;    // A donde se mueve
    private int iVel;            // Velocidad
    private double dAngulo1;    // Sin (22.5)
    private double dAngulo2;    // Cos (22.5)
    
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
            int iVel, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iMovimiento = iMovimiento;
        this.iVel = iVel;
        dAngulo1 = 0.3826834323650897717284599840303988667613445624856270414338;
        dAngulo2 = 0.9238795325112867561281831893967882868224166258636424861150;
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
                iAuxX += iVel;
                break;
            case 2: // Derecha Abajo
                iAuxX += (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                iAuxY += (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                break;
            case 3: // Abajo
                iAuxY += iVel;
                break;
            case 4: // Izquierda Abajo
                iAuxX -= (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                iAuxY += (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                break;
            case 5: // Izquierda
                iAuxX -= iVel;
                break;
            case 6: // Izquierda Arriba
                iAuxX -= (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                iAuxY -= (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                break;
            case 7: // Arriba
                iAuxY -= iVel;
                break;
            case 8: // Derecha Arriba
                iAuxX += (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                iAuxY -= (int) (Math.sqrt(iVel * iVel / 2.0d)) + 1;
                break;
            case 9: // Arriba Arriba Derecha
                iAuxX += (int) (iVel * dAngulo1) + 1;
                iAuxY -= (int) (iVel * dAngulo2) + 1;
                break;
            case 10:// Ariba Derecha Derecha
                iAuxX += (int) (iVel * dAngulo2) + 1;
                iAuxY -= (int) (iVel * dAngulo1) + 1;
                break;
            case 11: // Abajo Derecha Derecha
                iAuxX += (int) (iVel * dAngulo2) + 1;
                iAuxY += (int) (iVel * dAngulo1) + 1;
                break;
            case 12: // Abajo Abajo Derecha
                iAuxX += (int) (iVel * dAngulo1) + 1;
                iAuxY += (int) (iVel * dAngulo2) + 1;
                break;
            case 13: // Abajo Abajo Izquierda
                iAuxX -= (int) (iVel * dAngulo1) + 1;
                iAuxY += (int) (iVel * dAngulo2) + 1;
                break;
            case 14: // Abajo Izquierda Izquiera
                iAuxX -= (int) (iVel * dAngulo2) + 1;
                iAuxY += (int) (iVel * dAngulo1) + 1;
                break;
            case 15: // Arriba Izquierda Izquierda
                iAuxX -= (int) (iVel * dAngulo2) + 1;
                iAuxY -= (int) (iVel * dAngulo1) + 1;
                break;
            case 16: // Arriba Arriba Izquierda
                iAuxX -= (int) (iVel * dAngulo1) + 1;
                iAuxY -= (int) (iVel * dAngulo2) + 1;
                break;
        }
        
        this.setXY(iAuxX, iAuxY); // Actualiza la posicion
    }
    // *************************************************************************
}