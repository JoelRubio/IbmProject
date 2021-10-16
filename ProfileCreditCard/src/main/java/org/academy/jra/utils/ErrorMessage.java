package org.academy.jra.utils;

/**
 * Constantes para manejar los 
 * mensajes de error en la aplicación.
 * 
 * @author Joel Rubio
 *
 */
public interface ErrorMessage {

	String MIN_AGE    = "La edad mínima es de " + ValidationConstants.MIN_AGE + " años";
	String MAX_AGE    = "La edad máxima es de " + ValidationConstants.MAX_AGE + " años";
	String AGE_DIGITS = "La edad sólo puede tener dígitos"; 
	
	String MIN_MONTHLY_SALARY    = "El salario mensual mínimo es de 15000.00 pesos mexicanos";
	String MAX_MONTHLY_SALARY    = "El salario mensual máximo es de 999999999.99 pesos mexicanos";
	String MONTHLY_SALARY_DIGITS = "El salario mensual sólo puede tener dígitos";
	
	String ILEGAL_PASSION = "No existe ninguna preferencia con el valor proporcionado";
	
	String EMPTY_FIELDS = "No pueden estar vacíos todos los campos";
	
	String ENTITY_NOT_FOUND = "No existe ningún tipo de tarjeta de crédito con los parámetros dados";
}
