package org.academy.jra.utils;

/**
 * Constantes para manejar los 
 * mensajes de error en la aplicación.
 * 
 * @author joel
 *
 */
public interface ErrorMessage {

	String LATITUDE = "La latitud sólo puede tener números positivos o negativos entre -90.00000000 y 90.00000000";
	String LONGITUDE = "La longitud sólo puede tener números positivos o negativos entre -180.00000000 y 180.00000000";	
	
	String LETTERS_REGEX = "El lugar sólo puede tener valores alfabéticos";
	String CP_LENGTH = "La longitud del código postal es de " + ValidationConstants.CP_LENGTH + " dígitos";
	String CP_DIGITS = "El código postal sólo puede tener dígitos";
	
	String PLACE_LENGTH = "La longitud máxima de caracteres es de " + ValidationConstants.PLACE_MAX_LENGTH;
	
	String ENTITIES_NOT_FOUND = "No se encontró ningún establecimiento bancario";
	
	String EMPTY_FIELDS = "Ningún campo puede estar vacío";
}
