/**
 * Boton
 *
 * Modela la definición de todos los objetos de boton
 * <code>Boton</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Image;
import java.awt.Rectangle;

public class Boton extends Base {
    
    // *************************** ATRIBUTOS ***********************************
    private int iPantallaActual; // Guarda la pantalla en la que esta el boton
    private int iPantallaApunta; // Guarda la pantalla a la que apunta el boton
    private int iTipoDeBoton; // Guarda el tipo de boton;
    
    /*
     * iTipoDeBoton
     * 
     * 1 - Apuntador a otra pantalla
     * 2 - Accept
     * 3 - Cancel
     * 4 - Compra
     * 5 - Achievements
     * 6 - Back
     * 
     */
    // *************************************************************************
    
    
    // *************************** CONSTRUCTOR *********************************
    /**
     * Boton
     * 
     * Metodo constructor usado para crear el objeto boton
     * extendiendo funciones de la clase Base.
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * @param iPantallaActual es la <code>Pantalla actual</code> del objeto.
     * @param iPantallaApunta es la <code>Pantalla que apunta</code> del objeto.
     * @param iTipoDeBoton es el <code>Tipo de Boton</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Boton(int iX, int iY , int iAncho, int iAlto, int iPantallaActual,
            int iPantallaApunta, int iTipoDeBoton, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iPantallaActual = iPantallaActual;
        this.iPantallaApunta = iPantallaApunta;
        this.iTipoDeBoton = iTipoDeBoton;
    }
    // *************************************************************************
    
    
    
    
    
    // ********************** METODOS DE ACCESO ********************************
    /**
     * getPantallaActual
     * 
     * Metodo de acceso que regresa la pantalla actual del objeto 
     * 
     * @return iPantallaActual es la <code>Pantalla actual</code> del objeto.
     * 
     */
    public int getPantallaActual() {
        return this.iPantallaActual;
    }
    
    
    /**
     * getPantallaApunta
     * 
     * Metodo de acceso que regresa la pantalla que apunta del objeto 
     * 
     * @return iPantallaApunta es la <code>Pantalla que apunta</code> del objeto
     * 
     */
    public int getPantallaApunta() {
        return this.iPantallaApunta;
    }
    
    
    /**
     * getTipoDeBoton
     * 
     * Metodo de acceso que regresa el tipo del objeto 
     * 
     * @return iTipoDeBoton es el <code>Tipo de boton</code> del objeto
     * 
     */
    public int getTipoDeBoton() {
        return this.iTipoDeBoton;
    }
    // *************************************************************************
    
    
    
    
    
    // ******************* METODOS MODIFICADORES *******************************
    /**
     * setPantallaActual
     * 
     * Metodo modificador usado para cambiar la pantalla actual del objeto
     * 
     * @param iPantallaActual es la <code>Pantalla actual</code> del objeto.
     * 
     */
    public void setPantallaActual(int iPantallaActual) {
        this.iPantallaActual = iPantallaActual;
    }
    
    
    /**
     * setPantallaApunta
     * 
     * Metodo modificador usado para cambiar la pantalla que apunta el objeto
     * 
     * @param iPantallaApunta es la <code>Pantalla que apunta</code> el objeto.
     * 
     */
    public void setPantallaApunta(int iPantallaApunta) {
        this.iPantallaApunta = iPantallaApunta;
    }
    
    
    /**
     * setTipoDeBoton
     * 
     * Metodo modificador usado para cambiar el tipo de boton del objeto
     * 
     * @param iTipoDeBoton es el <code>Tipo de boton</code> del objeto.
     * 
     */
    public void setTipoDeBoton(int iTipoDeBoton) {
        this.iTipoDeBoton = iTipoDeBoton;
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************
    /**
     * mouseEncimaBoton
     * 
     * Metodo para revisar si una coordenada esta encima del boton
     * 
     * @param iX es la <code>X</code> del cursor.
     * @param iY es la <code>Y</code> del cursor.
     * 
     */
    public boolean intersecta(int iX, int iY) {
        // se crea los rectangulos de ambos
        Rectangle rctEste = new Rectangle(this.getX(), this.getY(),
                this.getAncho(), this.getAlto());
        
        Rectangle rctParam = new Rectangle(iX, iY, 1, 1); 

        // revisa si existe colision
        if (rctEste.intersects(rctParam)) {
            return true;
        }
        return false;
    }
    
    // *************************************************************************
}