package org.academy.jra.controller;

import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.academy.jra.dto.CreditCardDTO;
import org.academy.jra.dto.ProfileDTO;
import org.academy.jra.factory.ProfileFactory;
import org.academy.jra.model.ProfileModel;
import org.academy.jra.service.ProfileService;
import org.academy.jra.utils.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Clase que representa un controlador REST,
 * el cual contiene métodos que mapearán los 
 * endpoints del servicio Profile.
 * 
 * @author Joel Rubio
 *
 */
@RestController
@RequestMapping("${ws.api.uri}")
public class ProfileController {
	
	private ProfileService profileService;
	
	/**
	 * Inyección de dependencias por argumentos.
	 * 
	 * @param profileService servicio que contiene la lógica para
	 * 				         encontrar tipos de tarjetas de crédito
	 */
	public ProfileController(ProfileService profileService) {
		
		this.profileService = profileService;
	}
	
	/**
	 * Método que manejára la petición del cliente
	 * para que se le regrese un conjunto de tipos 
	 * de tarjetas de crédito acorde a sus parámetros.
	 * 
	 * 
	 * @param profileDTO parámetros para encontrar los tipos de tarjetas de crédito
	 * 
	 * @return  conjunto de tipos de tarjeta de crédito de acuerdo a los parámetros del cliente
	 */
	@ApiOperation(value = "Obtiene el tipo de tarjeta de crédito de acuerdo a la preferencia,"
			            + "el sueldo mensual, y la edad de la persona.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<CreditCardDTO>> getCreditCardType(ProfileDTO profileDTO) {
		
		//verifica que si todos los campos están vacíos.
		if (profileDTO.isEmpty()) {
			
			throw new IllegalArgumentException(ErrorMessage.EMPTY_FIELDS);
		}
		
		ProfileModel profileModel = ProfileFactory.createProfileModel(profileDTO);
		
		Set<CreditCardDTO> creditCards = profileService.getCreditCardType(profileModel);
		
		return ResponseEntity.ok(creditCards);
	}
}
