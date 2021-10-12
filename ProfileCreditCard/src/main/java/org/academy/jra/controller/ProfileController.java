package org.academy.jra.controller;

import java.math.BigDecimal;

import org.academy.jra.model.Passion;
import org.academy.jra.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Clase que representa un controlador REST
 * que contiene métodos que mapearán los 
 * endpoints del servicio profile.
 * 
 * @author joel
 *
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

	private ProfileService profileService;
	
	public ProfileController(ProfileService profileService) {
		
		this.profileService = profileService;
	}
	
	
	/**
	 * Método que manejára la petición del cliente
	 * para que se le regrese un tipo de tarjeta de 
	 * crédito acorde a sus parámetros.
	 * 
	 * 
	 * @param passion       pasión elegida por el cliente
	 * @param monthlySalary salario mensual del ciente
	 * @param age           edad del cliente 
	 * @return              tarjeta de crédito de acuerdo a sus parámetros
	 */
	@GetMapping
	public ResponseEntity<Object> getCreditCardType(@RequestParam("passion") Passion passion,
												    @RequestParam("monthlySalary") BigDecimal monthlySalary,
												    @RequestParam("age") int age) {
		
		
		return ResponseEntity.ok(null);
	}
}
