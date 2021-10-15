package org.academy.jra.service;

import java.util.Set;

import org.academy.jra.domain.Location;
import org.academy.jra.model.BankEntityDTO;

public interface LocationService {

	Set<BankEntityDTO> getBankEntitiesLocations(Location location);
}
