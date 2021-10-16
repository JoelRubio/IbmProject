package org.academy.jra.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
	 * @return true si el perfil está completo,
	 * 		   false de lo contrario
	 */
	public boolean containsAll() {
		
		return !passion.isEmpty() && !monthlySalary.isEmpty() && !age.isEmpty();
	}
}
