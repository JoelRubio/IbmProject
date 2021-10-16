package org.academy.jra.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un DTO
 * de la locación del cajero automático
 * o sucursal a buscar.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class LocationDTO implements Serializable {

	private static final long serialVersionUID = 4729459867929804569L;
	
	private String latitude;
	private String longitude;
	private String postalCode;
	private String place;
	
	/**
	 * Verifica si la locación está vacía.
	 * 
	 * @return true si la locación está vacía,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return latitude == null && longitude == null && postalCode == null && place == null;
	}
}
