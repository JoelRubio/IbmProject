package org.academy.jra.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	
	@Enumerated
	private Passion passion;
	
	@Column(name = "min_monthly_salary", nullable = false)
	private BigDecimal minMonthlySalary;
	
	@Column(name = "max_monthly_salary", nullable = false)
	private BigDecimal maxMonthlySalary;
	
	@Column(name = "min_age", nullable = false)
	private int minAge;
	
	@Column(name = "max_age", nullable = false)
	private int maxAge;
}
