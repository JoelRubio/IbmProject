package org.academy.jra.exception;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Clase que representa un controlador global
 * para el manejar de excepciones lanzadas
 * por la aplicación.
 * 
 * @author Joel Rubio
 *
 */
@ControllerAdvice
public class LocationControllerAdvice {

	/**
	 * Maneja la excepción IllegalArgumentException y después
	 * regresa una respuesta HTTP con un mensaje de errror 
	 * personalizado.
	 * 
	 * @param exception excepción lanzada por la aplicación
	 * @param request   petición web realizada por el cliente
	 * 
	 * @return         respuesta HTTP con un mensaje de error personalizado 
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
		
		CustomErrorMessage errorMessage = CustomErrorMessage.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(exception.getMessage())
				.description(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Maneja la excepción EntityNotFoundException y después
	 * regresa una respuesta HTTP con un mensaje de errror 
	 * personalizado.
	 * 
	 * @param exception excepción lanzada por la aplicación
	 * @param request   petición web realizada por el cliente
	 * 
	 * @return         respuesta HTTP con un mensaje de error personalizado 
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
		
		CustomErrorMessage errorMessage = CustomErrorMessage.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(exception.getMessage())
				.description(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
