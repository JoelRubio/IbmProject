package org.academy.jra;

import org.academy.jra.dto.LocationDTO;
import org.academy.jra.model.GPS;
import org.academy.jra.model.LocationModel;
import org.academy.jra.model.Place;
import org.academy.jra.model.PostalCode;

/**
 * 
 * @author Joel Rubio
 *
 */
public class LocationFactory {

	private LocationFactory() {}
	
	/**
	 * 
	 * @param locationDTO
	 * @return
	 */
	public static LocationModel createLocationModel(LocationDTO locationDTO) {
		
		return new LocationModel(new GPS(locationDTO.getLatitude(), locationDTO.getLongitude()),
						  new PostalCode(locationDTO.getPostalCode()), new Place(locationDTO.getPlace()));
	}
}
