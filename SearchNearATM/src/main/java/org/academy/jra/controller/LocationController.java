package org.academy.jra.controller;

import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.academy.jra.domain.GPS;
import org.academy.jra.domain.Location;
import org.academy.jra.domain.Place;
import org.academy.jra.domain.PostalCode;
import org.academy.jra.model.BankEntityDTO;
import org.academy.jra.model.LocationDTO;
import org.academy.jra.service.LocationService;
import org.academy.jra.utils.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * Clase que representa el controlador REST
 * para el cajero automático o sucursal.
 * 
 * @author Joel Rubio
 *
 */
@RestController
@RequestMapping("${ws.api.uri}")
public class LocationController {
	
	private LocationService locationService;
	
	/**
	 * Inyección de dependencias por argumentos.
	 * 
	 * @param atmService servicio que contiene la lógica
	 * 			         para la locación de un ATM o sucursal
	 */
	public LocationController(LocationService locationService) {
		
		this.locationService = locationService;
	}
	
	/**
	 * 
	 * @param locationDTO contiene los parámetros para localizar los cajeros automáticos 
	 * 
	 * @return conjunto de ubicaciones de cajeros automáticos de acuerdo a los parámetros dados
	 */
	@ApiOperation(value = "Obtiene los cajeros automáticos más cercanos de acuerdo a las características dadas")
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<BankEntityDTO>> getATMLocations(LocationDTO locationDTO) {
	
		//Verifica que no todos los campos estén vacíos
		if (locationDTO.isEmpty()) {
			
			throw new IllegalArgumentException(ErrorMessage.EMPTY_FIELDS);
		}
		
		
		Location location = new Location(new GPS(locationDTO.getLatitude(), locationDTO.getLongitude()),
										 new PostalCode(locationDTO.getPostalCode()),
										 new Place(locationDTO.getPlace()));
		
		Set<BankEntityDTO> locationResponse = locationService.getBankEntitiesLocations(location);
		
		return ResponseEntity.ok(locationResponse);
	}
}
