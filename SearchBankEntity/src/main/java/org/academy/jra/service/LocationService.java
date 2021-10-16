package org.academy.jra.service;

import java.util.Set;

import org.academy.jra.domain.Location;
import org.academy.jra.model.BankEntityDTO;

/**
 * Interfaz que representa los
 * métodos a implementar por
 * la lógica de negocio Location.
 * 
 * @author Joel Rubio
 *
 */
public interface LocationService {

	Set<BankEntityDTO> getBankEntitiesLocations(Location location);
}
