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
 * @date 29/ Abril/ 2015
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
import java.io.RandomAccessFile;
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
    private final int iHEIGHTEXTRA = 50; // Altura extra de la frame
        // Duraciones de cada cuadro de animaciones para cada objeto
    private final int iTIEMPOBOSS = 30; // Animacion de Boss
    private final int iTIEMPOMOSCA = 100; // Animacion de mosca
    private final int iTIEMPOHORMIGA = 70; // Animacion de hormiga
    private final int iTIEMPOARANA = 50; // Animacion de arana
    private final int iTIEMPOMATAMOSCAS = 70; // Animacion de matamoscas 
    
    // ********************** VARIABLES ****************************************
    private int iMouseX; // Posicion en X del cursor
    private int iMouseY; // Posicion en Y del cursor
    private int iPantallaActual; // Numero de la pantalla actual
    private int iTipoJ1; // Tipo de matamoscas J1
    private int iVelocidadJ1; // Velocidad del J1
    private int iPowerJ1; // Power del J1.
    private int iVidasJ1; // Vidas del J1.
    private int iPuntosJ1; // Puntaje del J1
    private int iBugs; // Cantidad de bugs que faltan
    private int iNivel; // Nivel inicial
    private int iModoDeJuego; // 1 Clasic, 2 100 bugs, 3 1 min, 4 5 min, 5 end
    private int iCantMaxEnemigos; // Cantidad maxima de enemigos
    private long lTiempoEnPartida; // Tiempo que va en la partida
    private boolean bClickJugador1; // Se dio click
    private boolean bPausa; // Pausa o no
    private boolean bClick; // Click
    private boolean bIniciaJuego; // Si ya inicio el juego
    private boolean bPresionadoMouse; // Se esta presionando el boton
    private boolean bSoltadoMouse; // Se solto el boton el mouse
    private boolean bBossTime; // Hora del boss
    private boolean bKeyRelEsq; // Se solto la tecla Esq
    private boolean bKeyRelEnter; // Se solto la tecla Enter
    private boolean bTerminoNivel; // Termina el nivel
    private boolean bMusicMenu; // Control de musica de menu
    private boolean bMusicWaiting; // Esperando a que se seleccione matamoscas
    private boolean bMusicMoscas; // Si se reproduce sonido de moscas
    private boolean bMusicBoss; // Si se reproduce sonido del boss
    private long lTiempoActual; // Contiene el tiempo que ha durado el juego
    private long lTiempoMAtacando; //Controla el tiempo de animacion golpeJugado
    private long lTiempoBAtacando; // Controla el tiempo de animacion golpe boss
    private long lTiempoBossFlash; // Controla el tiempo de animacion mostrada
    private long lTiempoMataMoscaFlash; //Controla el tiempo  animacion mostrada
    private boolean bBGolpeando; // Indica que el Boss esta golpeando
    private boolean bMMGolpeando; // Indica que el Matamoscas esta golpeando
    private boolean bBossAtacado; // Indica si se le pego al boss
    private boolean bMataMoscaAtacado; // Indica si un insecto toco la mano
    private String strUserName; // Contiene el nombre del usuario
    
    
    // ********************** OBJETOS ******************************************
    private LinkedList<Mosquito> lklMosquito; // Lista de Mosquitos
    private LinkedList<Mosca> lklMosca; // Lista de Moscas
    private LinkedList<Hormiga> lklHormiga; // Lista de Hormigas
    private LinkedList<Arana> lklArana; // Lista de Aranas
    
    private Mosquito mosBoss;   // Boss de nivel 1
    private Hormiga horBoss;    // Boss de nivel 2
    private Arana araBoss;      // Boss de nivel 3
    
    // Guarda valores importantes para ejecutar animaciones de los enemigos
    private LinkedList<Golpe> lklMosquitoGolpeado;
    private LinkedList<Golpe> lklMoscaGolpeado;
    private LinkedList<Golpe> lklHormigaGolpeado;
    private LinkedList<Golpe> lklAranaGolpeado;
    
    // Lista para cada uno de los proyectiles de los bosses
    private LinkedList<Proyectil> lklProyectil1;
    private LinkedList<Proyectil> lklProyectil2;
    private LinkedList<Proyectil> lklProyectil3;
    
    private Mosquito mosAux;     // Mosquito auxiliar para tener valores default
    private Mosca mscAux;     //  Mosca auxiliar para tener valores default
    private Hormiga horAux;     //  Hormiga auxiliar para tener valores default
    private Arana araAux;     // Arana auxiliar para tener valores default
    
    private Proyectil pro1Aux;   //Proyectil auxiliar para tener valores default
    private Proyectil pro2Aux;   //Proyectil auxiliar para tener valores default
    private Proyectil pro3Aux;  //Proyectil auxiliar para tener valores default
    
    private Boton arrBotBoton[]; // Arreglo de botones normales
    private Cursor curCursor;   // Cursor
    private Cursor curCursorBlanco;    // Cursor en blanco
    private ManoYMatamoscas manJugador1;    // Jugador 1
    
    
    // ********************** Animaciones **************************************
    private Animacion aniBoss1; // Anima el Boss del nivel 1
    private Animacion aniBossGolpe1; // Anima el Boss del nivel 1 golpeado
    private Animacion aniBossAttack1; // Anima el ataque del boss nivel 1
    private Animacion aniBoss2; // Anima el Boss del nivel 2
    private Animacion aniBossGolpe2; // Anima el Boss del nivel 2 golpeado
    private Animacion aniBossAttack2; // Anima el ataque del boss nivel 2
    private Animacion aniBoss3; // Anima el Boss del nivel 3
    private Animacion aniBossGolpe3; // Anima el Boss del nivel 3 golpeado
    private Animacion aniBossAttack3; // Anima el Boss del nivel 3 atacando
    private Animacion aniHormigaR; // Anima a las hormigas
    private Animacion aniHormigaL; // Hormiga hacia la izquierda
    private Animacion aniHormigaGolpeR; // Anima a las hormigas golpeadas
    private Animacion aniHormigaGolpeL; // Hormiga hacia la izquierda
    private Animacion aniMoscaL; // Anima a las moscas
    private Animacion aniMoscaR; // Anima a las moscas hacia la derecha
    private Animacion aniMoscaGolpeL; // Anima a las moscas golpeadas
    private Animacion aniMoscaGolpeR; // Mosca hacia la derecha
    private Animacion aniMosquitoR; // Anima a los mosquitos
    private Animacion aniMosquitoL; // Mosquitos animados hacia izquierda
    private Animacion aniMosquitoGolpeR; // Anima a los mosquitos golpeados
    private Animacion aniMosquitoGolpeL; // Mosquitos golpeados hacia izquierda
    private Animacion aniArana; // Anima a las aranas
    private Animacion aniAranaGolpe; // Anima a las aranas golpeadas
    private Animacion aniMataMoscaGolpeL; // Anima MataMoscas pegando left hand
    private Animacion aniMataMoscaGolpeR; // Anima Matamoscas pegando right hand
    private Animacion aniMataMoscaReactL; // Anima reaccion de mano izq a golpe
    private Animacion aniMataMoscaReactR; // Anima reaccion de mano der a golpe
    
    // ********************** Sonidos ******************************************
    private SoundClip souTeamLogo; // Sonido al aparecer logo de compania
    private SoundClip souMenu;	//Sonido de fondo en el menu
    private SoundClip souButton;  // Sonido de golpe a enemigos
    private SoundClip souHitTry; // Sonido de intento de golpe matamoscas
    private SoundClip souAplastado; // Sonido de pegar al insecto
    private SoundClip souBossLlega; // Sonido cuando aparecera el boss
    private SoundClip souEndLevel;  // Sonido de pantalla finalizar nivel
    private SoundClip souTouched;   // Sonido cuando te pega un enemigo
    private SoundClip souBoss1; // Sonido del boss del primer nivel
    private SoundClip souThrow; // Aviso de ataque por parte del boss
    private SoundClip souMoscas; // Sonido cuando hay moscas en pantalla
    private SoundClip souEsperando; // Sonido esperando a iniciar el juego
    private SoundClip souPerder; // Sonido de reproduccion en la pantalla perder
    // ********************** GRAFICOS Y URLS **********************************
    private Image imaImagenApplet;   // Imagen a proyectar en Applet
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen
    
    private URL urlImaCursor; // URL de la imagen del cursor
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
    private URL urlImaLives; // URL de la imagen para las vidas
    private URL urlImaAnuncioInicio; // URL de la imagen con instruccion inicial
    private URL urlImaProyectil1; // URL de la imagen del proyectil 1
    private URL urlImaControl[]; // URLs de las imagenes de los controles
    private URL urlImaBanner[]; // URLs de las imagenes del baner
    private URL urlImaBotonA[]; // URLs de las imagenes de los botones
    private URL urlImaBotonB[]; // URLs de las imagenes de los botones sobre
    private URL urlImaBotonC[]; // URLs de las imagenes de los botones presion
    private URL urlImaDialogo[]; // URLs de los dialogos del senor
    private URL urlImaBossN1[]; // URLs de la animacion del boss 1
    private URL urlImaBossH1;   // URLs de la animacion del boss 1 flashazo
    private URL urlImaBossA1[]; // URLs de animacion del boss 1 atacando
    private URL urlImaBossN2[]; // URLs de la animacion del boss 2
    private URL urlImaBossH2; // URLs de la animacion del boss 2 flashazo
    private URL urlImaBossA2[]; // URLs de animacion del boss 2 atacando
    private URL urlImaBossN3[]; // URLs de la animacion del boss 3
    private URL urlImaBossH3; // URLs de la animacion del boss 3 flashazo
    private URL urlImaBossA3[]; // URLs de animacion del boss 3 atacando
    private URL urlHormigaR[];   // URLs de animacion de hormigas
    private URL urlHormigaGolpeR; // URLs de animacion hormiga golpeada
    private URL urlHormigaL[];  // URLs de animacion de hormigas
    private URL urlHormigaGolpeL; // URLs de animacion de hormiga golpeada
    private URL urlMoscaL[];    // URLs de animacion de mosca
    private URL urlMoscaGolpeL; // URLs de animacion mosca golpeada
    private URL urlMoscaR[];    // URLs de animacion de mosca
    private URL urlMoscaGolpeR; // URLs de animacion mosca golpeada
    private URL urlMosquitoR[];   // URLs de animacion mosquito
    private URL urlMosquitoGolpeR; // URLs de animacion mosquito golpeado
    private URL urlMosquitoL[];   // URLs de animacion mosquito
    private URL urlMosquitoGolpeL; // URLs de animacion mosquito golpeado
    private URL urlArana[]; // URLs de animacion de aranas
    private URL urlAranaGolpe;  // URL de golpe a arana
    private URL urlMataMoscaGolpeR[]; // URL para las animaciones del golpe
    private URL urlMataMoscaHit;    // URL de imagen de matamoscas en flashazo
    private URL urlCursor;  // Cursor vacio
    
    // *************************************************************************
    
    /**
     * class Golpe
     * 
     * Clase utilizada para almacenar la ubicasion y las caracteristicas de una
     * mosca golpeada
     * 
     */
    public class Golpe{
        private int iPosX;  // Posicion del golpe en X
        private int iPosY;  // Posicion del golpe en Y
        private int iPasadas;   // Cantidad de pasadas que faltan
        private boolean bDireccion; // La direccion a la que voltea
        
        /**
        * Golpe
        * 
        * Metodo constructor usado para crear el objeto Golpe
        * 
        * @param iPosX es la <code>Posicion en x</code> del objeto.
        * @param iPosY es la <code>Posicion en y</code> del objeto.
        * @param iPasadas es las <code>Pasadas</code> del objeto.
        * @param bDireccion es el <code>Direccion</code> del objeto.
        * 
        */
        public Golpe(int iPosX, int iPosY, int iPasadas, boolean bDireccion){
            this.iPosX = iPosX;
            this.iPosY = iPosY;
            this.iPasadas = iPasadas;
            this.bDireccion = bDireccion;
        }
    }
    
    
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
     * actualizaAchievements
     * 
     * 
     * En este metodo lo que hace es actualizar los logros que se han obtenido
     * por haber cumplido con los requisitos al jugar los distintos modos de
     * juego
     * 
     * @param lMos es el <code>long</code> usado para cantidad de moscas matadas
     * @param lMsc es el <code>long</code> usado para cantidad de mosquitos
     * @param lHor es el <code>long</code> usado para cantidad de hormigas
     * @param lAra es el <code>long</code> usado para cantidad de arana
     * @param iM es el <code>int</code> es el modo de juego
     * @param bLogra es el <code>boolean</code> para verificar si cumplio
     * @param iLvl es el <code>long</code> para identificar el nivel
     * @param bClasicoFull es <code>boolean</code> indica si acabo el clasico
     * 
     */
    public void actualizaAchievements(long lMos, long lMsc, long lHor, 
            long lAra, int iM, boolean bLogra, int iLvl, boolean bClasicoFull) {
        try {
            /*
             * iModo:
             * 1.  Clasico
             * 2.  100 bugs
             * 3.  1 min
             * 4.  5 min
             * 5.  Endless
             * 6.  Creditos
             */
            
            /*
             * 1.   0 - long - mosquitos matadas
             * 2.   8 - long - moscas matados
             * 3.  16 - long - hormigas matadas
             * 4.  24 - long - aranas matadas
             * 5.  25 - bool - nivel 1
             * 6.  26 - bool - nivel 2
             * 7.  27 - bool - nivel 3
             * 8.  35 - long - insectos en modo 1 min
             * 9.  43 - long - insectos en modo 5 min
             * 10. 44 - bool - modo 100
             * 11. 52 - long - insectos modo infinito
             * 12. 53 - bool - creditos
             * 13. 61 - long - insectos totales
             * 14. 62 - bool - modo clasico sin perder vidas
             * 15. 63 - bool - completar el juego
             */
            
            // Guarda las posiciones del archivo
            int iPosicionesBinarias[] = 
                    {0, 8, 16, 24, 25, 26, 27, 35, 43, 44, 52, 53, 61, 62, 63};
            
            //Se accesa al archivo
            RandomAccessFile rafArchivo =new RandomAccessFile("stats.dat","rw");
            // De acuerdo a los valores de las variables, las que sufrieron
            // cambio significa una actualizacion
            if (rafArchivo.length() == 0) {  // Manejo de errores
                rafArchivo.seek(0);
                rafArchivo.writeLong(0);
                rafArchivo.writeLong(0);
                rafArchivo.writeLong(0);
                rafArchivo.writeLong(0);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeLong(0);
                rafArchivo.writeLong(0);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeLong(0);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeLong(0);
                rafArchivo.writeBoolean(false);
                rafArchivo.writeBoolean(false);
            }
            else {
                // Se empieza a actualizar la información del archivo
                rafArchivo.seek(0);
                lMos += rafArchivo.readLong();
                rafArchivo.seek(0);
                rafArchivo.writeLong(lMos);
                
                rafArchivo.seek(iPosicionesBinarias[1]);
                lMsc += rafArchivo.readLong();
                rafArchivo.seek(iPosicionesBinarias[1]);
                rafArchivo.writeLong(lMsc);
                
                rafArchivo.seek(iPosicionesBinarias[2]);
                lHor += rafArchivo.readLong();
                rafArchivo.seek(iPosicionesBinarias[2]);
                rafArchivo.writeLong(lHor);
                
                rafArchivo.seek(iPosicionesBinarias[3]);
                lAra += rafArchivo.readLong();
                rafArchivo.seek(iPosicionesBinarias[3]);
                rafArchivo.writeLong(lAra);
                
                long lT = lMos + lMsc + lHor + lAra; // total de enemigos matado
                long lAux; // Variable auxiliar
                switch (iM) { // El modo de juego que se jugo
                    case 1: // Actualiza el archivo para classic
                        if (bLogra) { 
                            // Actualiza el archivo según el nivel
                            if (iLvl == 1) {
                                rafArchivo.seek(iPosicionesBinarias[4]);
                                rafArchivo.writeBoolean(true);
                            }
                            else if (iLvl == 2) {
                                rafArchivo.seek(iPosicionesBinarias[5]);
                                rafArchivo.writeBoolean(true);
                            }
                            else if (iLvl == 3) {
                                rafArchivo.seek(iPosicionesBinarias[6]);
                                rafArchivo.writeBoolean(true);
                            }
                        }
                        break;
                        
                    case 2: // Actualiza el archivo para 100 bugs
                        if (bLogra) {
                            rafArchivo.seek(iPosicionesBinarias[9]);
                            rafArchivo.writeBoolean(true);
                        }
                        break;
                        
                    case 3: // Actualiza el archivo 1 min
                        rafArchivo.seek(iPosicionesBinarias[7]);
                        lAux = Math.max(rafArchivo.readLong(), lT);
                        rafArchivo.seek(iPosicionesBinarias[7]);
                        rafArchivo.writeLong(lAux);
                        break;
                        
                    case 4: // Actualiza el archivo 5 min
                        rafArchivo.seek(iPosicionesBinarias[8]);
                        lAux = Math.max(rafArchivo.readLong(), lT);
                        rafArchivo.seek(iPosicionesBinarias[8]);
                        rafArchivo.writeLong(lAux);
                        break;
                        
                    case 5: // Actualiza el archivo endless
                        rafArchivo.seek(iPosicionesBinarias[10]);
                        lAux = Math.max(rafArchivo.readLong(), lT);
                        rafArchivo.seek(iPosicionesBinarias[10]);
                        rafArchivo.writeLong(lAux);
                        break;
                    
                    case 6: // Actualiza el archivo creditos
                        if (bLogra) {
                             rafArchivo.seek(iPosicionesBinarias[11]);
                            rafArchivo.writeBoolean(true);
                        }
                        break;
                }
                
                // Bichos totales
                rafArchivo.seek(iPosicionesBinarias[12]);
                long lTotalMaximo = rafArchivo.readLong() + lT;
                rafArchivo.seek(iPosicionesBinarias[12]);
                rafArchivo.writeLong(lTotalMaximo);
                System.out.println(lTotalMaximo);
                
                // Modo clasico completo
                if (bClasicoFull) {
                    rafArchivo.seek(iPosicionesBinarias[13]);
                    rafArchivo.writeBoolean(true);
                }
                
                
                rafArchivo.writeBoolean(actualizaTexto(rafArchivo, 
                        iPosicionesBinarias));
                // Se cierra el archivo
                rafArchivo.close();
            }
            
        } 
        catch (Exception excE) {
            
        }
            
    }
    
    /**
     * actualizaTexto
     * 
     * Metodo que actualiza los texto que conforman cada uno de los logros
     * 
     * @param rafArchivo es el <code>RandomAccessFile</code> archivo a leer
     * @param iPosicionesBinarias es el <code>int</code> que indica posiciones
     * @return bNums es el <code>boolean</code> que indica posiciones
     * @throws IOException
     */
    public boolean actualizaTexto(RandomAccessFile rafArchivo, 
            int iPosicionesBinarias[]) throws IOException {
        rafArchivo.seek(iPosicionesBinarias[0]);
        // Variables para revisar el juego completado
        boolean bNums[] = new boolean[16];
        long l1 = rafArchivo.readLong();
        long l2 = rafArchivo.readLong();
        long l3 = rafArchivo.readLong();
        long l4 = rafArchivo.readLong();
        bNums[5] = rafArchivo.readBoolean();
        bNums[6] = rafArchivo.readBoolean();
        bNums[7] = rafArchivo.readBoolean();
        long l8 = rafArchivo.readLong();
        long l9 = rafArchivo.readLong();
        bNums[10] = rafArchivo.readBoolean();
        long l11 = rafArchivo.readLong();
        bNums[12] = rafArchivo.readBoolean();
        long l13 = rafArchivo.readLong();
        bNums[14] = rafArchivo.readBoolean();
        bNums[15] = rafArchivo.readBoolean();
        
        // Guarda si se cumplen los booleanos
        bNums[0] = (l1 >= 1000);
        bNums[1] = (l2 >= 1000);
        bNums[2] = (l3 >= 1000);
        bNums[3] = (l4 >= 1000);
        bNums[8] = (l8 >= 200);
        bNums[9] = (l9 >= 1200);
        bNums[11] = (l11 >= 150);
        bNums[13] = (l1 >= 50000);
        
        boolean bAnd = true; // boolean usado para revisar un AND
        for (int iI = 1; iI < 15; iI ++) {
            bAnd = bAnd && bNums[iI];
        }
        
        bNums[15] = bNums[15] || bAnd; // OR final
        rafArchivo.seek(iPosicionesBinarias[14]); 
        
        //Abre el archivo y guarda la informacion
        fileOut = new PrintWriter(new FileWriter("logrosObtenidos.txt"));
        for (int iI = 1; iI < 16; iI ++) {
            if (bNums[iI]) {
                fileOut.println("1"); //Se guarda el texto
            }
            else {
                fileOut.println("0"); //Se guarda el texto
            }
        } 
        

        fileOut.close();//Se cierra el doc
        
        return bNums[15];
    }
    
    /**
     * revisaAchievements
     * 
     * Metodo que accede al archivo para identificar logros ya obtenidos
     * 
     */
    public void revisaAchievements() {
        try {
            //Se abre el archivo de texto
            fileIn = new BufferedReader(new FileReader("logrosObtenidos.txt"));
            String strLinea; // String para leer las lineas
            
            int iI = 0; // Iterador
            while(fileIn.ready() && iI < 16) { // Ciclo para leer el archivo
                strLinea = fileIn.readLine();
                StringTokenizer stkTokens = new StringTokenizer(strLinea, "\\");
                
                /* Declaracion de variables auxiliares para la lectura del 
                 * archivo */
                int iA;
                
                // Leo el archivo
                iA = Integer.parseInt(stkTokens.nextToken().trim());
               
                // Se le cambia la imagen al boton
                if (iA == 1) {
                    arrBotBoton[iI + 30].setImagen(
                            Toolkit.getDefaultToolkit().
                            getImage(urlImaBotonB[iI + 30]));
                }
                
                iI ++; // Aumenta el iterador
            }
        }
        catch (IOException | NumberFormatException excE) {
            System.out.println(excE.toString()); // Muestra el error
        }
    }
    
    /**
     * iniciador
     * 
     * Inicializa todas las variables.
     * 
     */
    public void inicializador() {
        iModoDeJuego = 1;
        iCantMaxEnemigos = 1;
        bClickJugador1 = false; // Se dio click
        iTipoJ1 = 1; // Tipo basico
        iVelocidadJ1 = 1; // Velocidad del J1
        iPowerJ1 = 1; // Power del J1.
        iPuntosJ1 = 0; // Puntaje del J1
        iVidasJ1 = 5; // Vidas del jugador
        iNivel = 1; // Nivel del juego
        iBugs = 1; // Cantidad de Bugs
        lTiempoEnPartida = 0; // Tiempo que va en partida
        bBossTime = false; // Hora del boss
        bPausa = false; // Pausa o no
        bClick = false ; // Click
        bIniciaJuego = false; // Si ya inicio el juego
        bPresionadoMouse = false; // Se esta presionando el boton
        bSoltadoMouse = false; // Se solto el boton el mou
        bClickJugador1 = false; // El jugador 1 hizo un click
        bKeyRelEsq = false; // Se solo la tecla ESQ
        bKeyRelEnter = false; // Se solto la tecla Enter
        bTerminoNivel = false; // Se termino el nivel
        bMusicWaiting = false; // No se ha seleccionado matamoscas
        bMusicBoss = false; // la musica del boss
        bMusicMoscas = false; // la musica de moscas
        lTiempoMAtacando = 0; // Controla el tiempo animacion golpe de jugador
        lTiempoBAtacando = 0; // Controla el tiempo de animacion golpe del boss
        lTiempoBossFlash = 0; // Controla el tiempo de animacion de flashazo
        lTiempoMataMoscaFlash = 0; // Controla el tiempo de animacion flashazo
        bBGolpeando = false; // Indica que el Boss esta golpeando
        bMMGolpeando = false; // Indica que el Matamoscas esta golpeando
        bBossAtacado = false; // Indica que se golpeo al boss
        bMataMoscaAtacado = false; // Indica que se toco a la mano
        strUserName = ""; // Inicializa en vacio
    }
    
    
    /**
     * Creador
     * 
     * Crea todos los objetos.
     * 
     */
    public void creador() {
        iPantallaActual = 0; // Pantalla que se encuentra actualmente
        
        // Creo todas las listas
        lklMosquito = new LinkedList();
        lklMosca =  new LinkedList();
        lklHormiga = new LinkedList();
        lklArana = new LinkedList(); 
        
        lklMosquitoGolpeado = new LinkedList();
        lklMoscaGolpeado = new LinkedList();
        lklHormigaGolpeado = new LinkedList();
        lklAranaGolpeado = new LinkedList();
        
        lklProyectil1 = new LinkedList();
        lklProyectil2 = new LinkedList();
        lklProyectil3 = new LinkedList();
        
        /*
        mosBoss;
        horBoss;
        araBoss;
    
    
        mosAux;
        mscAux;
        horAux;
        araAux;
        
        pro1Aux
        pro2Aux
        pro3Aux
        */
        
        
        arrBotBoton = new Boton [100]; // Creo el arreglo de los botones
        
        // Creo todos los URLs
        urlImaManoYMatamoscas = this.getClass().
                getResource("RFrame1.png"); 
        urlImaHormiga = this.getClass().getResource("corazon.png");
        urlImaMosquito = this.getClass().getResource("mosquito.gif");
        urlImaArana = this.getClass().getResource("corazon.png");
        urlImaBoss1 = this.getClass().getResource("boss1.gif");
        urlImaSenor = this.getClass().getResource("senor.png");
        urlImaFondo = this.getClass().getResource("Fondo.png");
        urlImaLogo1 = this.getClass().getResource("logo1.png");
        urlImaLogo2 = this.getClass().getResource("logo2.png");
        urlImaLives = this.getClass().getResource("Life.png");
        urlImaAnuncioInicio = this.getClass().getResource("StartGame.png");
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
        urlImaCursor = this.getClass().getResource("Cursor.png");
        urlImaProyectil1 = this.getClass().getResource("Proyectil1.png");
        
        
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
            urlImaBotonA[iI] = this.getClass().getResource("botonA30.png");
            urlImaBotonB[iI] = this.getClass().
                    getResource("botonB" + iI + ".png");
            urlImaBotonC[iI] = this.getClass().
                    getResource("botonC" + iI + ".png");
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
        
        // ********************************************************************
        // Creo los arreglos de las imagenes de las animaciones ***************
        // ********************************************************************
        // URLs del Boss 1 ---------------------------------------------------
        urlImaBossN1 = new URL [31];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 30; iI ++) {
            urlImaBossN1[iI] = this.getClass().
                    getResource("MayateFrame" + iI + ".png");
        }
        urlImaBossH1 = this.getClass().getResource("MayateInv.png");
        
        urlImaBossA1 = new URL [7];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 6; iI ++) {
            urlImaBossA1[iI] = this.getClass().
                    getResource("MayateHitFrame" + iI + ".png");
        }
        
        // URLs del Boss 2 ---------------------------------------------------
        urlImaBossN2 = new URL [19];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 18; iI ++) {
            urlImaBossN2[iI] = this.getClass().
                    getResource("Frame" + iI + ".png");
        }
        urlImaBossH2 = this.getClass().getResource("MothfeliaInv.png");
        
        urlImaBossA2 = new URL [7];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 6; iI ++) {
            urlImaBossA2[iI] = this.getClass().
                    getResource("FrameBA" + iI + ".png");
        }
        
        // URLs del Boss 3 ---------------------------------------------------
        urlImaBossN3 = new URL [18];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 17; iI ++) {
            urlImaBossN3[iI] = this.getClass().
                    getResource("ArcFrame" + iI + ".png");
        }
        urlImaBossH3 = this.getClass().getResource("ArcnoditeInv.png");
        
        urlImaBossA3 = new URL [10];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 9; iI ++) {
            urlImaBossA3[iI] = this.getClass().
                    getResource("ArcFrameA" + iI + ".png");
        }
        
        // URLs de hormiga derecha -----------------------------------------
        urlHormigaR = new URL [10];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 9; iI ++) {
            urlHormigaR[iI] = this.getClass().
                    getResource("AntIzqFrame" + iI + ".png");
        }
        urlHormigaGolpeR = this.getClass().getResource("AntInvL.png");
        
        // URLs de hormiga izqiuerda ----------------------------------------
        urlHormigaL = new URL [10];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 9; iI ++) {
            urlHormigaL[iI] = this.getClass().
                    getResource("AntrightFrame" + iI + ".png");
        }
        urlHormigaGolpeL = this.getClass().getResource("AntInvR.png");
        
        // URLs de mosca izquierda -----------------------------------------
        urlMoscaL = new URL [7];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 6; iI ++) {
            urlMoscaL[iI] = this.getClass().
                    getResource("FlyrightFrame" + iI + ".png");
        }
        urlMoscaGolpeL = this.getClass().getResource("FlyInvR.png");
        
        // URLs de mosca derecha ----------------------------------------
        urlMoscaR = new URL [7];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 6; iI ++) {
            urlMoscaR[iI] = this.getClass().
                    getResource("FlyIzqFrame" + iI + ".png");
        }
        urlMoscaGolpeR = this.getClass().getResource("FlyInvL.png");
        
        // URLs de mosquita derecha -----------------------------------------
        urlMosquitoR = new URL [9];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 8; iI ++) {
            urlMosquitoR[iI] = this.getClass().
                    getResource("Mosquitoizqframe" + iI + ".png");
        }
        urlMosquitoGolpeR = this.getClass().getResource("MosquitoInvL.png");
        
        // URLs de mosquita izqiuerda ----------------------------------------
        urlMosquitoL = new URL [9];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 8; iI ++) {
            urlMosquitoL[iI] = this.getClass().
                    getResource("Mosquitoirightframe" + iI + ".png");
        }
        urlMosquitoGolpeL = this.getClass().getResource("MosquitoInvR.png");
        
        // URLs de arana ----------------------------------------------------
        urlArana = new URL [11];
        // URLs de imagenes de personajes
        for (int iI = 1; iI <= 10; iI ++) {
            urlArana[iI] = this.getClass().
                    getResource("SpiderFrame" + iI + ".png");
        }
        urlAranaGolpe = this.getClass().getResource("SpiderInv.png");
        
        // URLs de matamoscas golpe-------------------------------------------
        urlMataMoscaGolpeR = new URL [3];
        // URLs de imagenes de matamoscas
        for (int iI = 0; iI <= 2; iI ++) {
            urlMataMoscaGolpeR[iI] = this.getClass().
                    getResource("RFrame" + iI + ".png");
        }
        urlMataMoscaHit = this.getClass().getResource("RFrameInv.png");
        
        // Create a cursor.
        curCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                Toolkit.getDefaultToolkit().getImage(urlImaCursor), 
                new Point(0, 0), "Cursor");
        
        Image imaBlanco = Toolkit.getDefaultToolkit().getImage("corazon.png");
        curCursorBlanco = Toolkit.getDefaultToolkit().
                createCustomCursor(imaBlanco , new Point(0,0), "img");
        /*
        URL urlImaBlanco = this.getClass().getResource("icons/handwriting.gif");
         imaBlanco = Toolkit.getDefaultToolkit().getImage(urlImaBlanco);
        
        curCursorBlanco = Toolkit.getDefaultToolkit().
                createCustomCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                Toolkit.getDefaultToolkit().getImage(UrlImaBlanco) , new Point(0,0), "img");
                * 
                */
        
        /*
            ----------------------------------------------------------------
            --------------Cargar imagenes a las animaciones-----------------
            ----------------------------------------------------------------
        */
        // Creo la animacion del Boss 1-------------------------------------
        aniBoss1 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss1 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 30; iI ++) {
            Image imaBoss1 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossN1[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBoss1.sumaCuadro(imaBoss1, iTIEMPOBOSS);
        }
        
        // Creo la animacion del Boss 1 golpeado----------------------------
        aniBossGolpe1 = new Animacion();
        // Se carga la primera imagen del boss golpeado
        Image imaBossGolpe1 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossN1[1]);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe1.sumaCuadro(imaBossGolpe1, 100);
        // Se carga la segunda imagen del boss golpeado
        imaBossGolpe1 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossH1);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe1.sumaCuadro(imaBossGolpe1, 100);
        
        // Creo la animacion del Boss 1 atacando ----------------------------
        aniBossAttack1 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss1 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 6; iI ++) {
            Image imaBossAttack1 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossA1[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBossAttack1.sumaCuadro(imaBossAttack1, 130);
        }
        
        // Creo la animacion del Boss 2-------------------------------------
        aniBoss2 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss2 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 18; iI ++) {
            Image imaBoss2 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossN2[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBoss2.sumaCuadro(imaBoss2, iTIEMPOBOSS);
        }
        
        // Creo la animacion del Boss 2 golpeado----------------------------
        aniBossGolpe2 = new Animacion();
        // Se carga la primera imagen del boss golpeado
        Image imaBossGolpe2 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossN2[1]);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe2.sumaCuadro(imaBossGolpe2, 100);
        // Se carga la segunda imagen del boss golpeado
        imaBossGolpe2 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossH2);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe2.sumaCuadro(imaBossGolpe2, 100);
        
        // Creo la animacion del Boss 2 atacando ----------------------------
        aniBossAttack2 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss2 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 6; iI ++) {
            Image imaBossAttack2 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossA2[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBossAttack2.sumaCuadro(imaBossAttack2, 130);
        }
      
        // Creo la animacion del Boss 3--------------------------------------
        aniBoss3 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss3 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 17; iI ++) {
            Image imaBoss3 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossN3[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBoss3.sumaCuadro(imaBoss3, iTIEMPOBOSS);
        }
        
        // Creo la animacion del Boss 3 golpeado----------------------------
        aniBossGolpe3 = new Animacion();
        // Se carga la primera imagen del boss golpeado
        Image imaBossGolpe3 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossN3[1]);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe3.sumaCuadro(imaBossGolpe3, 100);
        // Se carga la segunda imagen del boss golpeado
        imaBossGolpe3 = Toolkit.getDefaultToolkit().getImage(
                urlImaBossH3);
        // Agrega la imagen como cuadro a la animacion
        aniBossGolpe3.sumaCuadro(imaBossGolpe3, 100);
        
        // Creo la animacion del Boss 3 atacando ----------------------------
        aniBossAttack3 = new Animacion();
        // Se cargan las imagenes para la animacion del Boss3 y se agregan a la
            // animacion
        for (int iI = 1; iI <= 9; iI ++) {
            Image imaBossAttack3 = Toolkit.getDefaultToolkit().getImage(
                    urlImaBossA3[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniBossAttack3.sumaCuadro(imaBossAttack3, 130);
        }
        
        // Creo la animacion de la hormiga-----------------------------------
        aniHormigaR = new Animacion();
        // Se cargan las imagenes para la animacion de la hormiga y se agregan
            // a la animacion
        for (int iI = 1; iI <= 9; iI ++) {
            Image imaHormigaR = Toolkit.getDefaultToolkit().getImage(
                    urlHormigaR[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniHormigaR.sumaCuadro(imaHormigaR, iTIEMPOHORMIGA);
        }
        
        // Creo la animacion de la hormiga golpeada--------------------------
        aniHormigaGolpeR = new Animacion();
        // Se carga la primera imagen de la hormiga golpeada
        Image imaHormigaGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlHormigaR[1]);
        // Agrega la imagen como cuadro a la animacion
        aniHormigaGolpeR.sumaCuadro(imaHormigaGolpeR, 70);
        // Se carga la segunda imagen de la hormiga golpeada
        imaHormigaGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlHormigaGolpeR);
        // Agrega la imagen como cuadro a la animacion
        aniHormigaGolpeR.sumaCuadro(imaHormigaGolpeR, 70);
        
        // Animacion de la hormiga hacia izquierda---------------------------
        aniHormigaL = new Animacion();
        // Se cargan las imagenes para la animacion de la hormiga y se agregan
            // a la animacion
        for (int iI = 1; iI <= 9; iI ++) {
            Image imaHormigaL = Toolkit.getDefaultToolkit().getImage(
                    urlHormigaL[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniHormigaL.sumaCuadro(imaHormigaL, iTIEMPOHORMIGA);
        }
        
        // Animacion de la hormiga golpeada hacia la izquierda---------------
        aniHormigaGolpeL = new Animacion();
        // Se carga la primera imagen de la hormiga golpeada
        Image imaHormigaGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlHormigaR[1]);
        // Agrega la imagen como cuadro a la animacion
        aniHormigaGolpeL.sumaCuadro(imaHormigaGolpeL, 70);
        // Se carga la segunda imagen de la hormiga golpeada
        imaHormigaGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlHormigaGolpeL);
        // Agrega la imagen como cuadro a la animacion
        aniHormigaGolpeL.sumaCuadro(imaHormigaGolpeL, 70);
        
        // Creo la animacion de la mosca-------------------------------------
        aniMoscaL = new Animacion();
        // Se cargan las imagenes para la animacion de la mosca y se agregan
            // a la animacion
        for (int iI = 1; iI <= 6; iI ++) {
            Image imaMoscaL = Toolkit.getDefaultToolkit().getImage(
                    urlMoscaL[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniMoscaL.sumaCuadro(imaMoscaL, iTIEMPOMOSCA);
        }
        
        // Creo la animacion de la mosca golpeada---------------------------
        aniMoscaGolpeL = new Animacion();
        // Se carga la primera imagen de la mosca golpeada
        Image imaMoscaGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlMoscaL[1]);
        // Agrega la imagen como cuadro a la animacion
        aniMoscaGolpeL.sumaCuadro(imaMoscaGolpeL, iTIEMPOMOSCA);
        // Se carga la segunda imagen de la mosca golpeada
        imaMoscaGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlMoscaGolpeL);
        // Agrega la imagen como cuadro a la animacion
        aniMoscaGolpeL.sumaCuadro(imaMoscaGolpeL, iTIEMPOMOSCA);
        
        // Creo la animacion de la mosca hacia derecha-----------------------
        aniMoscaR = new Animacion();
        // Se cargan las imagenes para la animacion de la mosca y se agregan
            // a la animacion
        for (int iI = 1; iI <= 6; iI ++) {
            Image imaMoscaR = Toolkit.getDefaultToolkit().getImage(
                    urlMoscaR[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniMoscaR.sumaCuadro(imaMoscaR, iTIEMPOMOSCA);
        }
        
        // Creo la animacion de la mosca golpeada a izquierda-----------------
        aniMoscaGolpeR = new Animacion();
        // Se carga la primera imagen de la mosca golpeada
        Image imaMoscaGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlMoscaR[1]);
        // Agrega la imagen como cuadro a la animacion
        aniMoscaGolpeR.sumaCuadro(imaMoscaGolpeR, iTIEMPOMOSCA);
        // Se carga la segunda imagen de la mosca golpeada
        imaMoscaGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlMoscaGolpeR);
        // Agrega la imagen como cuadro a la animacion
        aniMoscaGolpeR.sumaCuadro(imaMoscaGolpeR, iTIEMPOMOSCA);
        
        // Creo la animacion del mosquito-----------------------------------
        aniMosquitoR = new Animacion();
        // Se cargan las imagenes para la animacion del mosquito y se agregan
            // a la animacion
        for (int iI = 1; iI <= 8; iI ++) {
            Image imaMosquitoR = Toolkit.getDefaultToolkit().getImage(
                    urlMosquitoR[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniMosquitoR.sumaCuadro(imaMosquitoR, iTIEMPOMOSCA);
        }
        
        // Creo la animacion de la mosquita golpeada------------------------
        aniMosquitoGolpeR = new Animacion();
        // Se carga la primera imagen de la mosquita golpeada
        Image imaMosquitoGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlMosquitoR[1]);
        // Agrega la imagen como cuadro a la animacion
        aniMosquitoGolpeR.sumaCuadro(imaMosquitoGolpeR, iTIEMPOMOSCA);
        // Se carga la segunda imagen de la mosquita golpeada
        imaMosquitoGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlMosquitoGolpeR);
        // Agrega la imagen como cuadro a la animacion
        aniMosquitoGolpeR.sumaCuadro(imaMosquitoGolpeR, iTIEMPOMOSCA);
        
        // Creo la animacion del mosquito a izquierda -----------------------
        aniMosquitoL = new Animacion();
        // Se cargan las imagenes para la animacion del mosquito y se agregan
            // a la animacion
        for (int iI = 1; iI <= 8; iI ++) {
            Image imaMosquitoL = Toolkit.getDefaultToolkit().getImage(
                    urlMosquitoL[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniMosquitoL.sumaCuadro(imaMosquitoL, iTIEMPOMOSCA);
        }
        
        // Creo la animacion de la mosquita golpeada------------------------
        aniMosquitoGolpeL = new Animacion();
        // Se carga la primera imagen de la mosquita golpeada
        Image imaMosquitoGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlMosquitoL[1]);
        // Agrega la imagen como cuadro a la animacion
        aniMosquitoGolpeL.sumaCuadro(imaMosquitoGolpeL, iTIEMPOMOSCA);
        // Se carga la segunda imagen de la mosquita golpeada
        imaMosquitoGolpeL = Toolkit.getDefaultToolkit().getImage(
                urlMosquitoGolpeL);
        // Agrega la imagen como cuadro a la animacion
        aniMosquitoGolpeL.sumaCuadro(imaMosquitoGolpeL, iTIEMPOMOSCA);
        
        // Creo la animacion de la arana------------------------------------
        aniArana = new Animacion();
        // Se cargan las imagenes para la animacion de la arana y se agregan
            // a la animacion
        for (int iI = 1; iI <= 10; iI ++) {
            Image imaHormigaR = Toolkit.getDefaultToolkit().getImage(
                    urlArana[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniArana.sumaCuadro(imaHormigaR, iTIEMPOARANA);
        }
        
        // Creo la animacion de la arana golpeada--------------------------
        aniAranaGolpe = new Animacion();
        // Se carga la primera imagen de la arana golpeada
        Image imaAranaGolpe = Toolkit.getDefaultToolkit().getImage(
                urlArana[1]);
        // Agrega la imagen como cuadro a la animacion
        aniAranaGolpe.sumaCuadro(imaAranaGolpe, 70);
        // Se carga la segunda imagen de la arana golpeada
        imaAranaGolpe = Toolkit.getDefaultToolkit().getImage(
                urlAranaGolpe);
        // Agrega la imagen como cuadro a la animacion
        aniAranaGolpe.sumaCuadro(imaAranaGolpe, 70);
        
        // Creo la animacion del golpe del matamoscas right hand----------------
        aniMataMoscaGolpeR = new Animacion();
        // Se cargan las imagenes para la animacion del matamoscas y se agregan
            // a la animacion
        for (int iI = 0; iI <= 2; iI ++) {
            Image imaMataMoscaGolpeR = Toolkit.getDefaultToolkit().getImage(
                urlMataMoscaGolpeR[iI]);
            // Agrega la imagen como cuadro a la animacion
            aniMataMoscaGolpeR.sumaCuadro(imaMataMoscaGolpeR, 
                                                            iTIEMPOMATAMOSCAS);
        }
        
        // Creo la animacion de reaccion de mano der al ser tocado-------------
        aniMataMoscaReactR = new Animacion();
        // Se carga la primera imagen de la arana golpeada
        Image imaMataMoscaReactR = Toolkit.getDefaultToolkit().getImage(
                urlMataMoscaGolpeR[1]);
        // Agrega la imagen como cuadro a la animacion
        aniMataMoscaReactR.sumaCuadro(imaMataMoscaReactR, 70);
        // Se carga la segunda imagen de la arana golpeada
        imaMataMoscaReactR = Toolkit.getDefaultToolkit().getImage(
                urlMataMoscaHit);
        // Agrega la imagen como cuadro a la animacion
        aniMataMoscaReactR.sumaCuadro(imaMataMoscaReactR, 70);
        
        
         /*
            ----------------------------------------------------------------
            ----------------------------------------------------------------
            --------------Cargar sonidos y musica de juego------------------
            ----------------------------------------------------------------
            ----------------------------------------------------------------
        */
        souTeamLogo = new SoundClip("finalboss.wav"); // Sonido logo de empresa
        souTeamLogo.play();
        souMenu = new SoundClip("menuGameSong.wav"); // sonido de menu
        souMenu.setLooping(true);
        souBoss1 = new SoundClip("Boss1.wav"); // Sonido de boss 1
        souBoss1.setLooping(true);
        souMoscas = new SoundClip("moscaBuzzing.wav");
        souMoscas.setLooping(true);
        souEsperando = new SoundClip("waiting.wav"); // Esperando a iniciar
        souEsperando.setLooping(true);
        souButton = new SoundClip("boton2.wav"); //Sonido al dar clic sobre menu
        souHitTry = new SoundClip("Matamoscas.wav"); // Sonido de matamoscas
        souAplastado = new SoundClip("Aplastada.wav"); // Golpe a enemigo
        souBossLlega = new SoundClip("incomingBoss.wav"); // Viene el boss
        souEndLevel = new SoundClip("levelFinished.wav"); // Se acabo un nivel
        souTouched = new SoundClip("Ouch.wav"); // Golpeado por enemigo
        souThrow = new SoundClip("bossAttack.wav"); // Boss avisa el ataque
    }
    
    
    /**
     * Limpiador
     * 
     * Limpia las variables y objetos.
     * 
     */
    public void limpiador() {
        // Limbia las listas encadenadas
        lklMosquito.clear();
        lklMosca.clear();
        lklHormiga.clear();
        lklArana.clear();
        
        lklMosquitoGolpeado.clear();
        lklMoscaGolpeado.clear();
        lklHormigaGolpeado.clear();
        lklAranaGolpeado.clear();
        
        lklProyectil1.clear();
        lklProyectil2.clear();
        lklProyectil3.clear();
    }
    
    
    /**
     * llenaBotones
     * 
     * Le da valores a los botones para que puedan funcionar en sus respectivas
     * posiciones
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
    
    /**
     * generadorAranas
     * 
     * Metodo que se encarga de crear enemigos de tipo aranas en cualquier
     * modo de juego
     */
    public void generadorAranas() {
        
    }
  
    /**
     * generadorHormigas
     * 
     * Metodo que se encarga de crear enemigos de tipo hormigas en cualquier
     * modo de juego
     */
    public void generadorHormigas() {
        
    }
    
    /**
     * generadorMoscas
     * 
     * Metodo que se encarga de crear enemigos de tipo Moscas en cualquier
     * modo de juego
     */
    public void generadorMoscas() {
        
    }
    
    /**
     * generadorMosquitos
     * 
     * Metodo que se encarga de crear enemigos de tipo mosquitos en cualquier
     * modo de juego
     */
    public void generadorMosquitos() {
        // Que no haya tantos mosquitos en la pantalla
        if (lklMosquito.size() < iCantMaxEnemigos && 
                iBugs - lklMosquito.size() > 0) {
            int iDonde = (int) (Math.random() * 3.0d + 1.0d);
            
            switch (iDonde) {   // De donde saldran
                case 1: // Arriba
                    //  Creo el mosquito
                    Mosquito mosAux1 = new Mosquito(-99, -99, 40, 40, 1, 0, 3, 
                            1000, iDonde, false, Toolkit.getDefaultToolkit().
                            getImage(urlImaMosquito));
                    
                    // Asigno las ubicasiones
                    mosAux1.setX((int) (Math.random() * (iWIDTH - 
                            mosAux1.getAncho() - 10.0d) + 3.0d));
                    mosAux1.setY(- (int) (Math.random() * 20 + 
                            mosAux1.getAncho()));
                    
                    lklMosquito.add(mosAux1); // Guardo en la lista
                    break;
                case 2: // Izquierda
                    //  Creo el mosquito
                    Mosquito mosAux2 = new Mosquito(-99, -99, 40, 40, 1, 0, 3, 
                            1000, iDonde, false, Toolkit.getDefaultToolkit().
                            getImage(urlImaMosquito));
                    
                    // Asigno las ubicasiones
                    mosAux2.setX(- (int) (Math.random() * 30.0d + 
                            (mosAux2.getAncho() + 5.0d)));
                    mosAux2.setY((int) (Math.random() * (iHEIGHT + 
                            iHEIGHTEXTRA - 40.0d - mosAux2.getAlto()) + 25.0d));
                    
                    lklMosquito.add(mosAux2); // Guardo en la lista
                    break;
                case 3: // Derecha
                    //  Creo el mosquito
                    Mosquito mosAux3 = new Mosquito(-99, -99, 40, 40, 1, 0, 3, 
                            1000, iDonde, false, Toolkit.getDefaultToolkit().
                            getImage(urlImaMosquito));
                    
                    // Asigno las ubicasiones
                    mosAux3.setX((int) (Math.random() * 30.0d + 
                            (mosAux3.getAncho() + iWIDTH)));
                    mosAux3.setY((int) (Math.random() * (iHEIGHT + 
                            iHEIGHTEXTRA - 40.0d - mosAux3.getAlto()) + 25.0d));
                    
                    lklMosquito.add(mosAux3); // Guardo en la lista
                    break;
            }
        }
    }
    
    
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
        
        // Creo al jugador 1
        manJugador1 = new ManoYMatamoscas(iWIDTH / 2, iHEIGHT * 2 / 3, 65, 105, 
                iTipoJ1, iVelocidadJ1, iPowerJ1, 3, 0, 0, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaManoYMatamoscas));
        
        manJugador1.setXY((iWIDTH - manJugador1.getAncho()) / 2, 
                (iHEIGHT + manJugador1.getAlto()) / 2);
        
        // Creo los enemigos auxiliares
        mosAux = new Mosquito(-99, -99, 40, 40, 1, 0, 3, 1000, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaMosquito));
        
        mscAux = new Mosca(-99, -99, 40, 40, 1, 0, 3, 1000, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaMosquito));
        
        horAux = new Hormiga(-99, -99, 40, 40, 1, 0, 3, 1000, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaMosquito));
        
        araAux = new Arana(-99, -99, 40, 40, 1, 0, 3, 1000, 0, false, 
                Toolkit.getDefaultToolkit().getImage(urlImaMosquito));
        
        
        mosBoss = new Mosquito(-99, -90, 280, 200, 100, 0, 3, 1000, 1, 
                    false, Toolkit.getDefaultToolkit().getImage(urlImaBoss1));
        
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
        
        // Calcula el tiepo para las animaciones
            lTiempoActual = System.currentTimeMillis();
        do {
            // Actualiza las posiciones de los objetos en la pantalla
            actualiza(); 
            
            // Revisa si existen coliciones entre objetos en la pantalla
            checaColision();
            
            // Produce los debidos sonidos
            sonidos();
            
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
     *
     */
    public void mueve() {
        // Actualizar el movimiento de los moquitos
        if (!lklMosquito.isEmpty()) {
            int iIterador = 0;
            for (Mosquito mosEnemigo : lklMosquito) {
                mosEnemigo.movimientoMaestro(iWIDTH, iHEIGHT + 
                        iHEIGHTEXTRA);

                // Revisa que este dentro de la pantalla
                if (mosEnemigo.revisaDentroPantalla(iWIDTH, iHEIGHT)) {
                    //lklMosquito.remove(iIterador);
                    //iIterador --;
                }

                iIterador ++;
            }
        }

        // Actualizar el movimiento de las moscas
        if (!lklMosca.isEmpty()) {
            int iIterador = 0;
            for (Mosca mscEnemigo : lklMosca) {
                mscEnemigo.movimientoMaestro(iWIDTH, iHEIGHT + 
                        iHEIGHTEXTRA);

                // Revisa que este dentro de la pantalla
                if (mscEnemigo.revisaDentroPantalla(iWIDTH, iHEIGHT)) {
                    lklMosquito.remove(iIterador);
                    iIterador --;
                }

                iIterador ++;
            }
        }

        // Actualizar el movimiento de las hormigas
        if (!lklHormiga.isEmpty()) {
            int iIterador = 0;
            for (Hormiga horEnemigo : lklHormiga) {
                horEnemigo.movimientoMaestro(iWIDTH, iHEIGHT + 
                        iHEIGHTEXTRA);

                // Revisa que este dentro de la pantalla
                if (horEnemigo.revisaDentroPantalla(iWIDTH, iHEIGHT)) {
                    lklMosquito.remove(iIterador);
                    iIterador --;
                }
                iIterador ++;
            }
        }

        // Actualizar el movimiento de las hormigas
        if (!lklArana.isEmpty()) {
            int iIterador = 0;
            for (Arana araEnemigo : lklArana) {
                araEnemigo.movimientoMaestro(iWIDTH, iHEIGHT + 
                        iHEIGHTEXTRA); 

                // Revisa que este dentro de la pantalla
                if (araEnemigo.revisaDentroPantalla(iWIDTH, iHEIGHT)) {
                    lklMosquito.remove(iIterador);
                    iIterador --;
                }
                iIterador ++;
            }
        }
    }
    
    /**
     * clasic
     * 
     * Metodo que maneja la jugabilidad del sistema en el modo de juego clasico
     * 
     * @param lTiempoTranscurrido es el <code>long</code> que ha transcurrido
     *          todo el juego
     */
    public void clasic(long lTiempoTranscurrido) {
        // Si no se esta en pausa
        if (!bPausa) {
            // Si se esta en el nivel 1
            if (iNivel == 1) {
                // Se genera numero aleatorio para generar uno de los dos 
                // enemigos
                switch ((int) (Math.random() * 2.0d + 1.0d)) {
                    case 1:
                        // Crea un mosquito
                        generadorMosquitos();
                        break;
                    case 2:
                        // Crea una mosca
                        generadorMoscas();
                        break;
                }
            }
            // Si se esta en el nivel 2
            else if (iNivel == 2) {
                // Se genera numero aleatorio para generar uno de los dos 
                // enemigos
                switch ((int) (Math.random() * 3.0d + 1.0d)) {
                    case 1:
                        // Crea un mosquito
                        generadorMosquitos();
                        break;
                    case 2:
                        // Crea un mosca
                        generadorMoscas();
                        break;
                    case 3:
                        // Crea una hormiga
                        generadorHormigas();
                        break;
                }
            }
            // Si se esta en el nivel 3
            else if (iNivel == 3) {
                // Se genera numero aleatorio para generar uno de los dos 
                // enemigos
                switch ((int) (Math.random() * 4.0d + 1.0d)) {
                    case 1:
                        // Crea un mosquito
                        generadorMosquitos();
                        break;
                    case 2:
                        // Crea un mosca
                        generadorMoscas();
                        break;
                    case 3:
                        // Crea una hormiga
                        generadorHormigas();
                        break;
                    case 4:
                        // Crea una arana
                        generadorAranas();
                        break;
                }   
            }
            // Se actualiza posicion de matamoscas
            manJugador1.mueve(iMouseX - manJugador1.getAncho() / 2, iMouseY - 
                    manJugador1.getAlto() / 2, iWIDTH, iHEIGHT + iHEIGHTEXTRA, 
                    false);
            mueve();
            
            
            lTiempoEnPartida += 20;

            // Pelea con el boss
            if (bBossTime) { 
                if (iNivel == 1) {
                    if (mosBoss.getVida() <= 0) {  // Matar al boss
                        bTerminoNivel = true;
                        souBoss1.stop(); // Se deja de reproducir su sonido
                        bMusicBoss = false;
                        souEndLevel.play(); // Se reproduce cambio de nivel
                    }
                    mosBoss.movimientoMaestro(iWIDTH, iHEIGHT + iHEIGHTEXTRA);

                    // Para la animacion del disparo
                    if (mosBoss.iTiempoDeDisparo < 500) {
                        bBGolpeando = true;
                         souThrow.play();
                    }       

                    //  Revisar si esta golpeando
                    if (bBGolpeando) {
                        aniBossAttack1.actualiza(lTiempoTranscurrido);
                        aniBossAttack2.actualiza(lTiempoTranscurrido);
                        aniBossAttack3.actualiza(lTiempoTranscurrido);
                        /* Se lleva el control del tiempo que ha estado 
                            animandose el objeto */
                        lTiempoBAtacando += lTiempoTranscurrido;
                        /* Si el tiempo es mayor a la suma de tiempo de 
                            todos los cuadros   */
                        if (lTiempoBAtacando > 
                                aniBossAttack1.getDuracionTotal()) {
                            // Ya no debe haber animacion
                            bBGolpeando = false;
                            // Se reinicializa animacion
                            aniBossAttack1.iniciar();
                            aniBossAttack2.iniciar();
                            aniBossAttack3.iniciar();
                            lTiempoBAtacando = 0;
                        }
                    }

                    // Para lanzar los proyectiles
                    if (mosBoss.dispara() && lklProyectil1.isEmpty()) {
                        // Crea y lanza 8 proyectiles
                        for (int iJ = 1; iJ < 2; iJ ++) {
                            for (int iI = 1; iI < 8; iI ++) {
                                Proyectil proAux1 = new Proyectil(
                                        mosBoss.getCentroObjX(), 
                                        mosBoss.getCentroObjY(), 30, 60, iI, 
                                        10 * iJ, Toolkit.getDefaultToolkit().
                                        getImage(urlImaProyectil1));
                                lklProyectil1.add(proAux1);
                            }
                        }
                    }
                    
                    // Si se golpeo a boss
                    if (bBossAtacado) {
                        lTiempoBossFlash += lTiempoTranscurrido;
                        // Si el tiempo ya es mayor a la suma total de la
                        // animacion de este estado
                        if (lTiempoBossFlash > 
                                aniBossGolpe1.getDuracionTotal()) {
                            bBossAtacado = false;
                            lTiempoBossFlash = 0;
                        }
                    }

                    // mueve los proyectiles
                    if (! lklProyectil1.isEmpty()) {
                        int iI = 0; // Iterador
                        for (Proyectil proP : lklProyectil1) {
                            proP.movimiento();
                            if (proP.getX() < 0 || proP.getX() > iWIDTH ||
                                    proP.getY() < 0 || proP.getY() > 
                                    iHEIGHT + 40) {
                                lklProyectil1.remove(iI);
                                iI --;
                                break;
                            }
                            iI ++;
                        }
                    }
                } 
            }
            // Para sacar al Boss
            if (iBugs == 0 && ! bBossTime) {
                bBossTime = true; // Inicia la pela con el boss
                if (iNivel == 1) {
                    // Creo al boss y asigno posicion
                    mosBoss.setX((iWIDTH - mosBoss.getAncho()) / 2) ;
                    mosBoss.setY(-500);
                    
                    //Sonidos
                    souMoscas.stop(); // Para el sonido de moscas
                    bMusicMoscas = false;
                    bBossTime = true; // Inicia la pela con el boss
                    bMusicBoss = true;
                    souBossLlega.play(); // Se reproduce el sonido de intro boss
                    souBoss1.play(); // Se reproduce el sonido del boss1
                }
                else if (iNivel == 2) {
                    // Creo al boss y asigno posicion
                    mosBoss.setX((iWIDTH - mosBoss.getAncho()) / 2) ;
                    mosBoss.setY(-500);
                }
                else if (iNivel == 3) {
                    
                }
            }
        }
    }
    
    /**
     * bugs100
     * 
     * Es el metodo encargado de manejar el modo de juego en el que se tiene
     * que matar 100 insectos
     */
    public void bugs100() {
        
    }
    
    /**
     * min1
     * 
     * Es el metodo encargado de manejar el modo de juego en el que se tiene
     * que matar la mayor cantidad de insectos posibles en 1 min
     */
    public void min1() {
        
    }
    
    /**
     * min5
     * 
     * Es el metodo encargado de manejar el modo de juego en el que se tiene
     * que matar la mayor cantidad de insectos posibles en 5 min
     */
    public void min5() {
        
    }   
    
    /**
     * endless
     * 
     * Es el metodo encargado de manejar el modo de juego en el que no se
     * acaba hasta que el usuario haya perdido todas las vidas
     * 
     */
    public void endless() {
        
    }
    
    
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion de los objetos 
     * 
     */
    public void actualiza(){
        // Determina el tiempo que ha transcurrido desde el inicio de ejecucion
        long lTiempoTranscurrido = System.currentTimeMillis() - lTiempoActual;
        // Guardo el tiempo actual
        lTiempoActual += lTiempoTranscurrido;
        //Actualizo las distintas animaciones en el juego en cuanto al tiempo
        aniBoss1.actualiza(lTiempoTranscurrido);
        aniBossGolpe1.actualiza(lTiempoTranscurrido);
        aniBoss2.actualiza(lTiempoTranscurrido);
        aniBossGolpe2.actualiza(lTiempoTranscurrido);
        aniBoss3.actualiza(lTiempoTranscurrido);
        aniBossGolpe3.actualiza(lTiempoTranscurrido);
        aniMoscaL.actualiza(lTiempoTranscurrido);
        aniMoscaR.actualiza(lTiempoTranscurrido);
        aniMoscaGolpeL.actualiza(lTiempoTranscurrido);
        aniMoscaGolpeR.actualiza(lTiempoTranscurrido);
        aniMosquitoR.actualiza(lTiempoTranscurrido);
        aniMosquitoL.actualiza(lTiempoTranscurrido);
        aniMosquitoGolpeR.actualiza(lTiempoTranscurrido);
        aniMosquitoGolpeL.actualiza(lTiempoTranscurrido);
        aniHormigaR.actualiza(lTiempoTranscurrido);
        aniHormigaL.actualiza(lTiempoTranscurrido);
        aniHormigaGolpeR.actualiza(lTiempoTranscurrido);
        aniHormigaGolpeL.actualiza(lTiempoTranscurrido);
        aniArana.actualiza(lTiempoTranscurrido);
        aniAranaGolpe.actualiza(lTiempoTranscurrido);
        aniMataMoscaReactR.actualiza(lTiempoTranscurrido);
        // SI LA VARIABLE ENTERA ATACANDO ES MAYOR A 0 (LANZO OBJETOS)
            // ENTONCES ACTUALIZAR ANIMACION ANIBOSSATTACK1
        //aniBossAttack1.actualiza(lTiempoTranscurrido);
            // SUMAR EL VALOR NUMERO A LA VARIABLE ENTER
            // CHECAR SI LA SUMA ES MAYOR A LA SUMATOTAL DE TIEMPO DE LA ANIMAC
            // SI LLEGA A SER ASI, REINICIALIZAR VARIABLE ENTERA DE BOSS A CERO
            // Y APLICAR LA FUNCION PUBLICA INICIAR A ESTA MISMA ANIMACION
        // SI LA VARIABLE ENTERA ATACANDO ES MAYOR A 0 (ATACANDO CON MATAMOSCAS)
            // ENTONCES ACTUALIZAR ANIMACION ANIMATAMOSCAGOLPEL
        //aniMataMoscaGolpeL.actualiza(lTiempoTranscurrido);
            // HACER LAS MISMAS OPERACIONES CORRESPONDIENTES COMO CON EL ATAQUE
            // DEL BOSS
        
        if (iPantallaActual == 13 && bSoltadoMouse) {
            bMMGolpeando = true;
            souHitTry.play();
        } 
        
        // Si se golpeo a la mano
        if (bMataMoscaAtacado) {
            lTiempoMataMoscaFlash += lTiempoTranscurrido;
            // Si el tiempo ya es mayor a la suma total de la
            // animacion de este estado
            if (lTiempoMataMoscaFlash > 
                    aniMataMoscaReactR.getDuracionTotal() * 3) {
                // Se reinicializan los valores de tiempo y el booleano
                bMataMoscaAtacado = false;
                lTiempoMataMoscaFlash = 0;
            }
        }
        
        // Si el matamoscas esta golpeando
        if (bMMGolpeando) {
            aniMataMoscaGolpeR.actualiza(lTiempoTranscurrido);
            // Se lleva el control del tiempo que ha estado animandose el objeto
            lTiempoMAtacando += lTiempoTranscurrido;
            // Si el tiempo es mayor a la suma de tiempo de todos los cuadros
            if (lTiempoMAtacando > aniMataMoscaGolpeR.getDuracionTotal()) {
                // Ya no debe haber animacion
                bMMGolpeando = false;
                // Se reinicializa animacion
                aniMataMoscaGolpeR.iniciar();
                lTiempoMAtacando = 0;
            }
        }
        
        // Cambio de pantalla inicial
        if (iPantallaActual == 1) {
            iPantallaActual = 2;
            // Si no se ha iniciado la reproduccion
            if (!bMusicMenu) {
                // Se empieza a reproducir la musica
                souMenu.play();
                // Se indica que se esta reproduciendo la musica
                bMusicMenu = true;
            }
        }
        
        // Revisa interaccion de todos los botones para cambiar imagenes
        for(int iI = 0; iI < 66; iI ++) {
            // Achievements
            if (iI > 29 && iI < 45) {
                
            }
            // Si intersecta con la posicion
            else if (arrBotBoton[iI].intersecta(iMouseX, iMouseY)) {
                // Si se esta presionando el mouse
                if (bPresionadoMouse) {
                    arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                            getImage(urlImaBotonB[iI]));
                }
                else if (bSoltadoMouse && iPantallaActual == 
                        arrBotBoton[iI].getPantallaActual()) {
                    // Reproduce sonido al seleccionar botones
                    souButton.play();
                    
                    // Revisa que modo de juego se jugaria
                    if (iPantallaActual == 8) {
                        revisaAchievements();
                    }
                    else if (iPantallaActual == 14) {
                        if (iI == 9) {
                            iModoDeJuego = 1;
                            iCantMaxEnemigos = 25;
                        }
                        else if (iI == 10) {
                            iModoDeJuego = 2;
                            iCantMaxEnemigos = 50;
                        }
                        else if (iI == 11) {
                            iModoDeJuego = 3;
                            iCantMaxEnemigos = 25;
                        }
                        else if (iI == 12) {
                            iModoDeJuego = 4;
                            iCantMaxEnemigos = 25;
                        }
                        else if (iI == 13) {
                            iModoDeJuego = 5;
                            iCantMaxEnemigos = 2;
                        }
                    }
                    else if (iPantallaActual == 15 && iI == 60) {
                        inicializador();
                        limpiador();
                        
                        // Regresa a la posicion
                        manJugador1.setXY((iWIDTH - manJugador1.getAncho()) / 2,
                                (iHEIGHT + manJugador1.getAlto()) / 2);
                    }
                    else if (iPantallaActual == 16 && iI == 62) {
                        bTerminoNivel = false;
                        // Se indica que se reproduce musica de menu
                        bMusicMenu = true; 
                        souMenu.play(); // Se reproduce menu
                    }
                    iPantallaActual = arrBotBoton[iI].getPantallaApunta();
                    bSoltadoMouse = false;
                    //System.out.println(iI);
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
            // Se para la musica que indica que no a iniciado el usuario
            bMusicWaiting = false;
            souEsperando.stop();
            // Se para la musica de las moscas
            bMusicMoscas = false;
            souMoscas.stop();
            // Se para la musica del boss
            souBoss1.stop();
            bMusicBoss = false;
        }
        
        // Cambia a la pantalla de juego
        else if (bKeyRelEsq && iPantallaActual == 14) {
            iPantallaActual = 13;
        }
        
        
        // Reinicializacion
        if (iPantallaActual == 3) {
            manJugador1.setXY((iWIDTH - manJugador1.getAncho()) / 2, 
                (iHEIGHT + manJugador1.getAlto()) / 2); // Regresa a la posicion
            inicializador();
            limpiador();
        }
        
        // Actualizaciones especificas de la pantalla de juego
        if (iPantallaActual == 13) {
            // Si todavia no selecciona el matamoscas
            if (!bIniciaJuego) {
                if (!bMusicWaiting) {
                    bMusicWaiting = true;
                    souEsperando.play();
                }
            
                if (bSoltadoMouse) {
                    bIniciaJuego = manJugador1.intersecta(iMouseX, iMouseY);
                    
                    if (bIniciaJuego) {
                        bMusicWaiting = false;
                        souEsperando.stop();
                        souMoscas.play(); // Se reproduce sonidos de moscas
                    }
                }
            }
            else if (iModoDeJuego == 1) {
                clasic(lTiempoTranscurrido);
            }
            else if (iModoDeJuego == 2) {
                
            }
            else if (iModoDeJuego == 3) {
                
            }
            else if (iModoDeJuego == 4) {
                
            }
            else if (iModoDeJuego == 5) {
                
            }
        }
        
        // En caso de perder
        if (iPantallaActual == 13 && iVidasJ1 <= 0) {
            iPantallaActual = 17;
            //actualizaAchievements();
            // Ya no se reproducen sonidos del Boss
            bMusicBoss = false;
            souBoss1.stop();
            // Se deja de reproducir sonidos de moscas
            bMusicMoscas = false;
            souMoscas.stop();
        }
        
        // Cambia a la pantalla para el siguiente nivel
        if (bTerminoNivel && iNivel < 3) {
            iPantallaActual = 15;
            iNivel ++;
        }
        else if (bTerminoNivel && iNivel == 3) {
            iPantallaActual = 16;
        }
        
        // Reinicializa las variables booleanas
        bClick = false;
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
        int iAyudantePerdidaVidas = manJugador1.colisionEnemigo(lklMosquito);
        while(iAyudantePerdidaVidas != -1){
            // Se quita la vida
            iVidasJ1 --;
            lklMosquito.remove(iAyudantePerdidaVidas);
            iAyudantePerdidaVidas = manJugador1.colisionEnemigo(lklMosquito);
            souTouched.play(); // Reproduce el sonido de golpe recibido
            // Se prende el estado que indica que el jugador fue tocado por
            // insecto
            bMataMoscaAtacado = true;
            lTiempoMataMoscaFlash = 0;
            
            // Si el usuario ya perdio todas las vidas
            if (iVidasJ1 <= 0) {
                // Se interrumpe sonidos de las moscas al perder
                souMoscas.stop();
                bMusicMoscas = false;
                // Se interrumpe sonidos del boss al perder
                souBoss1.stop();
                bMusicBoss = false;
            }
        }
        
        // Revisa si le pego a alguna
        if (bSoltadoMouse) {
            iAyudantePerdidaVidas = manJugador1.golpea(lklMosquito);
            
            // Si le pegas a un mosquito
            while(iAyudantePerdidaVidas != -1){
                souAplastado.play();
                boolean bB;
                if (lklMosquito.get(iAyudantePerdidaVidas).getDireccionX() > 0){
                    bB = true;
                }
                else {
                    bB = false;
                }
                /* Guardas sus valores en otra lkl para mostrar la animacion del
                    mosquito golpeado */
                lklMosquitoGolpeado.add(new Golpe(
                        lklMosquito.get(iAyudantePerdidaVidas).getX(),
                        lklMosquito.get(iAyudantePerdidaVidas).getY(), 30, bB));
                iPuntosJ1 += 200;
                iBugs --;
                lklMosquito.remove(iAyudantePerdidaVidas);
                iAyudantePerdidaVidas = manJugador1.golpea(lklMosquito);
            }
            
            if (bBossTime) { // Inicia el boss
                /* Creo una lkl para revisar si el boss colisiona con la mano
                    mientras la mano golpea */
                LinkedList<Mosquito> lklAux;
                lklAux = new LinkedList();
                lklAux.add(mosBoss);
                int iAuxIterador = manJugador1.golpeaEnemigo(lklAux);
                if (iAuxIterador != -1) { // Si le pega y le quita vida al boss
                    souAplastado.play(); // Se reproduce sonido
                    mosBoss.setVida(mosBoss.getVida() - 1);
                    bBossAtacado = true;
                    lTiempoBossFlash = 0;
                }
            }
        }
        
        
        
        if (bBossTime) {    // Pelea contra el boss
            // Si un proyectil le pega a la mano
            int iAuxIterador = manJugador1.colisionEnemigo(lklProyectil1);
            while (iAuxIterador != -1) { // Pierde vidas y se borra el proyectil
                iVidasJ1 --;
                lklProyectil1.remove(iAuxIterador);
                iAuxIterador = manJugador1.colisionEnemigo(lklProyectil1);
                souTouched.play(); // Reproduce el sonido de golpe recibido
                
                // Se prende el estado que indica que el jugador fue tocado por
                // insecto
                bMataMoscaAtacado = true;
                lTiempoMataMoscaFlash = 0;
                
                // Si la mano pierde todas las vidas, dejan de reproducirse todo
                // sonidos del juego
                if (iVidasJ1 <= 0) {
                    // Se interrumpe sonidos de moscas
                    souMoscas.stop();
                    bMusicMoscas = false;
                    // Se interrumpe sonidos de boss
                    souBoss1.stop();
                    bMusicBoss = false;
                }
            }
        }
    }
    
    
    /**
     * sonidos
     * 
     * Metodo usado para verificar los sonidos y/o musica a reproducir
     * 
     */
    public void sonidos() {
        // Si no se encuentra el juego en el menu
        if (iPantallaActual > 12) {
            if (bMusicMenu) {
                bMusicMenu = false;
                souMenu.stop();
            }
        }
        // Si se encuentra en menu nuevamente
        if (iPantallaActual < 13 && iPantallaActual > 1) {
            // Si la musica no se estaba reproduciendo
            if (!bMusicMenu) {
                souMenu.play();
                bMusicMenu = true;
            }
        }
        // Si se esta en la pantalla de juego
        else if (iPantallaActual == 13) {
            // Si ya inicio el juego
            if (bIniciaJuego) {
                // Si estas en el tiempo del boss
                if (bBossTime) {
                    bMusicMoscas = false;
                    souMoscas.stop();
                    if (!bMusicBoss) {
                        // Se reproduce sonido
                        bMusicBoss = true;
                        souBoss1.play();
                    }
                }
                // Estan los mosquitos
                else {
                    // Si no se ha reproducido sonido
                    if (!bMusicMoscas) {
                        // Se reproduce sonidos d emoscas
                        bMusicMoscas = true;
                        souMoscas.play();
                    }
                }
            }
        }
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
        if (iPantallaActual == 0){
            for (int iI = 0; iI < 50; iI ++) {
                try {
                    paint2(graGrafico);
                }
                catch (Exception excE) {
                    
                }
            }
            iPantallaActual = 1;
        }
        
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
            // Implementado para captar errores
            Logger.getLogger(ProyectoFinal.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        if (iPantallaActual == 13 && bIniciaJuego) {
            // Asigna la nueva imagen al cursor
            getContentPane().setCursor(curCursorBlanco);
        }
        else {
            // Asigna la nueva imagen al cursor
            getContentPane().setCursor(curCursor);
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
                //graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                //        getImage(urlImaBlanco), 0, 0, 1000, 1000, this);
                
                for (int iI = 0; iI < iVidasJ1; iI ++) {
                    graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                            getImage(urlImaLives), 755 - iI * 29, 28, 25, 50, 
                            this);
                }
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("SCORE: \t" + iPuntosJ1, 15, 55);
                
                // Se despliega cantidad de bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t" + iBugs, 15, 80);
                
                // Conversion para el calculo del tiempo
                String strAuxSegundos = (lTiempoEnPartida / 1000 % 60) + "";
                if (strAuxSegundos.length() < 2) {
                    strAuxSegundos = "0" + strAuxSegundos;
                }
                long lAuxiliarMinutos = lTiempoEnPartida / 60000;
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t" + lAuxiliarMinutos + ":" + 
                        strAuxSegundos, 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE: LVL " + iNivel, 15, 520);

                try {
                    // Pinto cada mosquito de la lista
                    if (!lklMosquito.isEmpty()) {
                        for (Mosquito mosEnemigo : lklMosquito) {
                            if (lklMosquito.isEmpty()) {
                                break;
                            }

                            // Si el objeto se mueve a la derecha
                            if (mosEnemigo.getDireccionX() > 0) {
                                graDibujo.drawImage(aniMoscaR.getImagen(),
                                mosEnemigo.getX(), mosEnemigo.getY(), 
                                mosEnemigo.getAncho(), mosEnemigo.getAlto(), 
                                this);
                            }
                            else {
                                graDibujo.drawImage(aniMoscaL.getImagen(),
                                mosEnemigo.getX(), mosEnemigo.getY(), 
                                mosEnemigo.getAncho(), mosEnemigo.getAlto(), 
                                this);
                            }
                        }
                    }
                }
                catch (Exception excE) {
                    // Implementado para captar errores
                }
                
                try {
                    // Pinto mosquitos golpeados de la lista
                    if (!lklMosquitoGolpeado.isEmpty()) {
                        int iI = 0; // Iterador
                        for (Golpe golG : lklMosquitoGolpeado) {
                            // Si el objeto se mueve a la derecha

                            if (golG.bDireccion) {
                                graDibujo.drawImage(aniMoscaGolpeR.getImagen(),
                                golG.iPosX, golG.iPosY, mosAux.getAncho(), 
                                mosAux.getAlto(), this);
                            }
                            else { // Se mueve a la izquierda
                                graDibujo.drawImage(aniMoscaGolpeL.getImagen(),
                                golG.iPosX, golG.iPosY, mosAux.getAncho(), 
                                mosAux.getAlto(), this);
                            }

                            // Quitar la animacion del golpe
                            if (golG.iPasadas == 0) {
                                lklMosquitoGolpeado.remove(iI);
                                break;
                            }
                            else {
                                golG.iPasadas--;
                            }

                            iI ++;
                        }
                    }
                }
                catch (Exception excE) {
                    // Implementado para captar errores
                }
                
                if (bBossTime) {
                    if (bBGolpeando) {
                        //Pintan imagenes de animaciones con tamano de objeto
                        graDibujo.drawImage(aniBossAttack1.getImagen(), 
                                mosBoss.getX() - 5, mosBoss.getY() - 55,
                                mosBoss.getAncho() + 7, mosBoss.getAlto() + 115, 
                                this);
                    }
                    else if (bBossAtacado) {
                        //Pintan imagenes de animaciones con tamano de objeto
                        graDibujo.drawImage(aniBossGolpe1.getImagen(), 
                                mosBoss.getX(), mosBoss.getY(),
                                mosBoss.getAncho(), mosBoss.getAlto(), this);
                    }
                    else {
                        //Se pintan las imagenes de animaciones
                        graDibujo.drawImage(aniBoss1.getImagen(), 
                                mosBoss.getX(), mosBoss.getY(), 
                                mosBoss.getAncho(), mosBoss.getAlto(), this);
                    }
                    
                    // Pintar los proyectiles
                    if (! lklProyectil1.isEmpty()) {
                        for (Proyectil proP : lklProyectil1) {
                            proP.paint(graDibujo, this);
                        }
                    }
                }
                
                // Pinta el anuncio de agarrar el matamoscas
                if (! bIniciaJuego) {
                    graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                            getImage(urlImaAnuncioInicio), 50, 120, 700, 125, 
                            this);
                }
                
                // Si el matamoscas fue tocado por insecto
                if (bMataMoscaAtacado) {
                    graDibujo.drawImage(aniMataMoscaReactR.getImagen(), 
                            manJugador1.getX(), manJugador1.getY(),
                            manJugador1.getAncho(), manJugador1.getAlto(), this);
                }
                // Si el matamoscas esta tratando de golpear
                else if (bMMGolpeando) {
                    graDibujo.drawImage(aniMataMoscaGolpeR.getImagen(), 
                            manJugador1.getX(), manJugador1.getY(),
                            manJugador1.getAncho(), manJugador1.getAlto(), this);
                }
                else {
                    manJugador1.paint(graDibujo, this);
                }
                break;
            case 14: // Pantalla de Pausa
                
                // Se despliegan los puntos
                graDibujo.setColor(Color.black);
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("SCORE: \t" + iPuntosJ1, 15, 55);
                
                // Se despliega los bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t" + iBugs, 15, 80);
                
                // Conversion para el calculo del tiempo
                String strAuxSegundos1 = (lTiempoEnPartida / 1000 % 60) + "";
                if (strAuxSegundos1.length() < 2) {
                    strAuxSegundos1 = "0" + strAuxSegundos1;
                }
                long lAuxiliarMinutos1 = lTiempoEnPartida / 60000;
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t" + lAuxiliarMinutos1 + ":" + 
                        strAuxSegundos1, 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE: LVL " + iNivel, 15, 520);
                
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
                graDibujo.drawString("SCORE: \t" + iPuntosJ1, 15, 55);
                
                // Se despliega los bichos
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("BUGS: \t 130", 15, 80);
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 23));
                graDibujo.drawString("TIME: \t" , 15, 105);
                
                // Se despliega el modo de juego
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 32));
                graDibujo.drawString("CLASIC MODE: LVL " + iNivel, 15, 520);
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
                graDibujo.drawString(iPuntosJ1 + "", 315, 270);
                
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
                graDibujo.drawString(iPuntosJ1 + "", 315, 270);
                
                // Conversion para el calculo del tiempo
                String strAuxSegundos2 = (lTiempoEnPartida / 1000 % 60) + "";
                if (strAuxSegundos2.length() < 2) {
                    strAuxSegundos2 = "0" + strAuxSegundos2;
                }
                long lAuxiliarMinutos2 = lTiempoEnPartida / 60000;
                
                // Se despliega el tiempo
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 35));
                graDibujo.drawString(lAuxiliarMinutos2 + ":" + 
                        strAuxSegundos2, 510, 270);
                
                // Pinta los botones
                for (int iI = 64; iI < 65; iI++) {
                    arrBotBoton[iI].paint(graDibujo, this);
                }
                
                // Se pinta el valor del string de nombre
                graDibujo.setFont(new Font("Garamond", Font.BOLD, 20));
                graDibujo.drawString(strUserName, 254, 374);
                
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
        bSoltadoMouse = false;
    }
    
    /**
     *
     * @param graDibujo
     * @throws InterruptedException
     */
    public void paint2 (Graphics graDibujo) throws InterruptedException {
        
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaLogo1), 1000, 1000, 1, 1, this);
        
        // Press any key
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaPressAnyKey), 1000, 1000, 1, 1, this);
        
        // Logo del juego
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaLogo2), 1000, 1000, 1, 1, this);
        
        // Senor
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaSenor), 1000, 1000, 1, 1, this);

        // Tabla
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaTabla), 1000, 1000, 1, 1, this);

        // Senor
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaSenor), 1000, 1000, 1, 1, this);

            System.out.println("chefe");
        // Creditos
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaCreditos), 1000, 1000, 1, 1, this);
        
        
        for(int iI=0; iI < 10; iI ++) {
            // Banner
            graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                    getImage(urlImaBanner[iI]), 1000, 1000, 1, 1, this);
        }
        
        // Pinta los botones
        for (int iI = 0; iI < 66; iI++) {
            arrBotBoton[iI].paint(graDibujo, this);
            arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                    getImage(urlImaBotonB[iI]));
            arrBotBoton[iI].paint(graDibujo, this);
            arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                    getImage(urlImaBotonC[iI]));
            arrBotBoton[iI].paint(graDibujo, this);
            arrBotBoton[iI].setImagen(Toolkit.getDefaultToolkit().
                    getImage(urlImaBotonA[iI]));
        }

        // Vidas
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaLives), 1000, 1000, 1, 1, this);
        
        // Pinta el proyectil
        pro1Aux.paint(graDibujo, this);
        
        // Moscas
        graDibujo.drawImage(aniMoscaR.getImagen(), 1000, 1000, 1, 1, this);
        graDibujo.drawImage(aniMoscaL.getImagen(),1000, 1000, 1, 1, this);
        graDibujo.drawImage(aniMoscaGolpeR.getImagen(), 1000, 1000, 1, 1, this);
        graDibujo.drawImage(aniMoscaGolpeL.getImagen(), 1000, 1000, 1, 1, this);
        
        graDibujo.drawImage(aniBossAttack1.getImagen(), 1000, 1000, 1, 1, this);
        graDibujo.drawImage(aniBoss1.getImagen(), 1000, 1000, 1, 1, this);
        
        // Pinta el anuncio de agarrar el matamoscas
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                        getImage(urlImaAnuncioInicio),1000, 1000, 1, 1, this);
        
        // Si el matamoscas fue tocado por insecto
        graDibujo.drawImage(aniMataMoscaReactR.getImagen(), 1000, 1000, 1, 
                1, this);
        
        // Si el matamoscas esta tratando de golpear
        graDibujo.drawImage(aniMataMoscaGolpeR.getImagen(), 1000, 1000, 1, 1, 
                this);
        
        // Pinta la mano del jugador 1
        manJugador1.paint(graDibujo, this);
        
        // Cuadro de pausa
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaCuadroPausa), 1000, 1000, 1, 1, this);

        // Cuadro de pasa al siguiente nivel
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                (urlImaCuadroSigNivel), 1000, 1000, 1, 1, this);

        // Cuadro de ganador
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                getImage(urlImaCuadroGanador), 1000, 1000, 1, 1, this);

        // Cuadro de perdedor
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                (urlImaCuadroPerdedor), 1000, 1000, 1, 1, this);
        
        // Cuadro de multijugador
        graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().getImage
                (urlImaCuadroMultiFin), 1000, 1000, 1, 1, this);
        
        // Dialogo
        for (int iI = 0; iI < 6; iI++) {
            graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                    getImage(urlImaDialogo[iI]), 1000, 1000, 1, 1, this);
        }

        // Imagenes de los controles del P1 y P2
        for (int iI = 0; iI < 4; iI++) {
            graGraficaApplet.drawImage(Toolkit.getDefaultToolkit().
                    getImage(urlImaControl[iI]), 1000, 1000, 1, 1, this);
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
        
        
        if (iPantallaActual == 17) {
            // Si teclea el nombre en la pantalla de perdedor o ganador
            if (keyEvent.getKeyCode() != KeyEvent.VK_CAPS_LOCK && 
                    keyEvent.getKeyCode() != KeyEvent.VK_SHIFT) {
                // Si no es una tecla de borrar
                if (keyEvent.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    if (strUserName.length() < 25) {
                        // Se agrega el caracter de la tecla al string sin pasar
                        // 15 de longitud
                        strUserName += keyEvent.getKeyChar();
                    }
                }
                else {
                    // Si es un backspace se borra el ultimo caracter del string
                    if (strUserName.length() > 0) {
                       strUserName = strUserName.substring(0,
                               strUserName.length() - 1); 
                    }
                }
            }
        }
    }
    
    
    /**
     * keyTyped
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una 
     * tecla que no es de accion.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al presionar
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
