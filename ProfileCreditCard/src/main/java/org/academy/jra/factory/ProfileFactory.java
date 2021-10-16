package org.academy.jra.factory;

import org.academy.jra.dto.ProfileDTO;
import org.academy.jra.model.Age;
import org.academy.jra.model.MonthlySalary;
import org.academy.jra.model.Passion;
import org.academy.jra.model.ProfileModel;

/**
 * 
 * @author Joel Rubio
 *
 */
public class ProfileFactory {

	private ProfileFactory() {}
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	public static ProfileModel createProfileModel(ProfileDTO profileDTO) {
		
		return ProfileModel.builder()
			.passion(new Passion(profileDTO.getPassion()))
			.monthlySalary(new MonthlySalary(profileDTO.getMonthlySalary()))
			.age(new Age(profileDTO.getAge()))
			.build();
	}
}
