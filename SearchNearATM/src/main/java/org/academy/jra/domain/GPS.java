package org.academy.jra.domain;

import java.math.BigDecimal;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;

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
	
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public GPS(final String latitude, final String longitude) {
		
		validateLatitude(latitude);
		validateLongitude(longitude);
	}
	
	
	public boolean isLatitudeEmpty() {
		
		return ValidationConstants.DEFAULT_VALUE.equals(latitude);
	}
	
	public boolean isLongitudeEmpty() {
		
		return ValidationConstants.DEFAULT_VALUE.equals(longitude);
	}
	
	/**
	 * Obtiene la representación en cadena
	 * de la latitud.
	 * 
	 * @return latitud en String
	 */
	public String latitudeToString() {
		
		return latitude.toString();
	}
	
	/**
	 * Obtiene la representación en cadena
	 * de la longitud.
	 * 
	 * @return longitud en String
	 */
	public String longitudeToString() {
		
		return longitude.toString();
	}
	
	
	/**
	 * Verifica si los valores de GPS contiene valores por defecto,
	 * es decir, que no se les asigno un valor. 
	 * 
	 * @return true si contienen valores por defecto,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return latitude.equals(ValidationConstants.DEFAULT_VALUE) && 
			   longitude.equals(ValidationConstants.DEFAULT_VALUE);
	}
	
	/**
	 * Realiza la validación correspondiente para la latitud
	 * del cajero automático o sucursal a buscar.
	 * 
	 * @param latitude latitud del cajero automático o sucursal a buscar
	 */
	private void validateLatitude(final String latitude) {
		
		if (latitude == null) {
			
			this.latitude = ValidationConstants.DEFAULT_VALUE;
			
			return;
		}
		
		validateLatitudeRegex(latitude);
		
		this.latitude = new BigDecimal(latitude);
	}
	
	/**
	 * Realiza la validación correspondiente para la longitud
	 * del cajero automático o sucursal a buscar.
	 * 
	 * @param longitude longitud del cajero automático o sucursal a buscar
	 */
	private void validateLongitude(final String longitude) {
		
		if (longitude == null) {
			
			this.longitude = ValidationConstants.DEFAULT_VALUE;
			
			return;
		}
		
		validateLongitudeRegex(longitude);
		
		this.longitude = new BigDecimal(longitude);
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
