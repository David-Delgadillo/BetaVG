/**
 * ProyectoFinal
 *
 * Este es un prototipo de lo que sera el videojuego como proyecto final
 *
 * @author Carlos Garza     A01195968
 * @author David Delgadillo A01195995
 * @author Jose Gonzalez    A01280106
 * 
 * @version 1.0
 * @date 08/ Abril/ 2015
 */

import java.awt.Color;
import java.awt.Font;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public final class ProyectoFinal extends JFrame implements Runnable, 
        KeyListener, MouseListener, MouseMotionListener {
    private static PrintWriter fileOut;
    private static BufferedReader fileIn;

    // ********************** VARIABLES GLOBALES *******************************
    // ********************** VARIABLES DEFINIDAS ******************************
    private final int iHEIGHT = 500; // Altura de la frame
    private final int iWIDTH = 800;  // Anchura de la frame
    private final int iHEIGHTEXTRA = 35; // Altura extra de la frame
    
    
    // ********************** VARIABLES ****************************************
    int iMouseX; // Posicion en X del cursor
    int iMouseY; // Posicion en Y del cursor
    int iPantallaActual; // Numero de la pantalla actual
    int iTipoJ1; // Tipo de matamoscas J1
    int iVelocidadJ1; // Velocidad del J1
    int iPowerJ1; // Power del J1.
    boolean bClickJugador1; // Se dio click
    boolean bPausa; // Pausa o no
    boolean bClick; // Click
    boolean bPresionadoMouse; // Se esta presionando el boton
    boolean bSoltadoMouse; // Se solto el boton el mouse
    boolean bKeyRelEsq; // Se solto la tecla Esq
    boolean bKeyRelEnter; // Se solto la tecla Enter
            
    // ********************** OBJETOS ******************************************
    ManoYMatamoscas manJugador1;
    Mosquito mosBoss1;
    private LinkedList<Hormiga> lklHormiga; // Lista de Hormigas
    private LinkedList<Mosquito> lklMosquito; // Lista de Mosquitos
    private LinkedList<Arana> lklArana; // Lista de Aranas
    private Boton arrBotBoton[]; // Arreglo de botones normales
    
    
    // ********************** GRAFICOS Y URLS **********************************
    private Image imaImagenApplet;   // Imagen a proyectar en Applet
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen
    
    private URL urlImaManoYMatamoscas; // URL de la imagen del matamoscas
    private URL urlImaHormiga; // URL de la imagen de las hormigas
    private URL urlImaMosquito; // URL de la imagen de los mosquitos
    private URL urlImaArana; // URL de la imagen de las aranas
    private URL urlImaBoss1; // URL de la imagen del primer boss
    private URL urlImaSenor; // URL de la imagen del senor
    private URL urlImaPressAnyKey; // URL de la imagen Press any key
    private URL urlImaLogo1; // URL de la imagen del logo del juego
    private URL urlImaLogo2; // URL de la imagen del logo del juego
    private URL urlImaFondo; // URL de la imagen de fondo
    private URL urlImaCreditos; // URL de la imagen de los creditos
    private URL urlImaTabla; // URL de la imagen de la tabla
    private URL urlImaCuadroPausa; // URL de la imagen del cuadro de pausa
    private URL urlImaCuadroSigNivel; // URL de la imagen del cuadro next lvl
    private URL urlImaCuadroGanador; // URL de la imagen del cuadro de ganador
    private URL urlImaCuadroPerdedor; // URL de la imagen del cuadro de perdedor
    private URL urlImaCuadroMultiFin; // URL de la imagen del cuadro mulijugador
    private URL urlImaBlanco; // URL de la imagen de fondo blanco
    private URL urlImaControl[]; // URLs de las imagenes de los controles
    private URL urlImaBanner[]; // URLs de las imagenes del baner
    private URL urlImaBotonA[]; // URLs de las imagenes de los botones
    private URL urlImaBotonB[]; // URLs de las imagenes de los botones sobre
    private URL urlImaBotonC[]; // URLs de las imagenes de los botones presion
    private URL urlImaDialogo[]; // URLs de los dialogos del senor
    // *************************************************************************
    
    /**
     * main
     * 
     * El main de ProyectoFinal. Manda llamar al objeto de ProyectoFinal.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crea una nueva instancia de la clase Transformaciones
        ProyectoFinal proProyectoFinal = new ProyectoFinal();
        // Se establece tamanio de frame la cual deben ser los mismos valores
        // a los almacenados en iHEIGHT y iWIDTH en 
        proProyectoFinal.setSize(800, 500 + 35); 
        proProyectoFinal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        proProyectoFinal.setVisible(true);
    }
    
    
    /**
     * iniciador
     * 
     * Inicializa todas las variables.
     * 
     */
    public void inicializador() {
        iMouseX = 0; // Posicion en X del cursor
        iMouseY = 0; // Posicion en Y del cursor
        bClickJugador1 = false; // Se dio click
        iPantallaActual = 1; // Pantalla que se encuentra actualmente
        iTipoJ1 = 1; // Tipo basico
        iVelocidadJ1 = 1; // Velocidad del J1
        iPowerJ1 = 1; // Power del J1.
        bPausa = false; // Pausa o no
        bClick = false ; // Click
        bPresionadoMouse = false; // Se esta presionando el boton
        bSoltadoMouse = false; // Se solto el boton el mou
        bClickJugador1 = false; // El jugador 1 hizo un click
        bKeyRelEsq = false; // Se solo la tecla ESQ
        bKeyRelEnter = false; // Se solto la tecla Enter
    }
    
    
    /**
     * Creador
     * 
     * Crea todos los objetos.
     * 
     */
    public void creador() {
        // Creo todas las listas
        lklHormiga = new LinkedList();
        lklMosquito = new LinkedList(); 
        lklArana = new LinkedList(); 
        
        
        arrBotBoton = new Boton [100]; // Creo el arreglo de los botones
        
        // Creo todos los URLs
        urlImaManoYMatamoscas = this.getClass().
                getResource("manoYMatamoscas.png"); 
        urlImaHormiga = this.getClass().getResource("corazon.png");
        urlImaMosquito = this.getClass().getResource("mosquito.gif");
        urlImaArana = this.getClass().getResource("corazon.png");
        urlImaBoss1 = this.getClass().getResource("boss1.gif");
        urlImaSenor = this.getClass().getResource("senor.png");
        urlImaFondo = this.getClass().getResource("fondo.png");
        urlImaLogo1 = this.getClass().getResource("logo1.png");
        urlImaLogo2 = this.getClass().getResource("logo2.png");
        urlImaPressAnyKey = this.getClass().getResource("pressAnyKey.png");
        urlImaCreditos = this.getClass().getResource("creditos.png");
        urlImaTabla = this.getClass().getResource("tabla.png");
        urlImaCuadroPausa = this.getClass().getResource("cuadroPausa.png");
        urlImaCuadroSigNivel = this.getClass().getResource("cuadroSigLvl.png");
        urlImaCuadroGanador = this.getClass().getResource("cuadroGanador.png");
        urlImaCuadroPerdedor = this.getClass().
                getResource("cuadroPerdedor.png");
        urlImaCuadroMultiFin = this.getClass().
                getResource("cuadroMultiFin.png");
        urlImaBlanco = this.getClass().getResource("blanco.png");
        
        urlImaControl = new URL [4]; // Creo el arreglo
                
        // Creo los URLs de las imagenes de control
        urlImaControl[0] = this.getClass().getResource("controlP1Izq.png");
        urlImaControl[1] = this.getClass().getResource("controlP1Der.png");
        urlImaControl[2] = this.getClass().getResource("controlP2Izq.png");
        urlImaControl[3] = this.getClass().getResource("controlP2Der.png");
        
        
        urlImaDialogo = new URL [10]; // Creo el arreglo
        
         // Creo los URLs de los dialogos
        for (int iI = 0; iI < 6; iI ++) {
            urlImaDialogo[iI] = this.getClass().
                    getResource("dialogo0" + iI + ".png");
        }
        
        urlImaBanner = new URL [15]; // Creo el arreglo de los banners
        
        // Creo los URLs de los banners
        for (int iI = 0; iI < 10; iI ++) {
            urlImaBanner[iI] = this.getClass().
                    getResource("banner0" + iI + ".png");
        }
        
        // Creo los arreglos de las imagenes de los botones
        urlImaBotonA = new URL [100];
        urlImaBotonB = new URL [100];
        urlImaBotonC = new URL [100];
        
        
        // Creo los URLs de los botones
        for (int iI = 0; iI < 10; iI ++) {
            urlImaBotonA[iI] = this.getClass().
                    getResource("botonA0" + iI + ".png");
            urlImaBotonB[iI] = this.getClass().
                    getResource("botonB0" + iI + ".png");
            urlImaBotonC[iI] = this.getClass().
                    getResource("botonC0" + iI + ".png");
        }
        
        // Creo los URLs de los botones
        for (int iI = 10; iI < 30; iI ++) {
            urlImaBotonA[iI] = this.getClass().
                    getResource("botonA" + iI + ".png");
            urlImaBotonB[iI] = this.getClass().
                    getResource("botonB" + iI + ".png");
            urlImaBotonC[iI] = this.getClass().
                    getResource("botonC" + iI + ".png");
        }
        
        // Creo los URLs de los botones
        for (int iI = 30; iI < 45; iI ++) {
            urlImaBotonA[iI] = this.getClass().getResource("botonA33.png");
            urlImaBotonB[iI] = this.getClass().getResource("botonA33.png");
            urlImaBotonC[iI] = this.getClass().getResource("botonA33.png");
        }
        
        // Creo los URLs de los botones
        for (int iI = 45; iI < 66; iI ++) {
            urlImaBotonA[iI] = this.getClass().
                    getResource("botonA" + iI + ".png");
            urlImaBotonB[iI] = this.getClass().
                    getResource("botonB" + iI + ".png");
            urlImaBotonC[iI] = this.getClass().
                    getResource("botonC" + iI + ".png");
        }
        
        // Creo los URLs de los botones
        for (int iI = 66; iI < 100; iI ++) {
            urlImaBotonA[iI] = this.getClass().getResource("corazon.png");
            urlImaBotonB[iI] = this.getClass().getResource("corazon.png");
            urlImaBotonC[iI] = this.getClass().getResource("corazon.png");
        }
    }
    
    
    /**
     * Limpiador
     * 
     * Limpia las variables y objetos.
     * 
     */
    public void limpiador() {
        // Limbia las listas encadenadas
        lklHormiga.clear();
        lklMosquito.clear();
        lklArana.clear();
    }
    
    
    /**
     * llenaBotones
     * 
     * Le da valores a los botones para que puedan funcionar
     * 
     */
    public void llenaBotones() {
        try {
            //Se abre el archivo de texto
            fileIn = new BufferedReader(new FileReader("textoBotones.txt"));
            String strLinea; // String para leer las lineas
            
            int iI = 0; // Iterador
            while(fileIn.ready() && iI < 80) { // Ciclo para leer el archivo
                strLinea = fileIn.readLine();
                StringTokenizer stkTokens = new StringTokenizer(strLinea, "\\");
                
                /* Declaracion de variables auxiliares para la lectura del 
                 * archivo */
                int iX, iY, iAl, iAn, iPAc, iPAp, iT;
                
                // Leo el archivo
                iX = Integer.parseInt(stkTokens.nextToken().trim());
                iY = Integer.parseInt(stkTokens.nextToken().trim());
                iAn = Integer.parseInt(stkTokens.nextToken().trim());
                iAl = Integer.parseInt(stkTokens.nextToken().trim());
                iPAc = Integer.parseInt(stkTokens.nextToken().trim());
                iPAp = Integer.parseInt(stkTokens.nextToken().trim());
                iT = Integer.parseInt(stkTokens.nextToken().trim());
                
                // Se declara y crea un boton auxiliar
                Boton botAux = new Boton(iX, iY, iAn, iAl, iPAc, iPAp, iT,
                        Toolkit.getDefaultToolkit().getImage(urlImaBotonA[iI]));
                
                arrBotBoton[iI] = botAux; // Le asigna valor al boton
                
                iI ++; // Aumenta el iterador
            } 
        }
        catch (IOException | NumberFormatException excE) {
            System.out.println(excE.toString()); // Muestra el error
        }
    }
    
    /*
    public void generadorAranas() {
        
    }
    
    public void generadorHormigas() {
        
    }
    
    public void generadorMosquitos() {
        
    }
    */
    
    /** 
     * ProyectoFinal
     * 
     * Metodo constructor de la clase <code>ProyectoFinal</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>JFrame</code> y se definen funcionalidades.
     * 
     */
    public ProyectoFinal() {
        setBounds(300, 100, 800, 800); // Para que sea creada al centro
        setResizable(false); // Que no se le pueda dar otro tamano
        
        inicializador(); // Llama al metodo para inicializar las variables
        creador(); // Llama al metodo para crear los objetos
        llenaBotones(); // Llama al metodo para generar correctamente botones
        
        manJugador1 = new ManoYMatamoscas(iHEIGHT, iWIDTH, 65, 105, iTipoJ1, 
                iVelocidadJ1, iPowerJ1, 3, 0, 0, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaManoYMatamoscas));
        
        
        for(int iI = 0; iI < 3; iI ++) {
            Mosquito mosAux;
            mosAux = new Mosquito(150 + iI * 100, 150 + iI * 100, 40, 40, 1, 1, 
                    3, Toolkit.getDefaultToolkit().getImage(urlImaMosquito));
            
            lklMosquito.add(mosAux);
        }
        
        mosBoss1 = new Mosquito(450, 100, 150, 200, 1, 1, 
                3, Toolkit.getDefaultToolkit().getImage(urlImaBoss1));

        
        
        // Eventos del mouse y teclado
        addMouseListener(this);
        addMouseMotionListener(this);
	addKeyListener(this);
        
        Thread th = new Thread (this); // Declara y crea el hilo
        th.start(); // Empieza el hilo
    }
    
    
    /** 
     * run
     * 
     * Metodo sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, que contendrá las instrucciones
     * de nuestro juego.
     * 
     */
    public void run() {
        repaint();
        try {
            Thread.sleep (3000); // El thread se duerme.
        } catch (InterruptedException ex) {
            Logger.getLogger(ProyectoFinal.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        do {
            // Actualiza las posiciones de los objetos en la pantalla
            actualiza(); 
            
            // Revisa si existen coliciones entre objetos en la pantalla
            checaColision();
            
            // Repinta la pantalla
            repaint();

            try {
                Thread.sleep (20); // El thread se duerme.
            }
            catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString()); // Muestra el error
            }
        }
        while (true); // While total
    }

    
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion de los objetos 
     * 
     */
    public void actualiza(){
        if (iPantallaActual == 1) {
            iPantallaActual = 2;
        }
        manJugador1.revisaTiempo();
        manJugador1.mueve(iMouseX, iMouseY, iWIDTH, iHEIGHT + iHEIGHTEXTRA, 
                false);
        //stdOut.println(manJugador1.getFaltante());
        
        for(int iI = 0; iI < 66; iI ++) {
            if (arrBotBoton[iI].intersecta(iMouseX, iMouseY)) {
                if (bPresionadoMouse) {
                    arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                            getImage(urlImaBotonB[iI]));
                }
                else if (bSoltadoMouse && iPantallaActual == 
                        arrBotBoton[iI].getPantallaActual()) {
                    iPantallaActual = arrBotBoton[iI].getPantallaApunta();
                    bSoltadoMouse = false;
                    System.out.println(iI);
                }
                else {
                    arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                            getImage(urlImaBotonC[iI]));
                }
            }
            else {
                arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                        getImage(urlImaBotonA[iI]));
            }
        }
        
        // Cambia a la pantalla de pausa
        if (bKeyRelEsq && iPantallaActual == 13) {
            iPantallaActual = 14;
        }
        
        // Cambia a la pantalla de juego
        else if (bKeyRelEsq && iPantallaActual == 14) {
            iPantallaActual = 13;
        }
        
        // Cambia a la pantalla de siguiente nivel
        else if (bKeyRelEnter && iPantallaActual == 14) {
            iPantallaActual = 15;
        }
        
        // Cambia a la pantalla de ganador
        else if (bKeyRelEnter && iPantallaActual == 15) {
            iPantallaActual = 16;
        }
        
        // Cambia a la pantalla de perdedor
        else if (bKeyRelEnter && iPantallaActual == 16) {
            iPantallaActual = 17;
        }
        
        // Cambia a la pantalla de Multijugador fin del juego
        else if (bKeyRelEnter && iPantallaActual == 17) {
            iPantallaActual = 18;
        }
        
        else if (bKeyRelEnter && iPantallaActual == 18) {
            iPantallaActual = 3;
        }
        
        
        // Reinicializa las variables booleanas
        bClick = false;
        bSoltadoMouse = false;
        bKeyRelEsq = false;
        bKeyRelEnter = false;
    }
    
    /**
     * checaColision
     * 
     * Metodo usado para checar la colision entre objetos
     * 
     */
    public void checaColision() {
        // Revisa colision del jugador con las moscas
        // manJugador1.colisionEnemigo(lklHormiga); 
    }
    
    
    
    
    /**
     * paint
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y 
     * define cuando usar ahora el paint
     * 
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     * 
     */
    @Override
    public void paint (Graphics graGrafico){
        // Inicializan el DoubleBuffer
        if (imaImagenApplet == null){
                imaImagenApplet = createImage (this.getSize().width, 
                        this.getSize().height);
                graGraficaApplet = imaImagenApplet.getGraphics ();
        }
        
        // Actualiza la imagen de fondo.
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaFondo), 0, 0, iWIDTH, iHEIGHT + 50, this);

        // Actualiza el Foreground.
        graGraficaApplet.setColor(getForeground());
        try {
            paint1(graGraficaApplet);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProyectoFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Dibuja la imagen actualizada
        graGrafico.drawImage (imaImagenApplet, 0, 0, this);
    }
    
    /**
     * paint1
     * 
     * Metodo sobrescrito de la clase <code>JFrame</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * 
     * @param graDibujo es el objeto de <code>Graphics</code> usado para dibujar
     * 
     */
    public void paint1(Graphics graDibujo) throws InterruptedException {
        // revisa la pantalla que se esta cargando
        switch(iPantallaActual) {
            case 1: // Pantalla del logo del equipo
                // Fondo negro
                URL urlImaNegro = this.getClass().getResource("negro.png");
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaNegro), 0, 0, 1000, 1000, this);
                
                URL urlImaLogoEquipo = this.getClass().
                        getResource("logoEquipo.png");
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaLogoEquipo), 275, 125, 250, 250, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.white);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 50));
                graDibujo.drawString("FINAL BOSS", 250, 450);
                
                break;
            case 2: // Pantalla con el logo del juego
                // Logo del juego
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaLogo1), 75, 45, 650, 350, this);
                
                // Press any key
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaPressAnyKey), 260, 400, 300, 50, this);
                break;
            case 3: // Pantalla de menu principal
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[0]), 625, 35, 175, 125, this);
                
                // Logo del juego
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaLogo2), 185, 35, 450, 145, this);
                
                // Pinta los botones
                for (int iI = 0; iI < 4; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 4: // Pantalla seleccion Solo/Multi
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[1]), 625, 35, 175, 125, this);
                
                // Pinta los botones
                for (int iI = 5; iI < 8; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 5: // Pantalla de seleccion de juego SOLO
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[2]), 625, 35, 175, 125, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[0]), 540, 420, 300, 80, this);
                
                // Pinta los botones
                for (int iI = 8; iI < 14; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 6: // Pantalla de seleccion de juego Multi
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[3]), 625, 35, 175, 125, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[1]), 540, 420, 300, 80, this);
                
                // Pinta los botones
                for (int iI = 14; iI < 20; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 7: // Pantalla de Tienda
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[4]), 625, 35, 175, 125, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[2]), 540, 420, 300, 80, this);
                
                // Pinta los botones
                for (int iI = 20; iI < 25; iI += 2) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 8: // Pantalla de Data
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[5]), 625, 35, 175, 125, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[3]), 500, 420, 300, 80, this);
                
                // Pinta los botones
                for (int iI = 25; iI < 29; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 9: // Pantalla de Achievements
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[6]), 625, 35, 175, 125, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[4]), 540, 420, 220, 75, this);
                
                // Pinta los botones
                for (int iI = 29; iI < 45; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 10: // Pantalla de Records
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[7]), 625, 35, 175, 125, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString("MODE", 340, 90);
                
                // Tabla
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaTabla), 250, 105, 280, 420, this);
                
                // Senor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaSenor), 540, 110, 175, 300, this);
                
                // Dialogo
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaDialogo[5]), 550, 420, 250, 100, this);
                
                // Pinta los botones
                for (int iI = 45; iI < 51; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 11: // Pantalla de creditos                
                // Creditos
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaCreditos), 0, 25, 800, 510, this);
                
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[8]), 625, 35, 175, 125, this);
                
                // Pinta los botones
                for (int iI = 51; iI < 52; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 12: // Pantalla de opciones
                // Banner
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBanner[9]), 625, 25, 175, 125, this);
                
                // Imagen 1 de los controles del P1
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaControl[0]), 25, 110, 340, 325, this);
                
                // Imagen 1 de los controles del P2
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaControl[2]), 380, 115, 350, 400, this);
                
                // Pinta los botones
                for (int iI = 52; iI < 59; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                
                break;
            case 13: // Pantalla de juego
                // Fondo blanco
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaBlanco), 0, 0, 1000, 1000, this);
                
                URL urlImaLives = this.getClass().getResource("lives.png");
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaLives), 680, 25, 100, 50, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("SCORE: \t 0000", 15, 55);
                
                // Se despliega cantidad de bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t 100", 15, 80);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t 05:00", 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE", 15, 520);
                
                manJugador1.paint(graDibujo, this); // Jugador 1

                // Pinto cada bloque de la lista
                if (!lklMosquito.isEmpty()) {
                    for (Mosquito eneEnemigo : lklMosquito) {
                        eneEnemigo.paint(graDibujo, this);
                    }
                }
                
                mosBoss1.paint(graDibujo, this);
                
                break;
            case 14: // Pantalla de Pausa
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("SCORE: \t 0000", 15, 55);
                
                // Se despliega los bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t 100", 15, 80);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t 05:00", 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE", 15, 520);
                
                // Cuadro de pausa
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaCuadroPausa), 255, 115, 290, 340, this);
                
                // Pinta los botones
                for (int iI = 59; iI < 62; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                break;
            
            case 15: // Pantalla de pase al siguiente nivel
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("SCORE: \t 0000", 15, 55);
                
                // Se despliega los bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t 100", 15, 80);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t 05:00", 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE", 15, 520);
                // Cuadro de pasa al siguiente nivel
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                        (urlImaCuadroSigNivel), 255, 115, 290, 320, this);
                
                // Pinta los botones
                for (int iI = 62; iI < 63; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                break;
            case 16: // Pantalla de ganador
                
                // Cuadro de ganador
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaCuadroGanador), 200, 45, 400, 460, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString("0000", 315, 270);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString("05:00", 510, 270);
                
                // Pinta los botones
                for (int iI = 63; iI < 64; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                break;
            case 17: // Pantalla de perdedor
                
                // Cuadro de perdedor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                        (urlImaCuadroPerdedor), 200, 45, 400, 460, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString("0000", 315, 270);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString("05:00", 510, 270);
                
                // Pinta los botones
                for (int iI = 64; iI < 65; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                break;
            case 18: // Pantalla de Multijugador final de partida
                
                // Cuadro de perdedor
                graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                        (urlImaCuadroMultiFin), 200, 45, 400, 460, this);
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 37));
                graDibujo.drawString("0000", 415, 203);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("05:00", 420, 322);
                
                // Pinta los botones
                for (int iI = 65; iI < 66; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                break;
        }
    }
    
    /**
     * keyReleased
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al teclear
     * @param keyEvent es el <code>KeyEvent</code> que se genera.
     * 
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Si se suelta la tecla ESQ
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bKeyRelEsq = true;
        }
        
        // Si se suelta la tecla Enter
        else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            bKeyRelEnter = true;
        }
    }
    
    
    /**
     * keyTyped
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una 
     * tecla que no es de accion.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al presionar.
     * 
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // no hay codigo pero se debe escribir el metodo
    }
    
    
    /**
     * keyPressed
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al dejar presionada
     * alguna tecla.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al presionar
     * 
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // Press any key para el cambio de pantalla
        if (iPantallaActual == 2) {
            iPantallaActual = 3;
        }
    }
    
    
    /**
     * mouseClicked
     * 
     * Metodo sobrescrito de la interface <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al hacer un click con el
     * mouse o el pad.
     * 
     * @param mseEvent es el <code>MouseEvent</code> que se genera al clikear
     * 
     */
    @Override
    public void mouseClicked(MouseEvent mseEvent) {
        // manJugador1.golpea(lklHormiga);
        bClick = true; // Guarda si existe click
    }
    
    
    /**
     * mousePressed
     * 
     * Metodo sobrescrito de la interface <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al dejar presionado el 
     * boton del mouse.
     * 
     * @param mseEvent es el <code>MouseEvent</code> que se genera al presionar
     * 
     */
    @Override
    public void mousePressed(MouseEvent mseEvent) {
        //manJugador1.golpea(lklHormiga);
        bPresionadoMouse = true; // Guarda si se presiona el mouse
    }
    
    
    /**
     * mouseReleased
     * 
     * Metodo sobrescrito de la interface <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar el boton del 
     * mouse.
     * 
     * @param mseEvent es el <code>MouseEvent</code> que se genera al soltar
     * 
     */
    @Override
    public void mouseReleased(MouseEvent mseEvent) {
        bPresionadoMouse = false; // Guarda que se ya no se presiona el mouse
        bSoltadoMouse = true; // Guarda que se solto el mouse
    }
    
    
    /**
     * mouseEntered
     * 
     * Metodo sobrescrito de la interface <code>MouseMotionListener</code>.<P>
     * En este metodo maneja el evento que se genera cuando el cursor entra en
     * la ventana
     * 
     * @param mseEvent es el <code>MouseEvent</code> que se genera al entrar
     * 
     */
    @Override
    public void mouseEntered(MouseEvent mseEvent) {
        // no hay codigo pero se debe escribir el metodo
    }
    
    
    /**
     * mouseExited
     * 
     * Metodo sobrescrito de la interface <code>MouseMotionListener</code>.<P>
     * En este metodo maneja el evento que se genera cuando el cursor sale de
     * la ventana
     * 
     * @param mseEvent es el <code>MouseEvent</code> que se genera al salir
     * 
     */
    @Override
    public void mouseExited(MouseEvent mseEvent) {
        // no hay codigo pero se debe escribir el metodo
    }
    
    
    /**
     * mouseDragged
     * 
     * Metodo sobrescrito de la interface <code>MouseMotionListener</code>.<P>
     * En este metodo maneja el evento que se genera cuando el cursor se esta
     * presionando mientras se mueve
     * 
     * @param mseEvent es el <code>MouseEvent</code> que representa dragging
     * 
     */
    @Override
    public void mouseDragged(MouseEvent mseEvent) {
        // Obtiene posicion en X y Y del mouse
        iMouseX = mseEvent.getX();
        iMouseY = mseEvent.getY();
    }
    
    
    /**
     * mouseMoved
     * 
     * Metodo sobrescrito de la interface <code>MouseMotionListener</code>.<P>
     * En este metodo maneja el evento que se genera cuando el cursor se mueve
     * 
     * @param mseEvent es el <code>MouseEvent</code> que representa movimiento
     * 
     */
    @Override
    public void mouseMoved(MouseEvent mseEvent) {
        // Obtiene posicion en X y Y del mouse
        iMouseX = mseEvent.getX();
        iMouseY = mseEvent.getY();
    }
}
