package org.academy.jra.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el DTO
 * del perfil del cliente.
 * 
 * @author joel
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ProfileDTO implements Serializable {
	
	private static final long serialVersionUID = -5860577961159641708L;
	
	private String passion;
	private String monthlySalary;
	private String age;
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		
		return passion == null && monthlySalary == null && age == null;
	}
}
