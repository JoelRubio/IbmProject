package org.academy.jra.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un DTO
 * para el tipo de tarjeta de 
 * crédito.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class CreditCardDTO implements Serializable {

	private static final long serialVersionUID = 7452797134622794436L;

	private String type;
}
