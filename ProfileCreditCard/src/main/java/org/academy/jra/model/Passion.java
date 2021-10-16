package org.academy.jra.model;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la
 * preferencia de la persona.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Passion {
	
	/**
	 * Enumeración que contiene los valores
	 * correctos para la preferencia de la
	 * persona.
	 * 
	 * @author Joel Rubio
	 *
	 */
	enum LegalPassion {

		SHOPPING,
		TRAVELS,
		HELP,
		MY_BUSINESS,
		SPORTS,
		MY_STYLE;
		
		/**
		 * Verifica si la representación en cadena de la preferencia
		 * del cliente contiene un UNDERSCORE, si lo tiene lo reemplaza por
		 * un WHITESPACE y regresa la representación en cadena, si no, regresa 
		 * sólo la representación en cadena.
		 * 
		 * @param passion enumeración que contiene la preferencia del cliente
		 * 
		 * @return representación en cadena de la preferencia del cliente
		 * 		   sin ningún UNDERSCORE
		 */
		private static String cleanString(LegalPassion passion) {
			
			if (passion.toString().contains(ValidationConstants.UNDERSCORE)) {
				
				return passion.toString().replace(ValidationConstants.UNDERSCORE, ValidationConstants.WHITESPACE);
			}
			
			return passion.toString();
		}
		
		/**
		 * Verifica si existe la preferencia que eligió
		 * la persona para su tipo de tarjeta de crédito.
		 * 
		 * @param passion preferencia de la persona
		 * 
		 * @return true si existe la preferencia de la persona,
		 * 		   false de lo contrario
		 */
		public static boolean existsLegalPassion(String passion) {
			
			for (LegalPassion tempPassion : LegalPassion.values()) {
				
				if (passion.equalsIgnoreCase(cleanString(tempPassion))) {
					
					return true;
				}
			}
			
			return false;
		}
	}
	
	
	private String value;
	
	public Passion(final String passion) {
		
		validatePassion(passion);
	}
	
	/**
	 * Valida si está vacío el valor
	 * de la preferencia del cliente.
	 * 
	 * @return true si está vacío,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.EMPTY_VALUE.equals(value);
	}
	
	/**
	 * Realiza las validaciones correspondientes
	 * para la validación de la preferencia de la
	 * persona.
	 * 
	 * @param passion
	 */
	private void validatePassion(final String passion) {
		
		if (passion == null) {
			
			this.value = ValidationConstants.EMPTY_VALUE;
			
			return;
		}

		validateLength(passion);
		
		String tempPassion = removeUnderscoreIfExists(passion);
		
		validateLegalPassion(tempPassion);
		
		this.value = tempPassion.toLowerCase();
	}
	
	/**
	 * Verifica que la longitud de la preferencia
	 * no sea mayor a MAX_LENGTH_PASSION.
	 * 
	 * @param passion preferencia del cliente
	 */
	public void validateLength(final String passion) {
		
		if (passion.length() > ValidationConstants.MAX_LENGTH_PASSION) {
			
			throw new IllegalArgumentException(ErrorMessage.PASSION_LENGTH);
		}
	}
	
	/**
	 * Verifica si la preferencia del cliente contiene un UNDERSCORE, 
	 * si lo tiene lo reemplaza por un WHITESPACE y regresa la cadena, 
	 * si no, regresa sólo la cadena.
	 * 
	 * 
	 * @param passion preferencia del cliente
	 * 
	 * @return preferencia del cliente sin ningún UNDERSCORE
	 */
	private String removeUnderscoreIfExists(final String passion) {
		
		if (passion.contains(ValidationConstants.UNDERSCORE)) {
			
			return passion.replace(ValidationConstants.UNDERSCORE, ValidationConstants.WHITESPACE);
		}
		
		return passion;
	}
	
	/**
	 * Valida que la preferencia de la persona
	 * exista.
	 * 
	 * @param passion preferncia de la persona
	 */
	private void validateLegalPassion(final String passion) {
		
		if (!LegalPassion.existsLegalPassion(passion)) {
			
			throw new IllegalArgumentException(ErrorMessage.ILEGAL_PASSION);
		}
	}
}

