package org.academy.jra.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la entidad
 * para el perfil del cliente al
 * solicitar un tipo de tarjeta de
 * crédito.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
@Entity
public class Profile {

	@Id
	@Column(name = "profile_id", unique = true, nullable = false)
	private long profileId;
	
	private String passion;
	
	@Column(name = "min_monthly_salary", nullable = false)
	private BigDecimal minMonthlySalary;
	
	@Column(name = "max_monthly_salary")
	private BigDecimal maxMonthlySalary;
	
	@Column(name = "min_age", nullable = false)
	private int minAge;
	
	@Column(name = "max_age", nullable = false)
	private int maxAge;
	
	@ManyToMany
	@JoinTable(name = "ProfileCreditCard",
	           joinColumns = @JoinColumn(name = "profile_id"),
	           inverseJoinColumns = @JoinColumn(name = "credit_card_id"))
	private Set<CreditCard> creditCards = new HashSet<>();
}
