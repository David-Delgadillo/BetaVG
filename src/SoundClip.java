/**
 * Clase SoundClip
 * 
 * Modela la definicion de todos los objetos de tipo <code>SoundClip</code>
 * 
 * @author David Delgadillo A01195995
 * @author Carlos Garza     A01195968
 * @author Jose Gonzalez    A01280106
 * 
 * @version 2.2
 * @date 5/ Mayo/ 2015
 */
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.net.URL;

public class SoundClip {
        // *************************** ATRIBUTOS *******************************
	private AudioInputStream sample;
	private Clip clip;
	private boolean looping = false;
	private int repeat = 0;
	private String filename = "";
        
        // *************************** CONSTRUCTOR *****************************
	/**
	 * SoundClip
         * 
         * Metodo constructor usado para la inicializacion de las variables
         * que conforman los atributos de la clase
         * 
	 */
	public SoundClip() {
		try {
			//crea el Buffer de sonido
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) { 
                    // Implementado para captar errores
		}
	}

	/** 
         * SoundClip
         * 
	 * Constructor sobrecargado que manda llamar a load
	 * para cargar el archivo de sonido.
         * 
	 * @param filename es el <code>String</code> del archivo.
	 */
	public SoundClip(String filename) {
		//Llama al constructor default.
		this();
		//Carga el archivo de sonido.
		load(filename);
	}

	/** 
         * getClip
         * 
	 * Metodo de acceso que regresa un objeto de tipo Clip
         * 
	 * @return clip es un <code>objeto Clip</code>.
	 */
	public Clip getClip() { 
		return clip; 
	}

	/** 
         * setLooping
         * 
	 * Metodo modificador usado para modificar si el sonido se repite.
         * 
	 * @param looping es un valor <code>boleano</code> indicando si hay loop
	 */
	public void setLooping(boolean looping) {
		this.looping = looping; 
	}

	/** 
         * getLooping
         * 
	 * Metodo de acceso que regresa un booleano para ver si hay repeticion.
         * 
	 * @return looping  es un valor <code>boleano</code>
	 */
	public boolean getLooping() {
		return looping;
	}

	/** 
         * setRepeat
         * 
	 * Metodo modificador usado para definir el numero de repeticiones.
         * 
	 * @param repeat es un <code>entero</code> que es representa
         *          el numero de repeticiones. 
	 */
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	/** 
         * getRepeat
         * 
	 * Metodo de acceso que regresa el numero de repeticiones.
         * 
	 * @return repeat es un valor <code>entero</code> con el numero de 
         *          repeticiones. 
	 */
	public int getRepeat() { 
		return repeat; 
	}

	/**
         * setFilename
         * 
	 * Metodo modificador que asigna un nombre al archivo.
         * 
	 * @param filename es un <code>String</code> con el nombre del archivo. 
	 */
	public void setFilename(String filename) { 
		this.filename = filename; 
	}

	/** 
         * getFilename
         * 
	 * Metodo de acceso que regresa el nombre del archivo.
         * 
	 * @return filename es un <code>String</code> con el nombre del archivo. 
	 */
	public String getFilename() { 
		return filename;
	}

	/**
         * isLoaded
         * 
	 * Metodo que verifica si el archivo de audio esta cargado.
         * 
	 * @return sample es un <code>objeto sample</code> que castea a booleano
	 */
	public boolean isLoaded() {
		return (boolean)(sample != null);
	}

	/** 
         * getURL
         * 
	 * Metodo de acceso que regresa el url del archivo
         * 
	 * @param filename es un <code>String</code> con el nombre del archivo.
         * @return url es un <code>URL</code> con tiene la direccion y nombre
         *          del archivo
	 */
	private URL getURL(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		}
		catch (Exception e) { 
                    // Para capturar cualquier tipo de error
		}
                // Se retorna nulo si no se encontro en directorio
		return url;
	}

	/** 
         * load
         * 
	 * Metodo que carga el archivo de sonido.
         * 
	 * @param audiofile es un <code>String</code> con el nombre del archivo 
         *          de sonido.
         * @return true/false que son los valores de booleano
	 */
	public boolean load(String audiofile) {
		try {
                        // Se logra obtener audio y se  indica como cargado
			setFilename(audiofile);
			sample = AudioSystem.getAudioInputStream(getURL(filename));
			clip.open(sample);
			return true;
                        
		} // Excepcion con la entrada y salida
                catch (IOException e) {   // Caputuras que no se ha cargado
			return false;
		} // Captura el error en caso de audio no soportable
                catch (UnsupportedAudioFileException e) {
			return false;
		} // Captura el error de este tipo
                catch (LineUnavailableException e) {
			return false;
		}
	}

	/**
         * play
         * 
	 * Metodo que reproduce el sonido del objeto
         * 
	 */
	public void play() {
		//se sale si el sonido no a sido cargado
		if ( !isLoaded() ) 
			return;
		//vuelve a empezar el sound clip
		clip.setFramePosition(0);

		//Reproduce el sonido con repeticion opcional.
		if (looping)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			clip.loop(repeat);
	}

	/**
         * stop
         * 
	 * Metodo que detiene el sonido del objeto
	 */
	public void stop() {
		clip.stop();
	}

}
