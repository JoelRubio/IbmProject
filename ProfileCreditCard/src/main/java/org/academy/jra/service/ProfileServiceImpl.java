package org.academy.jra.service;

import java.util.HashSet;
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
 * @author Joel Rubio
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
	 * @param profileRepository repositorio del dominio Profile
	 * @param modelMapper       objeto que convierte de un tipo de dato a otro
	 */
	public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
		
		this.profileRepository = profileRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Obtiene los tipos de tarjeta de crédito acorde
	 * a los parámetros del cliente.
	 *  
	 * Además almacena en cache los valores para una 
	 * rápida obtención de ellos en un futuro.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return               tipo de tarjeta de crédito de acuerdo los parámetros del cliente
	 */
	@Cacheable("credit-cards")
	@Override
	public Set<CreditCardDTO> getCreditCardType(ProfileDTO profileDTO) {
		
		//Si el cliente proporcionó todos los parámetros
		if (profileDTO.containsAll()) {
			
			return getAll(profileDTO);
		}
		
		//Si el cliente propocionó la preferencia y el salario mensual
		if (!profileDTO.getPassion().getValue().isEmpty() && 
			!profileDTO.getMonthlySalary().isEmpty()) {
			
			return getByPassionAndMonthlySalary(profileDTO);
		}
		
		
		//Si el cliente propocionó la preferencia y la edad
		if (!profileDTO.getPassion().getValue().isEmpty() && 
			!profileDTO.getAge().isEmpty()) {
				
			return getByPassionAndAge(profileDTO);
		}
		
		//Si el cliente propocionó el salario mensual y la edad
		if (!profileDTO.getMonthlySalary().isEmpty() && 
			!profileDTO.getAge().isEmpty()) {
					
			return getByMonthlySalaryAndAge(profileDTO);
		}
		
		//Si el cliente sólo propocionó la preferencia
		if (!profileDTO.getPassion().getValue().isEmpty()) {
			
			return getByPassion(profileDTO);
		}
		
		//Si el cliente sólo propocionó el salario mensual
		if (!profileDTO.getMonthlySalary().isEmpty()) {
			
			return getByMonthlySalary(profileDTO);
		}
		
		//Si el cliente sólo propocionó la edad
		if (!profileDTO.getAge().isEmpty()) {
			
			return getByAge(profileDTO);			
		}
		
		//si no encontró ningún perfil, regrese un conjunto vacío
		return new HashSet<>();
	}
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo a la preferencia y salario mensual 
	 * del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByPassionAndMonthlySalary(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by passion and monthly salary...");
		
		List<Profile> profiles = profileRepository.findByPassionAndMonthlySalary(profileDTO.getPassion().getValue(),
																				 profileDTO.getMonthlySalary().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo a la preferencia y edad del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByPassionAndAge(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by passion and age...");
		
		List<Profile> profiles = profileRepository.findByPassionAndAge(profileDTO.getPassion().getValue(),
																	   profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo al salario mensual y la edad del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByMonthlySalaryAndAge(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by monthly salary and age...");
		
		List<Profile> profiles = profileRepository.findByMonthlySalaryAndAge(profileDTO.getMonthlySalary().getValue(),
																	         profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo a la preferencia del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByPassion(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by passion...");
		
		List<Profile> profiles = profileRepository.findByPassion(profileDTO.getPassion().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo a la edad del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByAge(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by age...");
		
		List<Profile> profiles = profileRepository.findByAge(profileDTO.getAge().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo al salario mensual del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getByMonthlySalary(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by monthly salary...");
		
		List<Profile> profiles = profileRepository.findByMonthlySalary(profileDTO.getMonthlySalary().getValue());
		
		if (profiles.isEmpty()) {
			
			throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
		}
		
		return getSetObject(profiles);
	}
	
	/**
	 * 
	 * 
	 * @param profiles lista de perfiles
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getSetObject(List<Profile> profiles) {
		
		Set<CreditCard> result = profiles.stream()
				.flatMap(profile -> profile.getCreditCards().stream())
				.collect(Collectors.toSet());
		
		//Convierte el tipo de dato
		Set<CreditCardDTO> creditCardDTOs = modelMapper.map(result, new TypeToken<Set<CreditCardDTO>>() {}.getType());
		
		return creditCardDTOs;
	}
	
	/**
	 * Busca los tipos de tarjeta de crédito
	 * de acuerdo a la preferencia, salario mensual,
	 * y edad del cliente.
	 * 
	 * @param profileDTO perfil del cliente
	 * 
	 * @return conjunto con el tipo de tarjetas de crédito
	 */
	private Set<CreditCardDTO> getAll(ProfileDTO profileDTO) {
		
		log.info("Getting profile's data by passion, monthly salary, and age...");
		
		Profile profile = profileRepository.findByPassionAndMonthlySalaryAndAge(profileDTO.getPassion().getValue(), 
																		        profileDTO.getMonthlySalary().getValue(), 
																		        profileDTO.getAge().getValue())
				.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));
		
		//Convierte el tipo de dato
		Set<CreditCardDTO> creditCardDTOs = modelMapper.map(profile.getCreditCards(), new TypeToken<Set<CreditCardDTO>>() {}.getType());
		
		return creditCardDTOs;
	}
}
