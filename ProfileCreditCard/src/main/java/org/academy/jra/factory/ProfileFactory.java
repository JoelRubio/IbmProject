package org.academy.jra.factory;

import org.academy.jra.dto.ProfileDTO;
import org.academy.jra.model.Age;
import org.academy.jra.model.MonthlySalary;
import org.academy.jra.model.Passion;
import org.academy.jra.model.ProfileModel;

/**
 * Clase que representa una fábrica
 * para crear objetos relacionados al
 * perfil del cilente.
 * 
 * @author Joel Rubio
 *
 */
public class ProfileFactory {

	private ProfileFactory() {}
	
	/**
	 * Crea un ProfileModel con base en los datos
	 * del DTO.
	 * 
	 * 
	 * @param profileDTO DTO del perfil del cliente
	 * 
	 * @return model del perfil del cliente
	 */
	public static ProfileModel createProfileModel(ProfileDTO profileDTO) {
		
		return ProfileModel.builder()
			.passion(new Passion(profileDTO.getPassion()))
			.monthlySalary(new MonthlySalary(profileDTO.getMonthlySalary()))
			.age(new Age(profileDTO.getAge()))
			.build();
	}
}
