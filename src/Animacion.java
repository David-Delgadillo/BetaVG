/**
 * Animacion
 *
 * Modela la definición de todos los objetos de tipo
 * <code>Animacion</code>
 *
 * @author David Delgadillo A01195995
 * @author Carlos Garza     A01195968
 * @author Jose Gonzalez    A01280106
 * 
 * @version 3.2
 * @date 25/ Febrero/ 2015
 */

import java.awt.Image;
import java.util.ArrayList;

/**
	La clase Animacion maneja una serie de imágenes (cuadros)
	y la cantidad de tiempo que se muestra cada cuadro.
*/

public class Animacion{
	
	private ArrayList cuadros;
	private int indiceCuadroActual;
	private long tiempoDeAnimacion;
	private long duracionTotal;
	
	/**
        * Animacion
        * 
        * Metodo constructor usado para crear el objeto animacion
        * utilizando un arreglo de imagenes
        * 
        * 
        */
	public Animacion(){
                // Arreglos que maneja los datos.
		cuadros = new ArrayList();
		duracionTotal = 0;
		iniciar();
	}
	
	/**
		Añade una cuadro a la animación con la duración
		indicada (tiempo que se muestra la imagen).
	*/	
	public synchronized void sumaCuadro(Image imagen, long duracion){
		duracionTotal += duracion;
		cuadros.add(new cuadroDeAnimacion(imagen, duracionTotal));
	}
	
	// Inicializa la animación desde el principio. 
	public synchronized void iniciar(){
		tiempoDeAnimacion = 0;
		indiceCuadroActual = 0;
	}
	
	/**
		Actualiza la imagen (cuadro) actual de la animación,
		si es necesario.
	*/
	public synchronized void actualiza(long tiempoTranscurrido){
		if (cuadros.size() > 1){
			tiempoDeAnimacion += tiempoTranscurrido;
			
			if (tiempoDeAnimacion >= duracionTotal){
				tiempoDeAnimacion = tiempoDeAnimacion % duracionTotal;
				indiceCuadroActual = 0; 
			}
			
			while (tiempoDeAnimacion > getCuadro(indiceCuadroActual).tiempoFinal){
				indiceCuadroActual++;
			}
		}
	}
	
	/**
		Captura la imagen actual de la animación. Regeresa null
		si la animación no tiene imágenes.
	*/
	public synchronized Image getImagen(){
		if (cuadros.size() == 0){
			return null;
		}
		else {
			return getCuadro(indiceCuadroActual).imagen;
		}
	}
	
        /*
            Retorna el cuadro en el que se encuentra en un debido momento
            de la lista del arreglo
        */
	private cuadroDeAnimacion getCuadro(int i){
		return (cuadroDeAnimacion)cuadros.get(i);
	}
	
        // Maneja los tiempos de cada cuadro de la animacion
	public class cuadroDeAnimacion{
		
		Image imagen;
		long tiempoFinal;
		// Metodo constructor
		public cuadroDeAnimacion(){
			this.imagen = null;
			this.tiempoFinal = 0;
		}
		// Metodo sobrecargado de la clase
		public cuadroDeAnimacion(Image imagen, long tiempoFinal){
			this.imagen = imagen;
			this.tiempoFinal = tiempoFinal;
		}
		// Retorna la imagen del cuadro
		public Image getImagen(){
			return imagen;
		}
		// Retorna el tiempo que toma un cuadro
		public long getTiempoFinal(){
			return tiempoFinal;
		}
		// Asigna imagen a un cuadro
		public void setImagen (Image imagen){
			this.imagen = imagen;
		}
		// Asigna el tiempo a un cuadro
		public void setTiempoFinal(long tiempoFinal){
			this.tiempoFinal = tiempoFinal;
		}
	}
		
}