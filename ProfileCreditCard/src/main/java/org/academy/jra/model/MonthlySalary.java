package org.academy.jra.model;

import java.math.BigDecimal;

import org.academy.jra.utils.ErrorMessage;
import org.academy.jra.utils.ValidationConstants;
import org.apache.commons.lang.math.NumberUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el salario
 * mensual del cliente.
 * 
 * @author joel
 *
 */
@Getter
@Setter
public class MonthlySalary {
	
	private BigDecimal value;
	
	public MonthlySalary(final String monthlySalary) {
		
		validateMonthlySalary(monthlySalary);
	}
	
	/**
	 * Verifica si está vacío el salario mensual
	 * 
	 * @return true si el salario mensual es cero,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return value.equals(BigDecimal.ZERO);
	}
	
	/**
	 * Realiza las validaciones correspondientes
	 * para el salario mensual del cliente.
	 * 
	 * @param monthlySalary
	 */
	private void validateMonthlySalary(final String monthlySalary) {
		
		//si el salario mensual es nulo, entonces estaría
		//vacío el salario mensual con un valor de 0.
		if (monthlySalary == null) {
			
			this.value = BigDecimal.ZERO;
			
			return;
		}
		
		validateMonthlySalaryDigits(monthlySalary);
		
		
		BigDecimal tempMonthlySalary = new BigDecimal(monthlySalary);
		
		
		validateMinMonthlySalary(tempMonthlySalary);
		
		validateMaxMonthlySalary(tempMonthlySalary);
		
		this.value = tempMonthlySalary;
	}
	
	/**
	 * Valida que el salario mensual sólo contenga dígitos, si no,
	 * arroja una excepción.
	 * 
	 * @param monthlySalary salario mensual del cliente
	 */
	private void validateMonthlySalaryDigits(final String monthlySalary) {
		
		String tempMonthlySalary = monthlySalary;
		
		if (tempMonthlySalary.contains(".")) {
			
			tempMonthlySalary = tempMonthlySalary.replace(".", "");
		}
		
		if (!NumberUtils.isDigits(tempMonthlySalary)) {
			
			throw new IllegalArgumentException(ErrorMessage.MONTHLY_SALARY_DIGITS);
		}
	}
	
	/**
	 * Valida que el salario mensual no
	 * sea menor a MIN_MONTHLY_SALARY, si no,
	 * arroja una exepción.
	 * 
	 * @param monthlySalary salario mensual del cliente
	 */
	private void validateMinMonthlySalary(final BigDecimal monthlySalary) {
		
		if (monthlySalary.doubleValue() < ValidationConstants.MIN_MONTHLY_SALARY.doubleValue()) {
			
			throw new IllegalArgumentException(ErrorMessage.MIN_MONTHLY_SALARY);
		}
	}
	
	/**
	 * Valida que el salario mensual no
	 * sea mayor a MAX_MONTHLY_SALARY, si no,
	 * arroja una excepción.
	 * 
	 * @param monthlySalary salario mensual del ciente
	 */
	private void validateMaxMonthlySalary(final BigDecimal monthlySalary) {
		
		if (monthlySalary.doubleValue() > ValidationConstants.MAX_MONTHLY_SALARY.doubleValue()) {
			
			throw new IllegalArgumentException(ErrorMessage.MAX_MONTHLY_SALARY);
		}
	}
}
