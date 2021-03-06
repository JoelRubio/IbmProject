package org.academy.jra.utils;

import java.math.BigDecimal;

import org.academy.jra.model.Passion;

/**
 * Constantes para realizar la validación
 * de los campos de la aplicación.
 * 
 * @author Joel Rubio
 *
 */
public interface ValidationConstants {

	int MIN_AGE = 18;
	int MAX_AGE = 75;
	
	BigDecimal MIN_MONTHLY_SALARY = new BigDecimal("15000.00");
	BigDecimal MAX_MONTHLY_SALARY = new BigDecimal("999999999.99");
	
	//longitud de la preferencia con más caracteres: My Business
	int MAX_LENGTH_PASSION = Passion.LegalPassion.MY_BUSINESS.toString().length();
	
	int INT_EMPTY_VALUE = 0;
	
	String EMPTY_VALUE = "";
	String UNDERSCORE  = "_";
	String WHITESPACE  = " ";
}
