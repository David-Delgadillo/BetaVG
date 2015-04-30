/**
 * ManoYMatamoscas
 *
 * Modela la definicion de todos los objetos de tipo
 * <code>ManoYMatamoscas</code>
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
import java.util.LinkedList;

public class ManoYMatamoscas extends Base {
    
    // *************************** ATRIBUTOS ***********************************
    private int iTipo;
    private int iVelocidad;
    private int iPower;
    private int iVidas;
    private int iPuntos;
    private int iTiempo;
    private long lFaltante;
    private boolean bGolpeando;
    Mano manMano;
    private Matamoscas matMatamoscas;
    // *************************************************************************
    
    
    
    
    
    // *************************** CONSTRUCTOR *********************************
    /**
     * ManoYMatamoscas
     * 
     * Metodo constructor usado para crear el objeto ManoYMatamoscas
     * extendiendo funciones de la clase Base.
     * 
     * @param iX es la <code>Posicion en x</code> del objeto.
     * @param iY es la <code>Posicion en y</code> del objeto.
     * @param iAncho es el <code>Ancho</code> del objeto.
     * @param iAlto es el <code>Largo</code> del objeto.
     * @param iTipo es el <code>Tipo</code> del objeto.
     * @param iVelocidad es la <code>Velocidad</code> del objeto.
     * @param iPower es el <code>Poder</code> del objeto.
     * @param iVidas es las <code>Vidas</code> del objeto.
     * @param iPuntos es los <code>Puntos</code> del objeto.
     * @param iTiempo es los <code>Puntos</code> del objeto.
     * @param lFaltante es los <code>Puntos</code> del objeto.
     * @param bGolpeando es los <code>Puntos</code> del objeto.
     * @param imaImagen es la <code>Imagen</code> del objeto.
     * 
     */
    public ManoYMatamoscas(int iX, int iY , int iAncho, int iAlto, int iTipo, 
            int iVelocidad, int iPower, int iVidas, int iPuntos, int iTiempo, 
            long lFaltante, boolean bGolpeando, Image imaImagen) {
        super(iX, iY, iAncho, iAlto, imaImagen);
        this.iTipo = iTipo;
        this.iVelocidad = iVelocidad;
        this.iPower = iPower;
        this.iVidas = iVidas;
        this.iPuntos = iPuntos;
        this.iTiempo = iTiempo;
        this.lFaltante = lFaltante;
        this.bGolpeando = bGolpeando;
        
        Mano manM = new Mano(this.getX() + 10, this.getY() + this.getAlto() / 2,
                iAncho - 40, iAlto / 2 + 10);
        Matamoscas matM = new Matamoscas(iX, iY, iAncho, iAlto / 2 + 5);
        
        this.manMano = manM;
        this.matMatamoscas = matM;
        
        //System.out.println(this.manMano.getY() + " " + this.getY());
        //System.out.println(this.manMano.getAlto() + " " + this.getAlto());
    }
    // *************************************************************************
    
    
    
    
    
    // ********************** METODOS DE ACCESO ********************************
    /**
     * getTipo
     * 
     * Metodo de acceso que regresa el tipo del objeto 
     * 
     * @return iTipo es el <code>tipo</code> del objeto.
     * 
     */
    public int getTipo() {
        return this.iTipo;
    }
    
    
    /**
     * getVelocidad
     * 
     * Metodo de acceso que regresa la velocidad del objeto 
     * 
     * @return iVelocidad es la <code>velocidad</code> del objeto.
     * 
     */
    public int getVelocidad() {
        return this.iVelocidad;
    }
    
    
    /**
     * getPower
     * 
     * Metodo de acceso que regresa el poder del objeto 
     * 
     * @return iPower es el <code>Poder</code> del objeto.
     * 
     */
    public int getPower() {
        return this.iPower;
    }
    
    
    /**
     * getVidas
     * 
     * Metodo de acceso que regresa las vidas del objeto 
     * 
     * @return iVidas es las <code>Vidas</code> del objeto.
     * 
     */
    public int getVidas() {
        return this.iVidas;
    }
    
    
    /**
     * getPuntos
     * 
     * Metodo de acceso que regresa los puntos del objeto 
     * 
     * @return iPuntos es los <code>Puntos</code> del objeto.
     * 
     */
    public int getPuntos() {
        return this.iPuntos;
    }
    
    
    /**
     * getTiempo
     * 
     * Metodo de acceso que regresa el tiempo del objeto 
     * 
     * @return iTiempo es el <code>Tiempo</code> del objeto.
     * 
     */
    public int getTiempo() {
        return this.iTiempo;
    }
    
    
    /**
     * getFaltante
     * 
     * Metodo de acceso que regresa el tiempo que falta del objeto 
     * 
     * @return lFaltante es el <code>Tiempo que falta</code> del objeto.
     * 
     */
    public long getFaltante() {
        return this.lFaltante;
    }
    
    
    /**
     * getGolpeando
     * 
     * Metodo de acceso que regresa si esta golpeando o no el objeto 
     * 
     * @return bGolpeando es <code>Si esta golpeando no</code> del objeto.
     * 
     */
    public boolean getGolpeando() {
        return this.bGolpeando;
    }
    // *************************************************************************
    
    
    
    
    
    // ******************* METODOS MODIFICADORES *******************************
    /**
     * setVelocidad
     * 
     * Metodo modificador usado para cambiar la velocidad del objeto
     * 
     * @param iMovimiento es la <code>Velocidad</code> del objeto.
     * 
     */
    public void setVelocidad(int iVelocidad) {
        this.iVelocidad = iVelocidad;
    }
    
    
    /**
     * setPower
     * 
     * Metodo modificador usado para cambiar el poder del objeto
     * 
     * @param iPower es el <code>Poder</code> del objeto.
     * 
     */
    public void setPower(int iPower) {
        this.iPower = iPower;
    }
       
    
    /**
     * setVidas
     * 
     * Metodo modificador usado para cambiar las vidas del objeto
     * 
     * @param iVidas es las <code>Vidas</code> del objeto.
     * 
     */
    public void setVidas(int iVidas) {
        this.iVidas = iVidas;
    }
    
    
    /**
     * setPuntos
     * 
     * Metodo modificador usado para cambiar los puntos del objeto
     * 
     * @param iPuntos es los <code>Puntos</code> del objeto.
     * 
     */
    public void setPuntos(int iPuntos) {
        this.iPuntos = iPuntos;
    }
    
    
    /**
     * setTipo
     * 
     * Metodo modificador usado para cambiar el tipo del objeto
     * 
     * @param iTipo es el <code>tipo</code> del objeto.
     * 
     */
    public void setTipo(int iTipo) {
        this.iTipo = iTipo;
    }
    
    
    /**
     * setTiempo
     * 
     * Metodo modificador usado para cambiar el tiempo del objeto
     * 
     * @param iTiempo es el <code>Tiempo</code> del objeto.
     * 
     */
    public void setTiempo(int iTiempo) {
        this.iTiempo = iTiempo;
    }
    
    
    /**
     * setFaltante
     * 
     * Metodo modificador usado para cambiar el tiempo faltante del objeto
     * 
     * @param lFaltante es el <code>Tiempo faltante</code> del objeto.
     * 
     */
    public void setFaltante(long lFaltante) {
        this.lFaltante = lFaltante;
    }
    
    
    /**
     * setGolpeando
     * 
     * Metodo modificador usado para cambiar el tiempo faltante del objeto
     * 
     * @param bGolpeando es el <code>Tiempo faltante</code> del objeto.
     * 
     */
    public void setGolpeando(boolean bGolpeando) {
        this.bGolpeando = bGolpeando;
    }
    // *************************************************************************
    
    
    
    
    
    // ************************** METODOS **************************************
    /**
     * mueve
     * 
     * Metodo que modifica la posicion del objeto seg�n el mouse checando que no
     * se pase de la medida y que no est� pausado el juego
     * 
     * @param iMouseX es el <code>integer</code> de posicion del mouse en eje X
     * @param iMouseX es el <code>integer</code> de posicion del mouse en eje Y
     * @param iAncho es el <code>integer</code> de ancho de la ventana
     * @param iAlto es el <code>integer</code> de altura de la ventana
     * @param bPausa es el <code>boolean</code> que indica si hay pausa
     * 
     */
    public void mueve(int iMouseX, int iMouseY, int iAncho, int iAlto, 
            boolean bPausa) {
        // si el juego no esta pausado
        if (!bPausa) {
            // revisa si posicion de objeto esta dentro de la ventana en anchura
            if (iMouseX > 0 && iMouseX + this.getAncho() < iAncho) {
                // Se modifica la posicion de mouse y matamoscas en eje X
                this.setX(iMouseX);
                this.manMano.setX(iMouseX + 20);
                this.matMatamoscas.setX(iMouseX);
            }
            // revisa si posicion de objeto esta dentro de la ventana en altura
            if (iMouseY > 0 && iMouseY + this.getAlto() < iAlto - 20) {
                // Se modifica la posicion de mouse y matamoscas en eje Y
                this.setY(iMouseY);
                this.manMano.setY(iMouseY + this.manMano.getAlto());
                this.matMatamoscas.setY(iMouseY - 10);
            }
        }
    }
    
    /**
     * revisaTiempo
     * 
     * Metodo que verifica el tiempo que le queda al matamoscas para dejar la
     * acci�n de golpear y estar listo para golpear de nuevo
     * 
     */
    public void revisaTiempo() {
        // Si todavia hay tiempo restante
        if (this.getFaltante() > 0) {
            // Se le resta uno al tiempo que queda
            this.setFaltante(this.getFaltante() - 20);
        } // Si ya no hay tiempo restante
        else if (this.getFaltante() <= 0) {
            // Se indica que el objeto no est� realizando un golpe
            this.setGolpeando(false);
        }
    }
    
    /**
     * golpea
     * 
     * Metodo que cambia el estado del matamoscas a golpeando y le asigna el 
     * tiempo que dura.
     * 
     * @param lklEnemigo es el <code>LinkedList</code> que tiene la lista de
     *          enemigos
     * 
     */
    public int golpea(LinkedList lklEnemigo) {
        // Si el matamoscas esta golpeando
        if (!bGolpeando) {
            // se asigna tiempo de golpe y se a�ade enemigo a lista de golpeado
            this.setFaltante(0);
            this.setGolpeando(true);
            return golpeaEnemigo(lklEnemigo);
        }
        else {
            revisaTiempo();
        }
        return -1;
    }
    
    /**
     * golpeaEnemigo
     * 
     * Metodo que checa si la posicion del golpeo del matamoscas coincide con
     * alguno de todos los enemigos que se encuentren en la lista
     * 
     * @param lklEnemigo es el <code>LinkedList</code> que tiene la lista de
     *          enemigos
     * 
     */
    public int golpeaEnemigo(LinkedList lklEnemigo) {
        // Si la lista de enemigos no est� vac�a
        if (!lklEnemigo.isEmpty()) {
            int iI = 0; // Iterador
            for (Object eneEnemigo : lklEnemigo) {
                // Si el matamoscas intersecta con el enemigo
                if (matMatamoscas.intersectaEnemigo(eneEnemigo)) {
                    return iI;
                }
                iI ++;
            }
        }
        // Si no intersecto con ning�n objeto de la lista
        return -1;
    }
    
    /**
     * colisionEnemigo
     * 
     * Metodo que checa si la posicion de la mano coincide con
     * alguno de todos los enemigos que se encuentren en la lista
     * 
     * @param lklEnemigo es el <code>LinkedList</code> que tiene la lista de
     *          enemigos
     * 
     */
    public int colisionEnemigo(LinkedList lklEnemigo) {
        // Si la lista de enemigos no est� vac�a
        if (!lklEnemigo.isEmpty()) {
            int iI = 0; // Iterador
            for (Object eneEnemigo : lklEnemigo) {
                // Si la mano intersecta con el enemigo
                if (this.manMano.intersectaEnemigo(eneEnemigo)) {
                    //System.out.println("Pase4");
                    return iI;
                }
                //System.out.println(" ");
                iI ++;
            }
        }
        // Si no intersecto con ning�n objeto de la lista
        return -1;
    }
    
    /**
     * intersecta
     * 
     * Metodo que checa si la manoYMatamoscas intersecta con una coordenada
     * 
     * @param iX es la <code>X</code> que se quiere checar.
     * @param iY es la <code>Y</code> que se quiere checar.
     * 
     * @return <code>boolean</code> 
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
        } // si no hay colision
        return false;
    }
    // *************************************************************************
}