package org.academy.jra.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * Clase que representa la
 * locación del cajero automático
 * o sucursal encontrado.
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
	private String address;
	private String state;
	private String type;
}
