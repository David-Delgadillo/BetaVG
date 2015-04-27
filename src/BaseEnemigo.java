/**
 * BaseEnemigo
 *
 * Modela la definición de todos los objetos de tipo
 * <code>BaseEnemigo</code>
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

public class BaseEnemigo extends Base {
    
    // *************************** ATRIBUTOS ***********************************
    private int iVida;  // Vida del enemigo
    private int iTiempo;    // Tiempo para el cambio de movimiento
    private int iVelocidad; // Velocidad a la que se mueve
    private int iFrecuencia; // Cada cuando va a cambiar de movimiento
    private int iEntrada; // Por que parte entrara el enemigo
    private boolean bSale;  // Debe salir de la pantalla el enemigo o no
    private int iDireccionX;     // Direccion en X del movimiento
    private int iDireccionY;    // Direccion en Y del movimiento
    
    /*
     * Entrada
     * 1 - Arriba
     * 2 - Abajo
     * 3 - Izquierda
     * 4 - Derecha
     */
    // *************************************************************************
    
    
    
    
    
    // *************************** CONSTRUCTOR *********************************   
    /**
     * BaseEnemigo
     * 
     * Metodo constructor usado para crear el objeto base creando el icono a
     * partir de una imagen
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
     * 
     */
    public BaseEnemigo(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, int iFrecuencia, int iEntrada, 
            boolean bSale, int iDireccionX, int iDireccionY, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iVelocidad = iVelocidad;
        this.iTiempo = iTiempo;
        this.iVida = iVida;
        this.iFrecuencia = iFrecuencia;
        this.iEntrada = iEntrada;
        this.bSale = bSale;
        this.iDireccionX = iDireccionX;
        this.iDireccionY = iDireccionY;
    }
    // *************************************************************************
    
    
    
    
    
    // ********************** METODOS DE ACCESO ********************************    
    /**
     * getVida
     * 
     * Metodo de acceso que regresa la vida del objeto 
     * 
     * @return iVida es la <code>Vida</code> del objeto.
     * 
     */
    public int getVida() {
        return this.iVida;
    }
    
    
    /**
     * getTiempo
     * 
     * Metodo de acceso que regresa el tiempo del objeto 
     * 
     * @return iTiempo es el <code>tiempo</code> del objeto.
     * 
     */
    public int getTiempo() {
        return this.iTiempo;
    }
    
    
    /**
     * getVelocidad
     * 
     * Metodo de acceso que regresa la velocidad del objeto 
     * 
     * @return iVelocidad es la <code>Velocidad</code> del objeto.
     * 
     */
    public int getVelocidad() {
        return this.iVelocidad;
    }
    
    
    /**
     * getFrecuencia
     * 
     * Metodo de acceso que regresa la Frecuencia del objeto 
     * 
     * @return iFrecuencia es la <code>Frecuencia</code> del objeto.
     * 
     */
    public int getFrecuencia() {
        return this.iFrecuencia;
    }
    
    
    /**
     * getEntrada
     * 
     * Metodo de acceso que regresa la Entrada del objeto 
     * 
     * @return iEntrada es la <code>Entrada</code> del objeto.
     * 
     */
    public int getEntrada() {
        return this.iEntrada;
    }
    
    
    /**
     * getSale
     * 
     * Metodo de acceso que regresa la Salida del objeto 
     * 
     * @return bSale es la <code>Sale</code> del objeto.
     * 
     */
    public boolean getSale() {
        return this.bSale;
    }
    
    
    /**
     * getDireccionX
     * 
     * Metodo de acceso que regresa la Direccion X del objeto 
     * 
     * @return iDireccionX es la <code>Direccion X</code> del objeto.
     * 
     */
    public int getDireccionX() {
        return this.iDireccionX;
    }
    
    
    /**
     * getDireccionY
     * 
     * Metodo de acceso que regresa la Direccion Y del objeto 
     * 
     * @return iDireccionY es la <code>Direccion Y</code> del objeto.
     * 
     */
    public int getDireccionY() {
        return this.iDireccionY;
    }
    // *************************************************************************
    
    
    
    
    
    // ******************* METODOS MODIFICADORES *******************************
    /**
     * setVida
     * 
     * Metodo modificador usado para cambiar la vida del objeto
     * 
     * @param iVida es la <code>Vida</code> del objeto.
     * 
     */
    public void setVida(int iVida) {
        this.iVida = iVida;
    }
    
    
    /**
     * setTiempo
     * 
     * Metodo modificador usado para cambiar el tiempo del objeto
     * 
     * @param iTiempo es la <code>Vida</code> del objeto.
     * 
     */
    public void setTiempo(int iTiempo) {
        this.iTiempo = iTiempo;
    }
    
    
    /**
     * setVelocidad
     * 
     * Metodo modificador usado para cambiar la velocidad del objeto
     * 
     * @param iVelocidad es la <code>Velocidad</code> del objeto.
     * 
     */
    public void setVelocidad(int iVelocidad) {
        this.iVelocidad = iVelocidad;
    }
    
    
    /**
     * setFrecuencia
     * 
     * Metodo modificador usado para cambiar la Frecuencia del objeto
     * 
     * @param iFrecuencia es la <code>Frecuencia</code> del objeto.
     * 
     */
    public void setFrecuencia(int iFrecuencia) {
        this.iFrecuencia = iFrecuencia;
    }
    
    
    /**
     * setEntrada
     * 
     * Metodo modificador usado para cambiar la Entrada del objeto
     * 
     * @param iEntrada es la <code>Entrada</code> del objeto.
     * 
     */
    public void setEntrada(int iEntrada) {
        this.iEntrada = iEntrada;
    }
    
    
    /**
     * setSale
     * 
     * Metodo modificador usado para cambiar la Salida del objeto
     * 
     * @param bSalida es la <code>Salida</code> del objeto.
     * 
     */
    public void setSale(boolean bSale) {
        this.bSale = bSale;
    }
    
    
    /**
     * setDireccion
     * 
     * Metodo modificador usado para cambiar la Direccion del objeto
     * 
     * @param iX es la <code>Direccion X</code> del objeto.
     * @param iY es la <code>Direccion Y</code> del objeto.
     * 
     */
    public void setDireccion(int iX, int iY) {
        this.iDireccionX = iX;
        this.iDireccionY = iY;
    }
    
    
    /**
     * setDireccionX
     * 
     * Metodo modificador usado para cambiar la Direccion X del objeto
     * 
     * @param iX es la <code>Direccion X</code> del objeto.
     * 
     */
    public void setDireccionX(int iX) {
        this.iDireccionX = iX;
    }
    
    
    /**
     * setDireccionY
     * 
     * Metodo modificador usado para cambiar la Direccion Y del objeto
     * 
     * @param iY es la <code>Direccion Y</code> del objeto.
     * 
     */
    public void setDireccionY(int iY) {
        this.iDireccionY = iY;
    }
    // *************************************************************************
        
    
    
    
    
    // ************************** METODOS **************************************
    
    // *************************************************************************
}