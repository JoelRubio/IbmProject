package org.academy.jra.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un
 * mensaje de error personalizado.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
@Builder
public class CustomErrorMessage {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String description;
}
