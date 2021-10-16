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
 * para manejar las excepciones de la aplicación.
 * 
 * @author Joel Rubio
 *
 */
@ControllerAdvice
public class ProfileExceptionController {

	/**
	 * Maneja la excepción EntityNotFoundException y regresa
	 * una respuesta con un mensaje de error personalizado.
	 * 
	 * 
	 * @param exception excepción lanzada en la aplicación
	 * @param request   petición hecha por el cliente
	 * @return          respuesta con un mensaje de error personalizado
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
		
		CustomErrorMessage errorMessage = CustomErrorMessage.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(exception.getMessage())
				.description(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Maneja la excepción IllegalArgumentException y regresa
	 * una respuesta con un mensaje de error personalizado.
	 * 
	 * 
	 * @param exception excepción lanzada en la aplicación
	 * @param request   petición hecha por el cliente
	 * @return          respuesta con un mensaje de error personalizado
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
}
