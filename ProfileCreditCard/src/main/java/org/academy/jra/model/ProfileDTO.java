package org.academy.jra.model;

import java.math.BigDecimal;

import org.academy.jra.domain.Passion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {

	private static final BigDecimal MIN_MONTH_SALARY = new BigDecimal(15000);
	private static final int MIN_AGE = 18;
	private static final int MAX_AGE = 75;
	
	private Passion passion;
	private BigDecimal monthlySalary;
	private int age;
	
	public ProfileDTO(String passion, BigDecimal monthlySalary, int age) {
		
		validatePassion(passion);
		validateMonthlySalary(monthlySalary);
		validateAge(age);
		
		this.passion       = Passion.valueOf(passion);
		this.monthlySalary = monthlySalary;
		this.age           = age;
	}
	
	private void validatePassion(String passion) {
		
		if (passion == null) {
			
			throw new IllegalArgumentException("La preferencia no puede estar vacía");
		}
		
		for (Passion enumPassion :Passion.values()) {
			
			if (passion.equalsIgnoreCase(enumPassion.toString())) {
				
				return;
			}
		}
		
		throw new IllegalArgumentException("La preferencia: " + passion + " no se encuentra en el sistema");
	}
	
	private void validateMonthlySalary(BigDecimal monthlySalary) {
		
		if (monthlySalary.doubleValue() < MIN_MONTH_SALARY.doubleValue()) {
			
			throw new IllegalArgumentException("El salario mínimo es de: " + MIN_MONTH_SALARY.toString());
		}
	}
	
	private void validateAge(int age) {
		
		if (age < MIN_AGE) {
			
			throw new IllegalArgumentException("La edad mínima es de: " + MIN_AGE);
		}
	
		if (age > MAX_AGE) {
			
			throw new IllegalArgumentException("La edad máxima es de: " + MAX_AGE);
		}
	}
}
