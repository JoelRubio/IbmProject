package org.academy.jra.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa los valores
 * que ingresa el cliente para obtener
 * un tipo de tarjeta de crédito.
 * 
 * @author joel
 *
 */
@Getter
@Setter
public class ProfileDTO {
	
	private Passion passion;
	private MonthlySalary monthlySalary;
	private Age age;
	
	public ProfileDTO(String passion, String monthlySalary, String age) {
		
		this.passion       = new Passion(passion);
		this.monthlySalary = new MonthlySalary(monthlySalary);
		this.age           = new Age(age);
	}

	/**
	 * Verifica si el perfil contiene todos
	 * sus campos.
	 * 
	 * @return true si está completo el perfil, 
	 * 		   false de lo contrario
	 */
	public boolean containsAll() {
		
		return (!passion.getValue().isEmpty() &&
		        !monthlySalary.isEmpty()      &&
		        !age.isEmpty()
		        );
	}
	
	/**
	 * Verifica si el perfil de la persona
	 * está vacío.
	 * 
	 * @return true si el perfil está vacío,
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return (passion.getValue().isEmpty() &&
		        monthlySalary.isEmpty()      &&
		        age.isEmpty()
		       ) ? true : false;
	}
}
