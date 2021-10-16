package org.academy.jra.domain;

import java.math.BigDecimal;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la
 * latitud del GPS.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Latitude {

	private BigDecimal value;
	
	public Latitude(String value) {
		
		validateLatitude(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.DEFAULT_VALUE.equals(value);
	}
	
	/**
	 * Obtiene la representación en cadena
	 * de la latitud.
	 * 
	 * @return latitud en String
	 */
	@Override
	public String toString() {
		
		return value.toString();
	}
	
	/**
	 * Realiza la validación correspondiente para la latitud
	 * del cajero automático o sucursal a buscar.
	 * 
	 * @param latitude latitud del cajero automático o sucursal a buscar
	 */
	private void validateLatitude(final String latitude) {
		
		if (latitude == null) {
			
			this.value = ValidationConstants.DEFAULT_VALUE;
			
			return;
		}
		
		validateLatitudeRegex(latitude);
		
		this.value = new BigDecimal(latitude);
	}
	
	
	/**
	 * Valida que la latitud sea correcta, de lo contrarios,
	 * arroja una expción.
	 * 
	 * @param latitude latitude del cajero automático o sucursal a buscar
	 */
	private void validateLatitudeRegex(final String latitude) {
		
		if (!latitude.matches(ValidationConstants.LATITUDE_COORDINATE_REGEX)) {
			
			throw new IllegalArgumentException(ErrorMessage.LATITUDE);
		}
	}
}
