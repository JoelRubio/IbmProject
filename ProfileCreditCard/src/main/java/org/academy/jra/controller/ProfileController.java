package org.academy.jra.controller;

import java.math.BigDecimal;
import java.util.Set;

import org.academy.jra.model.CreditCardDTO;
import org.academy.jra.model.ProfileDTO;
import org.academy.jra.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


/**
 * Clase que representa un controlador REST
 * que contiene métodos que mapearán los 
 * endpoints del servicio profile.
 * 
 * @author joel
 *
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

	private ProfileService profileService;
	
	/**
	 * Se realiza inyección de dependencias por argumentos,
	 * ya que no es necesario colocar @Autowired si sólo se tiene
	 * un constructor.
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
	@GetMapping
	public ResponseEntity<Set<CreditCardDTO>> getCreditCardType(@RequestParam("passion") String passion,
												                @RequestParam("monthlySalary") BigDecimal monthlySalary,
												                @RequestParam("age") int age) {
		
		ProfileDTO profileDTO = new ProfileDTO(passion, monthlySalary, age);
		
		Set<CreditCardDTO> creditCards = profileService.getCreditCardType(profileDTO);
		
		return ResponseEntity.ok(creditCards);
	}
}
