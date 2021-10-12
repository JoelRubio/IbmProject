package org.academy.jra.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CreditCard {

	@Id
	@Column(name = "credit_card_id", unique = true, nullable = false)
	private long creditCardId;
	
	@Column(nullable = false)
	private String type;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "creditCards")
	private Set<Profile> profiles = new HashSet<>();
}
