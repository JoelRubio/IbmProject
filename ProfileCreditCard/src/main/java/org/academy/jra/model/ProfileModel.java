package org.academy.jra.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el model
 * del perfil proveniente de la
 * petición HTTP.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
@Builder
public class ProfileModel {

	private Passion passion;
	private MonthlySalary monthlySalary;
	private Age age;
	
	/**
	 * Verifica si el perfil de la persona
	 * está completo.
	 * 
	 * @return true todos los atributos no están vacíos,
	 * 		   false de lo contrario
	 */
	public boolean containsAll() {
		
		return !passion.isEmpty() && !monthlySalary.isEmpty() && !age.isEmpty();
	}
}
