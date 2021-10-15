package org.academy.jra.utils;

import java.math.BigDecimal;

/**
 * Clase que representa una utilería
 * para el model GPS.
 * 
 * @author joel
 *
 */
public class GPSUtils {	
	
	/**
	 * 
	 * @param input1
	 * @param input2
	 * @return
	 */
	public static boolean isEqualInIntegerPart(final BigDecimal input1, final BigDecimal input2) {
		
		int input1IntegerPart = input1.intValue();
		
		int input2IntegerPart = input2.intValue();
		
		return (input1IntegerPart - input2IntegerPart) == 0;
	}
	
	/**
	 * 
	 * @param input1
	 * @param input2
	 * @return
	 */
	public static boolean areFirst4DecimalsEquals(final BigDecimal input1, final BigDecimal input2) {
		
		String input1DecimalPart = decimalPartToString(input1);
		String input2DecimalPart = decimalPartToString(input2);
		
		if (input1DecimalPart.length() == input2DecimalPart.length()) {
			
			return false;
		}
		
		return compare4Decimals(input1DecimalPart, input2DecimalPart);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private static String decimalPartToString(BigDecimal value) {
		
		final String NEGATIVE_SIGN = "-";
		
		String tempValue = value.remainder(BigDecimal.ONE).toString();
		
		return tempValue.contains(NEGATIVE_SIGN) ? tempValue.substring(3) : tempValue.substring(2);	
	}

	
	private static boolean compare4Decimals(String value1, String value2) {
		
		if (value1.length() < 4 || value2.length() < 4)
			return false;
		
		return getFirst4Decimals(value1) == getFirst4Decimals(value2);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static int getFirst4Decimals(String value) {
		
		return Integer.parseInt(value.substring(0, 4));
	}
}
