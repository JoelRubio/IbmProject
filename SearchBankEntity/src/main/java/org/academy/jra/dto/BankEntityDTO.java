package org.academy.jra.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Clase que representa un DTO para
 * la locación de un ATM y/o sucursal 
 * encontrado.
 * 
 * @author Joel Rubio
 *
 */
@Data
@Builder
public class BankEntityDTO implements Serializable {

	private static final long serialVersionUID = -721608304256692081L;

	private String latitude;
	private String longitude;
	private String street;
	private String address;
	private String state;
	private String hourOpen;
	private String hourClose;
	private String type;
}
