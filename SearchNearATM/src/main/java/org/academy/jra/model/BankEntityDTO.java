package org.academy.jra.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Clase que representa la
 * locación del cajero automático
 * o sucursal encontrado.
 * 
 * @author joel
 *
 */
@Data
public class BankEntityDTO implements Serializable {

	private static final long serialVersionUID = -721608304256692081L;

	private String latitude;
	private String longitude;
	private String address;
	private String state;
	private String type;
}