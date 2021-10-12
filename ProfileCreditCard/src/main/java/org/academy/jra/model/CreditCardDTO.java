package org.academy.jra.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardDTO implements Serializable {

	private static final long serialVersionUID = 7452797134622794436L;

	private String type;
}
