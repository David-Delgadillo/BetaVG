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
     * @param iDireccionX es la <code>Direccion X</code> del objeto.
     * @param iDireccionY es la <code>Direccion Y</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * @param bGolpeado es el <code>booleano</code> que indica si fue golpeado
     */
    public Mosquito(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, int iFrecuencia, int iEntrada, 
            boolean bSale, int iDireccionX, int iDireccionY, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, iVida, iTiempo, iVelocidad, iFrecuencia, 
                iEntrada, bSale, iDireccionX, iDireccionY, imaImagen);
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************
    /*
     * movimiento
     * 
     * Metodo que actualiza la posicion del objeto
     * 
     * @param iWidth es el <code>Ancho</code> del objeto.
     * @param iHeight es el <code>Alto</code> del objeto.
     * 
     */
    public void movimiento(int iWidth, int iHeight) {
        // Genera valores aleatorios para movimiento brusco
        int iAuxX = (int) (Math.random() * 12.0d - 6.0d);
        int iAuxY = (int) (Math.random() * 12.0d - 6.0d);
        
        // Revis que no se salga de la pantalla
        if (this.getX() + iAuxX + this.getAncho() >= iWidth || 
                this.getX() + iAuxX <= 0) {
            iAuxX = - iAuxX;
        }
        if (this.getY() + iAuxY + this.getAlto() >= iHeight || 
                this.getY() + iAuxY <= 0) {
            iAuxY = - iAuxY;
        }
        
        // Asigna nueva posicion
        this.setX(this.getX() + iAuxX);
        this.setY(this.getY() + iAuxY);
    }
    
    
    /*
     * movimientoAleatorio
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iWidth es el <code>Ancho</code> del objeto.
     * @param iHeight es el <code>Alto</code> del objeto.
     * 
     */
    public void movimientoAleatorio(int iW, int iH) {
        this.setTiempo(this.getTiempo() + 20); // Actualiza el tiempo
        
        // Para cambiar la direccion del movimiento
        if (this.getTiempo() == this.getFrecuencia()) {
            this.setTiempo(0); // Actualiza el tiempo
            
            // Modifica la direccion del movimiento
            double dTheta = 2.0d * Math.PI * Math.random();
            int iAuxX = (int) (Math.cos(dTheta) * this.getVelocidad());
            int iAuxY = (int) (Math.sin(dTheta) * this.getVelocidad());
            this.setDireccion(iAuxX, iAuxY);
        }
        
        // Que no se salga de la pantalla
        if (this.getX() + this.getDireccionX() < 0 || 
                this.getX() + this.getAncho() + this.getDireccionX() > iW) {
           this.setDireccionX(- this.getDireccionX());
        }
        
        if (this.getY() +  this.getDireccionY() < 0 || 
                this.getY() + this.getAlto() + this.getDireccionY() > iH) {
           this.setDireccionY(- this.getDireccionY());
        }
        
        // Actualiza el movimiento
        this.setX(this.getX() + this.getDireccionX()); 
        this.setY(this.getY() + this.getDireccionY());
    }
    
    // *************************************************************************
}