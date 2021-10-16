package org.academy.jra.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la locación
 * del cajero automático o sucursal
 * a buscar.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Location {
	
	private GPS gps;
	private PostalCode postalCode;
	private Place place;
	private List<Object> listObjects;
	
	public Location(GPS gps, PostalCode postalCode, Place place) {
				
		this.gps        = gps;
		this.postalCode = postalCode;
		this.place      = place;
	
		listObjects = new ArrayList<>();
		
		addObjects();
	}	
	
	/**
	 * 
	 */
	private void addObjects() {
		
		if (!gps.getLatitude().isEmpty()) {
			
			listObjects.add(gps.getLatitude());
		}
		
		if (!gps.getLongitude().isEmpty()) {
			
			listObjects.add(gps.getLongitude());
		}
		
		if (!postalCode.isEmpty()) {
			
			listObjects.add(postalCode);
		}
		
		if (!place.isEmpty()) {
			
			listObjects.add(place);
		}
	}
}
