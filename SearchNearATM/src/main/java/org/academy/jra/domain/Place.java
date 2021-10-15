package org.academy.jra.domain;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el lugar
 * a buscar del cajero automático
 * o sucursal.
 * 
 * @author joel
 *
 */
@Getter
@Setter
public class Place {
	
	private String name;
	
	public Place(final String name) {
		
		validatePlace(name);
	}
	
	/**
	 * Verifica si el lugar está vacío.
	 *
	 * @return true si el lugar está vacío,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.EMPTY_VALUE.equals(name);
	}
	
	/**
	 * Realiza las validaciones correspondientes
	 * para el lugar a buscar del cajero automático 
	 * o sucursal.
	 * 
	 * @param name nombre del lugar a buscar del cajero automático o sucursal
	 */
	private void validatePlace(final String name) {

		if (name == null) {
			
			this.name = ValidationConstants.EMPTY_VALUE;

			return;
		}
		
		validatePlaceRegex(name);
		
		this.name = name;
	}
	
	/**
	 * Valida que el lugar sólo contenga
	 * caracteres, si no, arroja una excepción.
	 * 
	 * @param place nombre del lugar a buscar del cajero automático o sucursal
	 */
	private void validatePlaceRegex(final String name) {
		
		if (!name.matches(ValidationConstants.LETTERS_REGEX)) {
			
			throw new IllegalArgumentException(ErrorMessage.LETTERS_REGEX);
		}
	}
}
