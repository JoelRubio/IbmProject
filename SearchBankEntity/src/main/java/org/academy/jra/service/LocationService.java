package org.academy.jra.service;

import java.util.Set;

import org.academy.jra.dto.BankEntityDTO;
import org.academy.jra.model.LocationModel;

/**
 * Interfaz que representa los
 * métodos a implementar por
 * la lógica de negocio Location.
 * 
 * @author Joel Rubio
 *
 */
public interface LocationService {

	Set<BankEntityDTO> getBankEntitiesLocations(LocationModel location);
}
