package org.academy.jra.service;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.academy.jra.domain.Profile;
import org.academy.jra.model.CreditCardDTO;
import org.academy.jra.model.ProfileDTO;
import org.academy.jra.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Clase que representa la lógica de negocio
 * para el dominio Profile.
 * 
 * @author joel
 *
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	private ProfileRepository profileRepository;
	private ModelMapper modelMapper;
	
	/**
	 * Se realiza inyección de dependencias por argumentos,
	 * ya no es necesario colocar @Autowired si sólo se tiene
	 * un constructor.
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
	 * @param passion        preferencia del cliente
	 * @param monthlySalary  sueldo mensual del cliente
	 * @param age            edad del cliente
	 * @return               tarjeta de crédito de acuerdo a sus parámetros
	 */
	@Cacheable("credit-cards")
	@Override
	public Set<CreditCardDTO> getCreditCardType(ProfileDTO profileDTO) {
		
		Profile profile = profileRepository.findByPassionAndMonthlySalaryAndAge(profileDTO.getPassion().toString(), 
																				profileDTO.getMonthlySalary(), 
																				profileDTO.getAge())
				.orElseThrow(() -> new EntityNotFoundException("No existe ningún tipo de tarjeta de crédito con los parámetros dados"));
		
		Set<CreditCardDTO> creditCardDTOs = modelMapper.map(profile.getCreditCards(), new TypeToken<Set<CreditCardDTO>>() {}.getType());
		
		return creditCardDTOs;
	}	
}
