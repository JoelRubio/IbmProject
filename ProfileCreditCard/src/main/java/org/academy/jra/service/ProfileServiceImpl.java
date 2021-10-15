package org.academy.jra.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.academy.jra.domain.CreditCard;
import org.academy.jra.domain.Profile;
import org.academy.jra.model.CreditCardDTO;
import org.academy.jra.model.ProfileDTO;
import org.academy.jra.repository.ProfileRepository;
import org.academy.jra.utils.ErrorMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que representa la lógica de negocio
 * para el dominio Profile.
 * 
 * @author joel
 *
 */
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {

	
	
	private ProfileRepository profileRepository;
	private ModelMapper modelMapper;
	
	/**
	 * Se realiza la inyección de dependencias por argumentos.
	 * 
	 * @param profileRepository repositorio del model Profile
	 * @param feignClient       el cliente que hará las peticiones a otro servicio web
	 */
	public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
		
		this.profileRepository = profileRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Obtiene el tipo de tarjeta de crédito acorde
	 * a los parámetros del cliente.
	 *  
	 * Además almacena en cache los valores para una 
	 * rápida obtención de ellos en un futuro.
	 * 
	 * @param passion        preferencia del cliente
	 * @param monthlySalary  sueldo mensual del cliente
	 * @param age            edad del cliente
	 * @return               tarjeta de crédito de acuerdo a sus parámetros
	 */
	@Cacheable("credit-cards")
	@Override
	public Set<CreditCardDTO> getCreditCardType(ProfileDTO profileDTO) {

		log.info("getting profile's data...");
		
		if (profileDTO.containsAll()) {
			
			return getAll(profileDTO);
		}
		
		if (!profileDTO.getPassion().getValue().isEmpty() && 
			!profileDTO.getMonthlySalary().isEmpty()) {
			
			return getByPassionAndMonthlySalary(profileDTO);
		}
		
		if (!profileDTO.getPassion().getValue().isEmpty() && 
			!profileDTO.getAge().isEmpty()) {
				
			return getByPassionAndAge(profileDTO);
		}
		
		if (!profileDTO.getMonthlySalary().isEmpty() && 
			!profileDTO.getAge().isEmpty()) {
					
			return getByMonthlySalaryAndAge(profileDTO);
		}
		
		if (!profileDTO.getPassion().getValue().isEmpty()) {
			
			return getByPassion(profileDTO);
		}
		
		if (!profileDTO.getMonthlySalary().isEmpty()) {
			
			return getByMonthlySalary(profileDTO);
		}
		
					
		return getByAge(profileDTO);
	}
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByPassionAndMonthlySalary(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByPassionAndMonthlySalary(profileDTO.getPassion().getValue(),
																				 profileDTO.getMonthlySalary().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByPassionAndAge(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByPassionAndAge(profileDTO.getPassion().getValue(),
																	   profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByMonthlySalaryAndAge(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByMonthlySalaryAndAge(profileDTO.getMonthlySalary().getValue(),
																	         profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByPassion(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByPassion(profileDTO.getPassion().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByAge(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByAge(profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getByMonthlySalary(ProfileDTO profileDTO) {
		
		List<Profile> profiles = profileRepository.findByMonthlySalary(profileDTO.getMonthlySalary().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * 
	 * @param profiles
	 * @return
	 */
	private Set<CreditCardDTO> getSetObject(List<Profile> profiles) {
		
		Set<CreditCard> result = profiles.stream()
				.flatMap(profile -> profile.getCreditCards().stream())
				.collect(Collectors.toSet());
		
		Set<CreditCardDTO> creditCardDTOs = modelMapper.map(result, new TypeToken<Set<CreditCardDTO>>() {}.getType());
		
		return creditCardDTOs;
	}
	
	/**
	 * 
	 * 
	 * @param profileDTO
	 * @return
	 */
	private Set<CreditCardDTO> getAll(ProfileDTO profileDTO) {
		
		Profile profile = profileRepository.findByPassionAndMonthlySalaryAndAge(profileDTO.getPassion().getValue(), 
																		        profileDTO.getMonthlySalary().getValue(), 
																		        profileDTO.getAge().getValue())
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));
		
		log.info("transforming to another data type...");
		
		Set<CreditCardDTO> creditCardDTOs = modelMapper.map(profile.getCreditCards(), new TypeToken<Set<CreditCardDTO>>() {}.getType());
		
		return creditCardDTOs;
	}
}
