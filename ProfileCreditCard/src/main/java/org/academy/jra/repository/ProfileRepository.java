package org.academy.jra.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.academy.jra.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que representa el repositorio
 * del dominio Profile y su relación con 
 * CreditCard.
 * 
 * @author Joel Rubio
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	//Regresa sólo un Profile verificando preferencia, salario mensual, y edad.
	@Query(value = "SELECT * "
			     + "FROM Profile "
			     + "WHERE passion = :passion AND "
			     + " (min_monthly_salary <= :monthlySalary AND :monthlySalary <= max_monthly_salary) AND "
				 + " (min_age <= :age AND :age <= max_age)", nativeQuery = true)
	Optional<Profile> findByPassionAndMonthlySalaryAndAge(@Param("passion") String passion, 
			                                              @Param("monthlySalary") BigDecimal monthlySalary, 
			                                              @Param("age") int age);
	
	//Regresa una lista de Profiles verificando preferencia.
	@Query(value = "SELECT * "
			     + "FROM Profile "
			     + "WHERE passion = :passion", nativeQuery = true)
	List<Profile> findByPassion(@Param("passion") String passion);
	
	
	
	//Regresa una lista de Profiles verificando edad.
	@Query(value = "SELECT * "
			     + "FROM Profile "
			     + "WHERE min_age <= :age AND :age <= max_age", nativeQuery = true)
	List<Profile> findByAge(@Param("age") int age);
	

	
	//Regresa una lista de Profiles verificando salario mensual.
	@Query(value = "SELECT * "
			     + "FROM Profile "
			     + "WHERE min_monthly_salary <= :monthlySalary AND :monthlySalary <= max_monthly_salary", nativeQuery = true)
	List<Profile> findByMonthlySalary(@Param("monthlySalary") BigDecimal monthlySalary);
	
	
	
	//Regresa una lista de Profiles verificando preferencia y edad.
	@Query(value = "SELECT * "
		     + "FROM Profile "
		     + "WHERE passion = :passion AND "
		     + " (min_age <= :age AND :age <= max_age)", nativeQuery = true)
	List<Profile> findByPassionAndAge(@Param("passion") String passion, @Param("age") int age);
	
	
	
	//Regresa una lista de Profiles verificando preferencia y salario mensual.
	@Query(value = "SELECT * "
		     + "FROM Profile "
		     + "WHERE passion = :passion AND "
		     + " (min_monthly_salary <= :monthlySalary AND :monthlySalary <= max_monthly_salary)", nativeQuery = true)
	List<Profile> findByPassionAndMonthlySalary(@Param("passion") String passion, @Param("monthlySalary") BigDecimal monthlySalary);

	
	
	//Regresa una lista de Profiles verificando salario mensual y edad.
	@Query(value = "SELECT * "
		     + "FROM Profile "
		     + "WHERE (min_monthly_salary <= :monthlySalary AND :monthlySalary <= max_monthly_salary) AND "
		     + "      (min_age <= :age AND :age <= max_age)", nativeQuery = true)
	List<Profile> findByMonthlySalaryAndAge(@Param("monthlySalary") BigDecimal monthlySalary, @Param("age") int age);
	
}
