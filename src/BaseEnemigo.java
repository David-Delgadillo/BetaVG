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

public class BaseEnemigo extends Base {
    
    // *************************** ATRIBUTOS ***********************************
    int iVida;
    int iTiempo;
    int iVelocidad;
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
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public BaseEnemigo(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iVelocidad = iVelocidad;
        this.iTiempo = iTiempo;
        this.iVida = iVida;
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
    // *************************************************************************
        
    
    
    
    
    // ************************** METODOS **************************************
    /*
    int iTiempo = 0;
    Point poiDireccion;
    public void movimientoAleatorio(int iVel, int iFrecuencia, int iH, int iW) {
        iTiempo += 20;
        
        if (iTiempo == iFrecuencia) {
            iTiempo = 0;
            
            double dTheta = 2.0d * Math.PI * Math.random();
            poiDireccion.x = (int) (Math.cos(dTheta) * iVel);
            poiDireccion.y = (int) (Math.sin(dTheta) * iVel);
        }
        this.iX += poiDireccion.x;
        
        if (this.iX < 0 || this.iX > iW) {
           poiDireccion.x = -poiDireccion.x;
        }
        
        this.iY += poiDireccion.y;
        
        if (this.iY < 0 || this.iY > iH) {
           poiDireccion.y = -poiDireccion.y;
        }
    }
    */
    // *************************************************************************
}