package org.academy.jra.model;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;
import org.apache.commons.lang.math.NumberUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el
 * código postal de una dirección.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class PostalCode {
	
	private String number;
	
	public PostalCode(final String number) {
		
		validatePostalCode(number);
	}
	
	/**
	 * Verifica si está vacío el código postal.
	 * 
	 * @return true si está vacío el código postal,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.EMPTY_VALUE.equals(number);
	}
	
	/**
	 * Verifica si el número del código postal
	 * es correcto.
	 * 
	 * @param number número del código postal
	 */
	private void validatePostalCode(final String number) {
		
		if (number == null) {
			
			this.number = ValidationConstants.EMPTY_VALUE;
			
			return;
		}
		
		validatePostalCodeDigits(number);

		validatePostalCodeLength(number);
		
		this.number = number;
	}
	
	/**
	 * Valida que el código postal sólo tenga 5 dígitos, si no,
	 * arroja una excepción.
	 * 
	 * @param number código postal de una dirección
	 */
	private void validatePostalCodeLength(final String number) {
		
		if (number.length() != ValidationConstants.CP_LENGTH) {
			
			throw new IllegalArgumentException(ErrorMessage.CP_LENGTH);
		}
	}
	
	/**
	 * Valida que el código postal sólo contenga dígitos, si no,
	 * arroja una excepción.
	 * 
	 * @param number código postal de una dirección
	 */
	private void validatePostalCodeDigits(final String number) {
		
		if (!NumberUtils.isDigits(number)) {
			
			throw new IllegalArgumentException(ErrorMessage.CP_DIGITS);
		}
	}
}
