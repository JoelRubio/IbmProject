package org.academy.jra.service;

import java.util.Set;

import org.academy.jra.dto.CreditCardDTO;
import org.academy.jra.model.ProfileModel;

/**
 * Interfaz que define los métodos para
 * el servicio Profile.
 * 
 * @author Joel Rubio
 *
 */
public interface ProfileService {

	Set<CreditCardDTO> getCreditCardType(ProfileModel profileDTO);
}
