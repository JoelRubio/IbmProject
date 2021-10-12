package org.academy.jra.service;

import org.academy.jra.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

	private ProfileRepository profileRepository;
	
	public ProfileServiceImpl(ProfileRepository profileRepository) {
		
		this.profileRepository = profileRepository;
	}
	
	
	
}
