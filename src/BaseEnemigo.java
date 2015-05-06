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
    private int iMovimientoActual; // El movimiento que esta ejecutando
    private int iCambioMovimiento; // Cuanto debe esperar el cambio
    private int iDireccionX;     // Direccion en X del movimiento
    private int iDireccionY;    // Direccion en Y del movimiento
    private int iCentroX;   // Centro del circulo en X
    private int iCentroY;  // Centro del circulo en Y
    private int iRadio;     // Radio del circulo
    int iTiempoDeDisparo; // Tiempo que debe esperar para disparar
    private boolean bSentido;   // Sentido en el que se mueve
    private double dThetas;     // Variable usada para los circulos
    
    
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
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public BaseEnemigo(int iX, int iY , int iAncho, int iAlto, int iVida,
            int iTiempo, int iVelocidad, int iFrecuencia, int iEntrada, 
            boolean bSale, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iVelocidad = iVelocidad;
        this.iTiempo = iTiempo;
        this.iVida = iVida;
        this.iFrecuencia = iFrecuencia;
        this.iEntrada = iEntrada;
        this.bSale = bSale;
        this.iDireccionX = 0;
        this.iDireccionY = 0;
        this.iMovimientoActual = 0;
        this.iCambioMovimiento = 30;
        this.iCentroX = 0;
        this.iCentroY = 0;
        this.iRadio = 0; 
        this.bSentido = true;
        this.dThetas = 0.0d;
        this.iTiempoDeDisparo = 6000;
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
     * getMovimientoActual
     * 
     * Metodo de acceso que regresa el movimiento actual del objeto 
     * 
     * @return iMovimientoActual es el <code>movimiento actual</code> del objeto.
     * 
     */
    public int getMovimientoActual() {
        return this.iMovimientoActual;
    }
    
    
    /**
     * getCambioMovimiento
     * 
     * Metodo de acceso que regresa el cambio de movimiento del objeto 
     * 
     * @return iCambioMovimiento es el <code>cambio movimiento</code> del objeto
     * 
     */
    public int getCambioMovimiento() {
        return this.iCambioMovimiento;
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
    
    
    /**
     * getCentroX
     * 
     * Metodo de acceso que regresa el Centro X del objeto 
     * 
     * @return iCentroX es el <code>Centro X</code> del objeto.
     * 
     */
    public int getCentroX() {
        return this.iCentroX;
    }
    
    
    /**
     * getCentroY
     * 
     * Metodo de acceso que regresa el Centro Y del objeto 
     * 
     * @return iCentroY es el <code>Centro Y</code> del objeto.
     * 
     */
    public int getCentroY() {
        return this.iCentroY;
    }
    
    
    /**
     * getRadio
     * 
     * Metodo de acceso que regresa el radio del objeto 
     * 
     * @return iRadio es el <code>radio</code> del objeto.
     * 
     */
    public int getRadio() {
        return this.iRadio;
    }
    
    
    /**
     * getSentido
     * 
     * Metodo de acceso que regresa el sentido del objeto 
     * 
     * @return bSentido es el <code>sentido</code> del objeto.
     * 
     */
    public boolean getSentido() {
        return this.bSentido;
    }
    
    
    /**
     * getThetas
     * 
     * Metodo de acceso que regresa un calculable del objeto 
     * 
     * @return dThetas es un <code>calculable</code> del objeto.
     * 
     */
    public double getThetas() {
        return this.dThetas;
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
     * setMovimientoActual
     * 
     * Metodo modificador usado para cambiar el movimiento actual del objeto
     * 
     * @param iMovimientoActual es el <code>movimiento actual</code> del objeto.
     * 
     */
    public void setMovimientoActual(int iMovimientoActual) {
        this.iMovimientoActual = iMovimientoActual;
    }
    
    
    /**
     * setCambioMovimiento
     * 
     * Metodo modificador usado para cambiar el cambio de movimiento del objeto
     * 
     * @param iCambioMovimiento es el <code>cambio movimiento</code> del objeto.
     * 
     */
    public void setCambioMovimiento(int iCambioMovimiento) {
        this.iCambioMovimiento = iCambioMovimiento;
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
    
    
    /**
     * setCentro
     * 
     * Metodo modificador usado para cambiar el centro del objeto
     * 
     * @param iX es el <code>Centro X</code> del objeto.
     * @param iY es el <code>Centro Y</code> del objeto.
     * 
     */
    public void setCentro(int iX, int iY) {
        this.iCentroX = iX;
        this.iCentroY = iY;
    }
    
    
    /**
     * setCentroX
     * 
     * Metodo modificador usado para cambiar el centro X del objeto
     * 
     * @param iX es el <code>Centro X</code> del objeto.
     * 
     */
    public void setCentroX(int iX) {
        this.iCentroX = iX;
    }
    
    
    /**
     * setCentroY
     * 
     * Metodo modificador usado para cambiar el centro Y del objeto
     * 
     * @param iY es el <code>Centro Y</code> del objeto.
     * 
     */
    public void setCentroY(int iY) {
        this.iCentroY = iY;
    }
    
    
    /**
     * setRadio 
     * 
     * Metodo modificador usado para cambiar el radio del objeto
     * 
     * @param iRadio es el <code>radio</code> del objeto.
     * 
     */
    public void setRadio(int iRadio) {
        this.iRadio = iRadio;
    }
    
    
    /**
     * setSentido 
     * 
     * Metodo modificador usado para cambiar el bSentido del objeto
     * 
     * @param bSentido es el <code>Sentido</code> del objeto.
     * 
     */
    public void setSentido(boolean bSentido) {
        this.bSentido = bSentido;
    }
    
    
    /**
     * setThetas 
     * 
     * Metodo modificador usado para cambiar un calculable del objeto
     * 
     * @param dThetas es un <code>calculable</code> del objeto.
     * 
     */
    public void setThetas(double dThetas) {
        this.dThetas = dThetas;
    }
    // *************************************************************************
        
    
    
    
    
    // ************************** METODOS **************************************
    /*
     * dispara
     * 
     * Revisa si se pueden disparar proyectiles del objeto
     * 
     * @return <code>booleano</code> si dispara objeto.
     */
    public boolean dispara() {
        // Revisa si ya es tiempo de disparar
        if (this.iTiempoDeDisparo <= 0) {
            // Reasigna tiempo aleatorio al disparo
            this.iTiempoDeDisparo = (int) (Math.random() * 2000 + 2000);
            return true;
        }
        // Actualiza el tiempo de disparo
        this.iTiempoDeDisparo -= 20;
        return false;
    }
    
    
    /*
     * movLineal
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iW es el <code>Ancho</code> del objeto.
     * @param iH es el <code>Alto</code> del objeto.
     * 
     * @return un punto <code>Point</code> de la nueva posicion
     */
    public Point movLineal(int iW, int iH) {
        // Para cambiar la direccion del movimiento
        if (this.getTiempo() == 0) {
            // Modifica la direccion del movimiento
            double dTheta = 2.0d * Math.PI * Math.random();
            int iAuxX = (int) (Math.cos(dTheta) * this.getVelocidad());
            int iAuxY = (int) (Math.sin(dTheta) * this.getVelocidad());
            this.setDireccion(iAuxX, iAuxY);
        }
        
        // Variables del nuevo movimiento
        int iNewX = this.getX() + this.getDireccionX();
        int iNewY = this.getY() + this.getDireccionY();
        
        
        // Que no se salga de la pantalla
        if (iNewX < 0 || iNewX + this.getAncho() > iW) {
           this.setDireccionX(- this.getDireccionX());
           iNewX += (this.getDireccionX() * 2);
        }
        if (iNewY < 35 || iNewY + this.getAlto() > iH - 15) {
           this.setDireccionY(- this.getDireccionY());
           iNewY += (this.getDireccionY() * 2);
        }
        
        return new Point(iNewX, iNewY); // Actualiza el movimiento
    }
    
    
    /*
     * movCircular
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iW es el <code>Ancho</code> del objeto.
     * @param iH es el <code>Alto</code> del objeto.
     * 
     * @return un punto <code>Point</code> de la nueva posicion
     */
    public Point movCircular(int iW, int iH) {
        // Para cambiar la direccion del movimiento
        if (this.getTiempo() == 0) {
            // Modifica la direccion del movimiento
            this.setThetas(Math.random() * 2.0d * Math.PI);
            this.setCentroX(this.getX() + (int) (Math.cos(this.getThetas()) * 
                    this.getRadio())); 
            this.setCentroY(this.getY() + (int) (Math.sin(this.getThetas()) * 
                    this.getRadio()));
            
            // Clockwise or counter
            this.setSentido((int) (Math.random() * 2.0d) < 1);
            
            // Asignar posicion correcta
            this.setThetas(Math.atan2(this.getY() - this.getCentroY(), 
                    this.getX() - this.getCentroX()));
        }
        
        double dSigno = bSentido ? 1.0d: -1.0d;
        this.setThetas(this.getThetas() + dSigno * 20 * 0.0035);
        
        // variables auxiliares
        int iNewX = (int) (this.getCentroX() + this.getRadio() * 
                Math.cos(this.getThetas()));
        int iNewY = (int) (this.getCentroY() + this.getRadio() * 
                Math.sin(this.getThetas()));
        
        // Que no se salga de la pantalla
        if (iNewX < 0 || iNewX + this.getAncho() > iW || 
                iNewY < 30 || iNewY + this.getAlto() > iH + 30) {
           this.setSentido(! this.getSentido());
           iNewX = this.getX();
           iNewY = this.getY();
        }
        
        return new Point(iNewX, iNewY); // regresa posicion
    }
    
    /*
     * movEspiral
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iW es el <code>Ancho</code> del objeto.
     * @param iH es el <code>Alto</code> del objeto.
     * 
     * @return un punto <code>Point</code> de la nueva posicion
     */
    public Point movEspiral(int iW, int iH) {
        // Calcula el radio actual en el que se expande
        int iRadioT = (int) (- 2.0d * this.getRadio() * 
                Math.abs(this.getTiempo() % this.getFrecuencia() - 
                this.getFrecuencia() / 2.0d) / this.getFrecuencia() + 
                this.getRadio());
        
        this.setThetas(0.002d * this.getTiempo());
        
        // Auxiliares
        int iNewX = (int) (iRadioT * Math.cos(dThetas));
        int iNewY = (int) (iRadioT * Math.sin(dThetas));
        
        return new Point(iNewX, iNewY);
    }
    
    
    /*
     * movIniciarl
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iW es el <code>Ancho</code> del objeto.
     * @param iH es el <code>Alto</code> del objeto.
     * 
     * @return un punto <code>Point</code> de la nueva posicion
     */
    public Point movIniciarl(int iW, int iH) {
        // Auxiliares
        int iNewX = this.getX();
        int iNewY = this.getY();
        
        switch(this.getEntrada()) {
            case 1:
                iNewY += 4;
                break;
            case 2:
                iNewX += 4;
                break;
            case 3:
                iNewX -= 4;
                break;
        }
        
        return new Point(iNewX, iNewY);
    }
    
    
    /*
     * movimientoMaestro
     * 
     * Actualiza la posicion del objeto
     * 
     * @param iWidth es el <code>Ancho</code> del objeto.
     * @param iHeight es el <code>Alto</code> del objeto.
     * 
     */
    public void movimientoMaestro(int iW, int iH) {
        // Actualiza el tiempo
        if (this.getTiempo() >= this.getFrecuencia()) {
            this.setTiempo(0);
        }
        
        if (this.getCambioMovimiento() == 0) {
            //this.setMovimientoActual((int) (Math.random() * 2.0d + 1.0d));
            this.setMovimientoActual(1);
            this.setCambioMovimiento(80);
        }
        int iNewX = 50;
        int iNewY = 50;
        
        switch (this.getMovimientoActual()){
            case 0: // Entrada
                // Llama metodos y actualiza
                Point poiP0 = movIniciarl(iW, iH);
                iNewX = poiP0.x;
                iNewY = poiP0.y;
                
                if (this.getX() > 3 && this.getX() + this.getAncho() < iW &&
                        this.getY() > 28 && this.getY() + this.getAlto() < 
                        iH + 25 +3) {
                }
                else {
                    this.setCambioMovimiento(20);
                }
                break;
            case 1: // Linea Recta
                // Llama metodos y actualiza
                Point poiP1 = movLineal(iW, iH);
                iNewX = poiP1.x;
                iNewY = poiP1.y;
                break;
            case 2: // Curvas
                // Obtiene un nuevo radio
                if (this.getCambioMovimiento() == 50) {
                    this.setRadio((int) (Math.random() * 20.0 + 30.0d));
                }
                // Llama metodos y actualiza
                Point poiP2 = movCircular(iW, iH);
                iNewX = poiP2.x;
                iNewY = poiP2.y;
                break;
            case 3: // Espiral
                // Llama metodos y actualiza
                Point poiP5 = movEspiral(iW, iH);
                iNewX = poiP5.x + 100;
                iNewY = poiP5.y + 100;
                break;
            case 4: // Linea Recta y Curvas
                // Obtiene un nuevo radio
                if (this.getCambioMovimiento() == 50) {
                    this.setRadio((int) (Math.random() * 20.0 + 30.0d));
                }
                // Llama metodos y actualiza
                Point poiP3 = movLineal(iW, iH);
                Point poiP4 = movCircular(iW, iH);
                iNewX = (poiP3.x + poiP4.x) / 2;
                iNewY = (poiP3.y + poiP4.y) / 2;
                break;
        }
        
        this.setXY(iNewX, iNewY); // Actualiza la posicion
        
        this.setCambioMovimiento(this.getCambioMovimiento() - 1);
        this.setTiempo(this.getTiempo() + 20); // Actualiza el tiempo
    }
    
    
    /*
     * revisaDentroPantalla
     * 
     * Revisa que el objeto este dentro de la pantalla
     * 
     * @param iW es el <code>Ancho</code> del objeto.
     * @param iH es el <code>Alto</code> del objeto.
     * 
     * @return posicion <code>boolean</code> dentro de la pantalla o no
     */
    public boolean revisaDentroPantalla(int iW, int iH) {
        return (this.getX() < 5 || this.getX() > iW || this.getY() < 25 || 
                this.getY() > iH + 15) && this.getMovimientoActual() != 0;
    }
    // *************************************************************************
}