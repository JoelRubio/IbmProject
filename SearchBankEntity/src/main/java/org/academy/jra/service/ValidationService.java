package org.academy.jra.service;

import java.math.BigDecimal;

import org.academy.jra.model.BankEntityPlace;
import org.academy.jra.model.Latitude;
import org.academy.jra.model.Longitude;
import org.academy.jra.utils.GPSUtils;
import org.springframework.stereotype.Service;

/**
 * Clase que representa la lógica
 * para validar la locación que
 * proporcionó el cliente con los
 * del banco.
 * 
 * @author Joel Rubio
 *
 */
@Service
public class ValidationService {

	
	/**
	 * Verifica si la entidad bancaria es de tipo 
	 * ATM o una sucursal de cualquier tipo.
	 * 
	 * 
	 * @param type  tipo de entidad bancaria
	 * 
	 * @return      true si es una ATM o una sucursal de cualquier tipo, 
	 * 				false de lo contrario 
	 */
	public boolean verifyTypeOfPlace(final String type) {
		
		if (type.toUpperCase().contains(BankEntityPlace.ATM.toString())) {
			return true;
		}
		
		if (type.toUpperCase().contains(BankEntityPlace.SUCURSAL.toString())) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Verifica que el código postal que proporcionó el cliente
	 * sea correcto.
	 * 
	 * @param postalCodeFromClient  código postal del ATM o la sucursal que proporcionó el cliente
	 * @param postalCodeFromBank    código postal donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coincide el código postal,
	 * 		   false de lo contrario
	 */
	public boolean verifyPostalCode(final String postalCodeFromClient, final String addressFromBank) {
		
		return addressFromBank.contains(postalCodeFromClient);
	}
	
	/**
	 * Verifica que la latitud que proporcionó el cliente
	 * es correcta o se encuentra cerca del ATM o sucursal.
	 * 
	 * @param gpsFromClient     coordenadas del ATM o la sucursal que proporcionó el cliente
	 * @param latitudeFromBank  latitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coincide la coordenada o está cerca,
	 * 		   false de lo contrario
	 */
	public boolean verifyLatitude(final Latitude latitudeFromClient, final String latitudeFromBank) {
		
		return latitudeFromBank.equals(latitudeFromClient.toString()) ||
			  (GPSUtils.isEqualInIntegerPart(new BigDecimal(latitudeFromBank), latitudeFromClient.getValue()) && 
		       GPSUtils.areFirst4DecimalsEquals(new BigDecimal(latitudeFromBank), latitudeFromClient.getValue()));
	}
	
	/**
	 * Verifica que el lugar (Delegación/Estado) que proporcionó el cliente
	 * se encuentra en la dirección o el Estado del ATM o sucursal.
	 * 
	 * @param gpsFromClient     coordenadas del ATM o la sucursal que proporcionó el cliente
	 * @param longitudeFromBank longitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coincide la coordenada o está cerca,
	 * 		   false de lo contrario
	 */
	public boolean verifyLongitude(final Longitude longitudeFromClient, final String longitudeFromBank) {
		
		return longitudeFromBank.equals(longitudeFromClient.toString()) ||
			   (GPSUtils.isEqualInIntegerPart(new BigDecimal(longitudeFromBank), longitudeFromClient.getValue()) && 
			    GPSUtils.areFirst4DecimalsEquals(new BigDecimal(longitudeFromBank), longitudeFromClient.getValue()));
	}
	
	
	/**
	 * Verifica que el lugar (Delegación/Estado) que proporcionó el cliente
	 * se encuentra en la dirección o el Estado del ATM
	 * o sucursal.
	 * 
	 * @param placeFromClient  Delegación/Estado del ATM o la sucursal que proprcionó el cliente
	 * @param addressFromBank  dirección donde se encuentra el ATM o la sucursal
	 * @param stateFromBank    Estado donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si se encuentra en algún campo,
	 * 		   false de lo contrario
	 */
	public boolean verifyAddress(final String placeFromClient, 
			 					 final String addressFromBank, 
			 					 final String stateFromBank) {
		
		return addressFromBank.toLowerCase().contains(placeFromClient.toLowerCase()) || 
			   stateFromBank.equalsIgnoreCase(placeFromClient);
	}
}
