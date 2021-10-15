package org.academy.jra.controller;

import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.academy.jra.model.CreditCardDTO;
import org.academy.jra.model.ProfileDTO;
import org.academy.jra.service.ProfileService;
import org.academy.jra.utils.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


/**
 * Clase que representa un controlador REST
 * el cual contiene métodos que mapearán los 
 * endpoints del servicio Profile.
 * 
 * @author joel
 *
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	private ProfileService profileService;
	
	/**
	 * Se realiza la inyección de dependencias por argumentos.
	 * 
	 * @param profileService
	 */
	public ProfileController(ProfileService profileService) {
		
		this.profileService = profileService;
	}
	
	
	/**
	 * Método que manejára la petición del cliente
	 * para que se le regrese un tipo de tarjeta de 
	 * crédito acorde a sus parámetros.
	 * 
	 * 
	 * @param passion       preferencia del cliente
	 * @param monthlySalary salario mensual del ciente
	 * @param age           edad del cliente 
	 * @return              tarjeta de crédito de acuerdo a sus parámetros
	 */
	@ApiOperation(value = "Obtiene el tipo de tarjeta de crédito de acuerdo a la preferencia,"
			            + "el sueldo mensual, y la edad de la persona.")
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<CreditCardDTO>> getCreditCardType(ProfileDTO profileDTO) {
		
		//verifica que si todos los campos están vacíos.
		if (profileDTO.isEmpty()) {
			
			throw new IllegalArgumentException(ErrorMessage.EMPTY_FIELDS);
		}
		
		Set<CreditCardDTO> creditCards = profileService.getCreditCardType(profileDTO);
		
		return ResponseEntity.ok(creditCards);
	}
}
