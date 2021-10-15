package org.academy.jra.domain;

import org.academy.jra.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la locación
 * del cajero automático o sucursal
 * a buscar.
 * 
 * @author joel
 *
 */
@Getter
@Setter
public class Location {
	
	private GPS gps;
	private PostalCode postalCode;
	private Place place;
	
	public Location(GPS gps, PostalCode postalCode, Place place) {
		
		this.gps        = gps;
		this.postalCode = postalCode;
		this.place      = place;
	}
	
	/**
	 * Verifica que los campos de esta clase no estén
	 * vacíos.
	 * 
	 * @return true si todos los campos no está vacíos,
	 * 		   false de lo contratrio
	 */
	public boolean containsAll() {
		
		return !gps.getLatitude().equals(ValidationConstants.DEFAULT_VALUE) &&
			   !gps.getLongitude().equals(ValidationConstants.DEFAULT_VALUE) &&
			   !postalCode.isEmpty() &&
			   !place.getName().isEmpty();
	}
}
