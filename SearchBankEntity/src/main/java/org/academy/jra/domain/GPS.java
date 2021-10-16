package org.academy.jra.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa las
 * coordenadas para el GPS.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class GPS {
	
	private Latitude latitude;
	private Longitude longitude;
	
	public GPS(final String latitude, final String longitude) {
		
		this.latitude  = new Latitude(latitude);
		this.longitude = new Longitude(longitude);
	}
	
	/**
	 * Verifica si los valores de GPS contiene valores por defecto,
	 * es decir, que no se les asigno un valor. 
	 * 
	 * @return true si contienen valores por defecto,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return latitude.isEmpty() && longitude.isEmpty();
	}
}
