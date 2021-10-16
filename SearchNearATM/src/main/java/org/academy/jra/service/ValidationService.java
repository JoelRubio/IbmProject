package org.academy.jra.service;

import java.math.BigDecimal;

import org.academy.jra.domain.GPS;
import org.academy.jra.domain.Location;
import org.academy.jra.model.BankEntityPlace;
import org.academy.jra.utils.GPSUtils;
import org.academy.jra.utils.ValidationConstants;
import org.json.JSONArray;
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
		
		if (BankEntityPlace.ATM.toString().contains(type)) {
			return true;
		}
		
		if (BankEntityPlace.SUCURSAL.toString().contains(type.toUpperCase())) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Verifica si es válido el código postal y la latitud.
	 * 
	 * @param location   locación que proporcionó el cliente
	 * @param postalCode código postal donde se encuentra el ATM o la sucursal
	 * @param latitude   latitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isPostalCodeAndLatitudeValid(final Location location, final String postalCode, final String latitude) {
		
		return verifyPostalCode(location.getPostalCode().getNumber(), postalCode) &&
			   verifyLatitude(location.getGps(), latitude);
	}
	
	/**
	 * Verifica si es válido código postal y la longitud.
	 * 
	 * @param location   locación que proporcionó el cliente
	 * @param postalCode código postal donde se encuentra el ATM o la sucursal
	 * @param longitude  longitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isPostalCodeAndLongitudeValid(final Location location, final String postalCode, final String longitude) {
		
		return verifyPostalCode(location.getPostalCode().getNumber(), postalCode) &&
			   verifyLongitude(location.getGps(), longitude);
	}
	
	/**
	 * Verifica si es válido el código postal, la latitud,
	 * y la longitud.
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param address   dirección donde se encuentra el ATM o la sucursal
	 * @param latitude  latitud donde se encuentra el ATM o la sucursal
	 * @param longitude longitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isPostalCodeAndLatitudeAndLongitudeValid(final Location location, 
														    final String address, 
														    final String latitude,
														    final String longitude) {
		
		return verifyPostalCode(location.getPostalCode().getNumber(), address) &&
			   verifyLatitude(location.getGps(), latitude) &&
		       verifyLongitude(location.getGps(), longitude);
	}
	
	/**
	 * Verifica si es válido el código postal, la
	 * longitud y el lugar (Delegación/Estado).
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param longitude longitud donde se encuentra el ATM o la sucursal
	 * @param address   dirección donde se encuentra el ATM o la sucursal
	 * @param state     Estado donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isPostalCodeAndLongitudeAndPlaceValid(final Location location,
														 final String longitude,
														 final String address,
														 final String state) {
		
		return verifyPostalCode(location.getPostalCode().getNumber(), address) &&
				verifyLatitude(location.getGps(), longitude) &&
				verifyAddress(location.getPlace().getName(), address, state);
	}
	
	/**
	 * Verifica si es válida la latitud y longitud.
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param latitude  latitud donde se encuentra el ATM o la sucursal
	 * @param longitude longitud donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isLatitudeAndLongitudeValid(final Location location, final String latitude, final String longitude) {
		
		return verifyLatitude(location.getGps(), latitude) &&
			   verifyLongitude(location.getGps(), longitude);
	}
	
	
	/**
	 * Verifica si es válida la latitud y lugar (Delegación/Estado).
	 * 
	 * @param location locación que proporcionó el cliente
	 * @param latitude latitud donde se encuentra el ATM o la sucursal
	 * @param address  dirección donde se encuentra el ATM o la sucursal
	 * @param state    Estado donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isLatitudeAndPlaceValid(final Location location, final String latitude, final String address, final String state) {
		
		return verifyLatitude(location.getGps(), latitude) &&
			   verifyAddress(location.getPlace().getName(), address, state);
	}
	
	
	/**
	 * Verifica si es válida la longitud y lugar (Delegación/Estado).
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param longitude longitud donde se encuentra el ATM o la sucursal
	 * @param address   dirección donde se encuentra el ATM o la sucursal
	 * @param state     Estado donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si coinciden los campos,
	 * 		   false de lo contrario
	 */
	public boolean isLongitudeAndPlaceValid(final Location location, final String longitude, final String address, final String state) {
		
		return verifyLongitude(location.getGps(), longitude) &&
			   verifyAddress(location.getPlace().getName(), address, state);
	}
	
	
	/**
	 * Verifica si es válida la latitud, longitud y lugar (Delegación/Estado).
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param latitude  latitud donde se encuentra el ATM o la sucursal
	 * @param longitude longitud donde se encuentra el ATM o la sucursal
	 * @param address   dirección donde se encuentra el ATM o la sucursal
	 * @param state     Estado donde se encuentra el ATM o la sucursal
	 * 
	 * @return true si es válida la latitud y el lugar,
	 * 		   false de lo contrario
	 */
	public boolean isLatitudeAndLongitudeAndPlaceValid(final Location location, final String latitude,
													   final String longitude, final String address, final String state) {
		
		return verifyLatitude(location.getGps(), latitude) &&
			   verifyLongitude(location.getGps(), longitude) &&
			   verifyAddress(location.getPostalCode().getNumber(), address, state);
	}
	
	/**
	 * Valida que todos los campos sean válidos.
	 * 
	 * @param location  locación que proporcionó el cliente
	 * @param jsonArray arreglo que contiene los valores del ATM o sucursal
	 * 
	 * @return true si coinciden todos los campos,
	 * 		   false de lo contrario
	 */
	public boolean areAllValid(final Location location, final JSONArray jsonArray) {
		
		final String LATITUDE  = jsonArray.getString(ValidationConstants.POSITION_FOR_LATITUDE);
		final String LONGITUDE = jsonArray.getString(ValidationConstants.POSITION_FOR_LONGITUDE);
		final String ADDRESS   = jsonArray.getString(ValidationConstants.POSITION_FOR_ADDRESS);
		final String STATE     = jsonArray.getString(ValidationConstants.POSITION_FOR_STATE);
		
		return verifyPostalCode(location.getPostalCode().getNumber(), ADDRESS) &&
			   verifyLatitude(location.getGps(), LATITUDE) &&
			   verifyLongitude(location.getGps(), LONGITUDE) &&
			   verifyAddress(location.getPlace().getName(), ADDRESS, STATE);
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
	private boolean verifyPostalCode(final String postalCodeFromClient, final String postalCodeFromBank) {
		
		return postalCodeFromBank.contains(postalCodeFromClient);
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
	private boolean verifyLatitude(final GPS gpsFromClient, final String latitudeFromBank) {
		
		return latitudeFromBank.equals(gpsFromClient.latitudeToString()) ||
			  (GPSUtils.isEqualInIntegerPart(new BigDecimal(latitudeFromBank), gpsFromClient.getLatitude()) && 
		       GPSUtils.areFirst4DecimalsEquals(new BigDecimal(latitudeFromBank), gpsFromClient.getLatitude()));
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
	private boolean verifyLongitude(final GPS gpsFromClient, final String longitudeFromBank) {
		
		return longitudeFromBank.equals(gpsFromClient.longitudeToString()) ||
			   (GPSUtils.isEqualInIntegerPart(new BigDecimal(longitudeFromBank), gpsFromClient.getLongitude()) && 
			    GPSUtils.areFirst4DecimalsEquals(new BigDecimal(longitudeFromBank), gpsFromClient.getLongitude()));
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
	private boolean verifyAddress(final String placeFromClient, 
			 					  final String addressFromBank, 
			 					  final String stateFromBank) {
		
		return addressFromBank.contains(placeFromClient) || stateFromBank.equals(placeFromClient);
	}
}
