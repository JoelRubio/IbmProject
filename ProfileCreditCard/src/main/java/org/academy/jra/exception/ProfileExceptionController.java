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
 * para maneja excepciones de la aplicación.
 * 
 * @author joel
 *
 */
@ControllerAdvice
public class ProfileExceptionController {

	/**
	 * Maneja la excepción EntityNotFoundException y regresa
	 * una respuesta con un mensaje de error específico.
	 * 
	 * 
	 * @param exception excepción lanzada en la aplicación
	 * @param request   petición hecha por el cliente
	 * @return          respuesta con un mensaje de error específico
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.toString())
				.message(exception.getMessage())
				.description(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Maneja la excepción IllegalArgumentException y regresa
	 * una respuesta con un mensaje de error específico.
	 * 
	 * 
	 * @param exception excepción lanzada en la aplicación
	 * @param request   petición hecha por el cliente
	 * @return          respuesta con un mensaje de error específico
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.toString())
				.message(exception.getMessage())
				.description(request.getDescription(false))
				.build();
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
