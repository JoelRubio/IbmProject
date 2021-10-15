package org.academy.jra.model;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;
import org.apache.commons.lang.math.NumberUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la edad
 * del cliente. 
 * 
 * @author joel
 *
 */
@Getter
@Setter
public class Age {

	
	
	
	private int value;
	
	public Age(final String age) {
	
		validateStringAge(age);
	}
	
	/**
	 * Verifica si la edad está vacía.
	 * 
	 * @return true si la edad es cero,
	 *         false de lo contrario
	 */
	public boolean isEmpty() {
		
		return value == 0;
	}
	
	/**
	 * Realiza las validaciones correspondientes
	 * para la edad con un tipo de dato String.
	 * 
	 * @param age
	 */
	private void validateStringAge(final String age) {
		
		//si es nulo significa que no ingresó 
		//ningún valor para la edad y puede
		//estar en 0
		if (age == null) {
			
			this.value = 0;
			
			return;
		}
		
		validateAgeDigits(age);

		int tempAge = Integer.parseInt(age);
		
		validateMinAge(tempAge);
		
		validateMaxAge(tempAge);
		
		this.value = tempAge;
	}
	
	
	/**
	 * Valida que la edad del cliente sólo contenga
	 * dígitos.
	 * 
	 * @param age edad del cliente
	 */
	private void validateAgeDigits(final String age) {
		
		if (!NumberUtils.isDigits(age)) {
			
			throw new IllegalArgumentException(ErrorMessage.AGE_DIGITS);
		}
	}
	
	/**
	 * Valida que la edad no sea menor a MIN_AGE,
	 * si no, arroja una excepción.
	 * 
	 * @param age edad de del cliente
	 */
	private void validateMinAge(final int age) {
		
		if (age < ValidationConstants.MIN_AGE) {
			
			throw new IllegalArgumentException(ErrorMessage.MIN_AGE);
		}
	}
	
	/**
	 * Valida que la edad no sea mayor a MAX_AGE,
	 * si no, arroja una excepción.
	 * 
	 * @param age edad del cliente
	 */
	private void validateMaxAge(final int age) {
		
		if (age > ValidationConstants.MAX_AGE) {
			
			throw new IllegalArgumentException(ErrorMessage.MAX_AGE);
		}
	}
}
