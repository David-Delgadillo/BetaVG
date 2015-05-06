/**
 * Base
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Base</code>
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Base {
    
    // *************************** ATRIBUTOS ***********************************
    private int iX;     //posicion en x.       
    private int iY;     //posicion en y.
    private int iCentroObjX;   //posicion en el centro de x
    private int iCentroObjY;   //posicion en el centro de y
    private int iAncho; //ancho del objeto
    private int iAlto; //largo del objeto
    private Image imaImagen;	//imagen.
    // *************************************************************************
    
    
    
    
    
    // *************************** CONSTRUCTOR *********************************
    /**
     * Base
     * 
     * Metodo constructor usado para crear el objeto base creando el icono a
     * partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Base(int iX, int iY , int iAncho, int iAlto, Image imaImagen) {
        this.iX = iX;
        this.iY = iY;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        this.imaImagen = imaImagen;
        modificaCentro();
    }
    
    
    /**
     * Base
     * 
     * Metodo constructor usado para crear el objeto base creando el icono a 
     * partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param iAncho es el <code>ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * 
     */
    public Base(int iX, int iY , int iAncho, int iAlto) {
        this.iX = iX;
        this.iY = iY;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        this.imaImagen = null;
        modificaCentro();
    }
    // *************************************************************************
    
    
    
    
    
    // ********************** METODOS DE ACCESO ********************************
    /**
     * getX
     * 
     * Metodo de acceso que regresa la posicion en x del objeto 
     * 
     * @return iX es la <code>posicion en x</code> del objeto.
     * 
     */
    public int getX() {
        return iX;
    }     
    
    
    /**
     * getY
     * 
     * Metodo de acceso que regresa la posicion en y del objeto 
     * 
     * @return posY es la <code>posicion en y</code> del objeto.
     * 
     */
    public int getY() {
        return iY;
    }
    
    
    /**
     * getAncho
     * 
     * Metodo de acceso que regresa el ancho del icono 
     * 
     * @return un <code>entero</code> que es el ancho de la imagen.
     * 
     */
    public int getAncho() {
        return iAncho;
    }
    
    
    /**
     * getAlto
     * 
     * Metodo que  da el alto del icono 
     * 
     * @return un <code>entero</code> que es el alto de la imagen.
     * 
     */
    public int getAlto() {
        return iAlto;
    }
    
    
    /**
     * getCentroX
     * 
     * Metodo que da el Centro X del objeto 
     * 
     * @return un <code>entero</code> que es el centro en x de la imagen.
     * 
     */
    public int getCentroObjX() {
        return this.iCentroObjX;
    }
    
    
    /**
     * getCentroY
     * 
     * Metodo que da el Centro Y del objeto 
     * 
     * @return un <code>entero</code> que es el centro en y de la imagen.
     * 
     */
    public int getCentroObjY() {
        return this.iCentroObjY;
    }
    
    
    /**
     * getImagen
     * 
     * Metodo de acceso que regresa la imagen que representa el icono del objeto
     * 
     * @return la imagen a partide del <code>icono</code> del objeto.
     * 
     */
    public Image getImagen() {
        return imaImagen;
    }
    // *************************************************************************
    
    
    
    
    
    // ******************* METODOS MODIFICADORES *******************************
    /**
     * setX
     * 
     * Metodo modificador usado para cambiar la posicion en x del objeto
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * 
     */
    public void setX(int iX) {
        this.iX = iX;
        modificaCentro();
    }
    
    
    /**
     * setY
     * 
     * Metodo modificador usado para cambiar la posicion en y del objeto 
     * 
     * @param iY es la <code>posicion en y</code> del objeto.
     * 
     */
    public void setY(int iY) {
        this.iY = iY;
        modificaCentro();
    }
    
    
    /**
     * setXY
     * 
     * Metodo modificador usado para cambiar la posicion en x, y del objeto 
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * 
     */
    public void setXY(int iX, int iY) {
        this.iX = iX;
        this.iY = iY;
        modificaCentro();
    }
    
    
    /**
     * setCentroXY
     * 
     * Metodo modificador usado para cambiar el centro en x, y del objeto 
     * 
     * @param iCentroObjX es el <code>centro en x</code> del objeto.
     * @param iCentroObjY es el <code>centro en y</code> del objeto.
     * 
     */
    public void setCentroObjXY(int iCentroObjX, int iCentroObjY) {
        this.iCentroObjX = iCentroObjX;
        this.iCentroObjY = iCentroObjY;
    }
    
    
    /**
     * setImagen
     * 
     * Metodo modificador usado para cambiar el icono de imagen del objeto
     * tomandolo de un objeto imagen
     * 
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public void setImagen(Image imaImagen) {
        this.imaImagen = imaImagen;
    }
    // *************************************************************************    
    
    

    
    
    // ************************** METODOS **************************************
    /**
     * paint
     * 
     * Metodo para pintar el animal
     * 
     * @param graGrafico    objeto de la clase <code>Graphics</code> para graficar
     * @param imoObserver  objeto de la clase <code>ImageObserver</code> es el 
     *    Applet donde se pintara
     * 
     */
    public void paint(Graphics graGrafico, ImageObserver imoObserver) {
        graGrafico.drawImage(getImagen(), getX(), getY(), getAncho(), getAlto(), 
                imoObserver);
    }
    
    
    /**
     * modificaCentro
     * 
     * Metodo para modificar el centro del objeto
     * 
     */
    public void modificaCentro() {
        this.setCentroObjXY(this.getX() + this.getAncho() / 2,
                this.getY() + this.getAlto() / 2);
    }
    // *************************************************************************
}