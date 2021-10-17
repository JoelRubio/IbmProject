package org.academy.jra.model;

import java.math.BigDecimal;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la
 * longitud del GPS.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Longitude {

	private BigDecimal value;
	
	public Longitude(String value) {
		
		validateLongitude(value);
	}
	
	
	/**
	 * Verifica si la longitud está vacía.
	 * 
	 * @return true si la longitud está vacía (DEFAULT_VALUE),
	 * 			    false de lo contrario
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.DEFAULT_VALUE.equals(value);
	}
	
	
	/**
	 * Obtiene la representación en cadena
	 * de la longitud.
	 * 
	 * @return longitud en String
	 */
	@Override
	public String toString() {
		
		return value.toString();
	}
	
	/**
	 * Realiza la validación correspondiente para la longitud
	 * del cajero automático o sucursal a buscar.
	 * 
	 * @param longitude longitud del cajero automático o sucursal a buscar
	 */
	private void validateLongitude(final String longitude) {
		
		if (longitude == null) {
			
			this.value = ValidationConstants.DEFAULT_VALUE;
			
			return;
		}
		
		validateLongitudeRegex(longitude);
		
		this.value = new BigDecimal(longitude);
	}
	
	/**
	 * Valida que la longitud sea correcta, de lo contrario,
	 * arroja una excepción.
	 * 
	 * @param longitude longitude del cajero automático o sucursal a buscar
	 */
	private void validateLongitudeRegex(final String longitude) {
		
		if (!longitude.matches(ValidationConstants.LONGITUDE_COORDINATE_REGEX)) {
			
			throw new IllegalArgumentException(ErrorMessage.LONGITUDE);
		}
	}
}
