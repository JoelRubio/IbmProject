package org.academy.jra.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un mensaje
 * de error para ser colocado en
 * el cuerpo de la respuesta cuando
 * ocurra una excepción.
 * 
 * @author joel
 *
 */
@Getter
@Setter
@Builder
public class ErrorMessage {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String description;
}
