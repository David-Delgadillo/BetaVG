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
 * @version 2.1
 * @date 29/ Abril/ 2015
 */

import java.awt.Image;
import java.util.ArrayList;

/**
* La clase Animacion maneja una serie de imágenes (cuadros)
* y la cantidad de tiempo que se muestra cada cuadro.
*/

public class Animacion{
	// *************************** ATRIBUTOS *******************************
	private ArrayList cuadros;
	private int indiceCuadroActual;
	private long tiempoDeAnimacion;
	private long duracionTotal;
	
        // *************************** CONSTRUCTOR *****************************
	/**
        * Animacion
        * 
        * Metodo constructor usado para crear el objeto animacion
        * utilizando un arreglo de imagenes
        * 
        */
	public Animacion(){
                // Arreglos que maneja los datos.
		cuadros = new ArrayList();
		duracionTotal = 0; // Variable que tiene el total de duracion
		iniciar(); // Inicializa los otros dos atributos
	}
	
	/**
	* Añade una cuadro a la animación con la duración
	* indicada (tiempo que se muestra la imagen).
        * 
        * @param imagen es la <code>Image</code> del objeto. 
        * @param duracion es el <code>long</code> del objeto. 
	*/	
	public synchronized void sumaCuadro(Image imagen, long duracion){
		// Añade tiempo de cuadro a la duracion total de tiempo
                duracionTotal += duracion;
		cuadros.add(new cuadroDeAnimacion(imagen, duracionTotal));
	}
	
	/**
	* Reinicializa la animacion desde cero y el tiempo
        *  
	*/ 
	public synchronized void iniciar(){
                // Se reinicializan estos dos atributos a cero
		tiempoDeAnimacion = 0;
		indiceCuadroActual = 0;
	}
	
	/**
	* Actualiza la imagen (cuadro) actual de la animación,
	* si es necesario.
        * 
        * @param tiempoTranscurrido es la <code>long</code> del objeto. 
	*/
	public synchronized void actualiza(long tiempoTranscurrido){
                // Si hay más de un cuadro de imagen en la animacion
		if (cuadros.size() > 1){
                        // Añadir tiempo transcurrido al control del tiempo
			tiempoDeAnimacion += tiempoTranscurrido;
			// Si el total de tiempo es mayor a la duracion total
			if (tiempoDeAnimacion >= duracionTotal){
                                // se obtiene el calculo modular
				tiempoDeAnimacion = tiempoDeAnimacion % 
                                                                duracionTotal;
				indiceCuadroActual = 0; 
			}
			// Si el tiempo coincide a pintar el siguiente cuadro
			while (tiempoDeAnimacion > 
                                getCuadro(indiceCuadroActual).tiempoFinal){
				// Poner indice de cuadro al siguiente
                                indiceCuadroActual++;
			}
		}
	}
	
	/**
	* Captura la imagen actual de la animación. Regeresa null
	* si la animación no tiene imágenes.
        * 
        * @return getCuadro es la <code>Image</code> del objeto.
	*/
	public synchronized Image getImagen(){
            // Si no hay imagen alguna en la animacion
            if (cuadros.size() == 0){
                        // Se retorna nulo
			return null;
		}
		else {
                        // Se regresa la imagene indicada por indice
			return getCuadro(indiceCuadroActual).imagen;
		}
	}
	
        /**
	* Regresa el tiempo de animacion
	*
        * @return tiempoDeAnimacion es la <code>long</code> del objeto.
        */
	public synchronized long getTiempoDeAnimacion(){
                // Regresa tiempo de animacion
		return tiempoDeAnimacion;
	}
        
        /**
	* Regresa la duracion total de animacion
	* 
        * @return duracionTotal es el <code>long</code> del objeto.
        */
	public synchronized long getDuracionTotal(){
                // Regresa duracion total de animacion
		return duracionTotal;
	}
        
        /**
        * Retorna el cuadro en el que se encuentra en un debido momento
        * de la lista del arreglo
        * 
        * @return duracionTotal es el <code>cuadro</code> del objeto.
        */
	private cuadroDeAnimacion getCuadro(int i){
		return (cuadroDeAnimacion)cuadros.get(i);
	}
	
        /** 
         * Maneja los tiempos de cada cuadro de la animacion
         * 
         */ 
	public class cuadroDeAnimacion{
		// *************************** ATRIBUTOS ***********************
		Image imagen;
		long tiempoFinal;
                
		// Metodo constructor ******************************************
                /**
                * cuadroDeAnimacion
                * 
                * Metodo constructor usado para crear el objeto animacion
                * utilizando un arreglo de imagenes
                * 
                */
		public cuadroDeAnimacion(){
                        // No hay imagen inicialmente
			this.imagen = null;
			this.tiempoFinal = 0;
		}
                
		/**
                * 
                * cuadroDeAnimacion
                * 
                * Metodo sobrecargado de la clase
                * 
                * @param imagen es la <code>Image</code> del objeto.
                * @param tiempoFinal es el <code>long</code> del objeto.
                */
		public cuadroDeAnimacion(Image imagen, long tiempoFinal){
                        // Se asigna imagen recibida en la funcion
			this.imagen = imagen;
			this.tiempoFinal = tiempoFinal;
		}
                
		/**
                * 
                * getImagen
                * 
                * Retorna la imagen del cuadro
                * 
                * @return imagen es la <code>Image</code> del objeto.
                */
		public Image getImagen(){
			return imagen;
		}
                
		/**
                * 
                * getTiempoFinal
                * 
                * Retorna el tiempo que toma un cuadro
                * 
                * @return tiempoFinal es la <code>long</code> del objeto.
                */
		public long getTiempoFinal(){
                        // Regresa el tiempo final
			return tiempoFinal;
		}
                
		/**
                * 
                * setImagen
                * 
                * Asigna imagen a un cuadro
                * 
                * @param tiempoFinal es la <code>long</code> del objeto.
                */
		public void setImagen (Image imagen){
			this.imagen = imagen;
		}
                
		/**
                * 
                * setTiempoFinal
                * 
                * Asigna el tiempo a un cuadro
                * 
                * @param tiempoFinal es la <code>long</code> del objeto.
                */
		public void setTiempoFinal(long tiempoFinal){
			this.tiempoFinal = tiempoFinal;
		}
	}
		
}