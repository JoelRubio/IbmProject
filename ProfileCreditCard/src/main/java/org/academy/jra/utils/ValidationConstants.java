package org.academy.jra.utils;

import java.math.BigDecimal;

/**
 * Constantes para realizar la validación
 * de los campos de la aplicación.
 * 
 * @author joel
 *
 */
public interface ValidationConstants {

	int MIN_AGE = 18;
	int MAX_AGE = 75;
	
	BigDecimal MIN_MONTHLY_SALARY = new BigDecimal("15000.00");
	BigDecimal MAX_MONTHLY_SALARY = new BigDecimal("999999999.99");
	
	String EMPTY_VALUE = "";
	String UNDERSCORE  = "_";
	String WHITESPACE  = " ";
}
