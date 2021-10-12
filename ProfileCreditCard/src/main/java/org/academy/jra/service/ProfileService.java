package org.academy.jra.service;

import java.util.Set;

import org.academy.jra.model.CreditCardDTO;
import org.academy.jra.model.ProfileDTO;

/**
 * Interfaz que define los métodos para
 * el servicio Profile.
 * 
 * @author joel
 *
 */
public interface ProfileService {

	Set<CreditCardDTO> getCreditCardType(ProfileDTO profileDTO);
}
