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

	//Ninguna coordenada real puede tener este valor, 
    //sólo es para tener un valor por defecto si el usuario no ingresó una coordenada.
	BigDecimal DEFAULT_VALUE = BigDecimal.valueOf(1000);
	String EMPTY_VALUE = "";
	
	int PLACE_MAX_LENGTH = 50;
	
	int CP_LENGTH                     = 5;
	String NUMBERS_REGEX              = "\\d{5}";
	String LETTERS_REGEX              = "[\\pL\\pM\\p{Zs}]+";
	String LATITUDE_COORDINATE_REGEX  = "(\\+|-)?(?:90(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,8})?))";
	String LONGITUDE_COORDINATE_REGEX = "(\\+|-)?(?:180(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,8})?))";
	
	int POSITION_FOR_ADDRESS       = 4;
	int POSITION_FOR_LONGITUDE     = 15;
	int POSITION_FOR_LATITUDE      = 16;
	int POSITION_FOR_STATE         = 17;
	int POSITION_FOR_TYPE_OF_PLACE = 19;
}
