package org.academy.jra.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.academy.jra.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que representa el repositorio
 * del dominio Profile, extendiendo la interfaz
 * JpaRepository que permite obtener una implementación
 * por parte del framework.
 * 
 * @author joel
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query(value = "SELECT * "
			     + "FROM Profile "
			     + "WHERE passion = :passion "
			     + "AND (min_monthly_salary <= :monthlySalary AND :monthlySalary <= max_monthly_salary) "
				 + "AND (min_age <= :age AND :age <= max_age)", nativeQuery = true)
	Optional<Profile> findByPassionAndMonthlySalaryAndAge(@Param("passion") String passion, 
			                                              @Param("monthlySalary") BigDecimal monthlySalary, 
			                                              @Param("age") int age);
}